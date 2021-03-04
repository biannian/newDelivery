package com.pgk.delivery.Order.Service.ServiceImpl;

import com.pgk.delivery.Buyer.Mapper.BuyerMapper;
import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Order.Mapper.OrderMapper;
import com.pgk.delivery.Order.Pojo.Order;
import com.pgk.delivery.Order.Pojo.Shopping;
import com.pgk.delivery.Shop.Mapper.ShopMapper;
import com.pgk.delivery.Shop.Pojo.Commodity;
import com.pgk.delivery.Shop.Pojo.Shop; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class   OrderService implements com.pgk.delivery.Order.Service.OrderService {

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private BuyerMapper buyerMapper;


    @Override
    public Result<?> addOrder(Order order) {
        mapper.addOrder(order);
        for (int i = 0; i < order.getShopping().size(); i++) {
            //根据i来循环order实体里的商品，
            order.getShopping().get(i).setShoppingOrderId(order.getOrderId());

            mapper.addShopping(order.getShopping().get(i));
            //A为订单中商品数量，commodityNumber为商品库存
            order.getShopping().get(i).setCommodityNumber(
                    order.getShopping().get(i).getCommodityNumber()
                            - order.getShopping().get(i).getA());

            mapper.deleteCommodityNumber(order.getShopping().get(i));
        }
        return Result.success();
    }

    @Override
    public Result<?> selectOrder(String orderBuyerId) {
        Order order = new Order();
        order.setOrderBuyerId(orderBuyerId);
        List<Order> orders = mapper.selectOrder(order);

        List<List<Shopping>> shoppings = new ArrayList<>();
        HashMap<Integer, String> shops = new HashMap<>();

        for (int i = 0; i < orders.size(); i++) {
            //根据订单中的店铺编号查询店铺信息
            shops.put(orders.get(i).getShopId(), shopMapper.queryForName(orders.get(i).getShopId()));
            //根据订单编号去查询中间表中的商品
            shoppings.add(mapper.selectShopping(orders.get(i).getOrderId()));
        }
        HashMap<String, Object> orderMap = new HashMap<>();
        orderMap.put("shoppings", shoppings);
        orderMap.put("orders", orders);
        orderMap.put("shops", shops);
        return Result.success(orderMap);
    }
    @Override
    public Result<?> updateState(Order order) {
        if (order.getShopping() == null) {
            int msg = mapper.updateState(order);
            return Result.success(msg);
        } else {
            int msg = 0;
            mapper.updateState(order);
            //根据订单中的商品数量进行循环，先去数据库中查询出商品库存，然后将前端传来的数量加上库存存入数据库中
            for (int i = 0; i < order.getShopping().size(); i++) {
                Commodity commodity = shopMapper.queryCommodityById(order.getShopping().get(i).getCommodityId());
                order.getShopping().get(i).setCommodityNumber(commodity.getCommodityNumber() + order.getShopping().get(i).getCommodityNumber());
                msg = +mapper.deleteCommodityNumber(order.getShopping().get(i));
            }
            return Result.success(msg);
        }
    }

    @Override
    public Result<?> deleteOrder(int orderId) {
        int orderMsg = mapper.deleteOrder(orderId);
        int shoppingMsg = mapper.delectShopping(orderId);
        if (orderMsg == 1){
            return Result.success(shoppingMsg);
        }else {
            return Result.fail(500);
        }

    }

    @Override
    public Result<?> sellerSelectOrder(int accountUserId) {
        Order order = new Order();
        Shop shop = shopMapper.selectShopInformation(accountUserId);
        order.setShopId(shop.getShopId());
        List<Order> Orders = mapper.selectOrder(order);
        return Result.success(Orders);
    }

    @Override
    public Result<?> sellerSelectOrderById(int accountUserId) {
        Order order = new Order();
        Shop shop = shopMapper.selectShopInformation(accountUserId);
        order.setShopId(shop.getShopId());
        //把实体中的是否将订单状态码当作查询条件 设为true
        order.setStateOpen(true);
        //根据店铺Id 查询订单
        List<Order> Orders = mapper.selectOrder(order);
        //实例一个存放商品的集合
        List<List<Shopping>> shoppings = new ArrayList<>();
        //实例一个存放用户地址的集合
        List<Buyer> buyers = new ArrayList<>();
        for (int i = 0; i <Orders.size() ; i++) {
            shoppings.add( mapper.selectShopping(Orders.get(i).getOrderId()));
            buyers.add(buyerMapper.getBuyerAddress(Orders.get(i).getOrderBuyerId()));
        }
        HashMap<String, Object> orderMap = new HashMap<>();
        orderMap.put("buyers",buyers);
        orderMap.put("shoppings",shoppings);
        orderMap.put("Orders",Orders);
        return Result.success(orderMap);
    }
}

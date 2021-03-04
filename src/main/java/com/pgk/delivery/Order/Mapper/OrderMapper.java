package com.pgk.delivery.Order.Mapper;


import com.pgk.delivery.Order.Pojo.Order;
import com.pgk.delivery.Order.Pojo.Shopping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 新增订单表
     *
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 新增订单中间表数据
     *
     * @param shopping
     * @return
     */
    int addShopping(Shopping shopping);

    /**
     * 删除商品库存
     *
     * @param shopping
     * @return
     */
    int deleteCommodityNumber(Shopping shopping);

    /**
     * 查询订单
     *
     * @param order
     * @return
     */
    List<Order> selectOrder(Order order);

    /**
     * 根据订单编号查询购买的商品
     *
     * @param shoppingOrderId
     * @return
     */
    List<Shopping> selectShopping(int shoppingOrderId);

    /**
     * 修改订单状态
     * @param order
     * @return
     */
    int updateState(Order order);

    /**
     * 根据订单id去删除订单表数据
     * @param orderId
     * @return
     */
    int deleteOrder(int orderId);
    /**
     * 根据订单ID 去中间表shpping中删除数据
     * @param orderId
     * @return
     */
    int delectShopping(int orderId);
}

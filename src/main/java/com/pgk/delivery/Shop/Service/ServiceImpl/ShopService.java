package com.pgk.delivery.Shop.Service.ServiceImpl;

import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Shop.Mapper.ShopMapper;
import com.pgk.delivery.Shop.Pojo.Commodity;
import com.pgk.delivery.Shop.Pojo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ShopService implements com.pgk.delivery.Shop.Service.ShopService {

    @Autowired
    private ShopMapper mapper;

    @Override
    public Result<?> queryAll() {
//        PageHelper.startPage(pageNum, pageSize);
        List<Shop> shop = mapper.queryAll();
//        PageInfo<Shop> page = new PageInfo<>(shop);
        return Result.success(shop);
    }

    @Override
    public Result<?> queryById(int shopId) {
        if (shopId <= 0) {
            return Result.fail(-1, "没有此店铺");
        }
        Shop shop = mapper.queryById(shopId);
        if (shop == null) {
            return Result.fail(-1, "没有此店铺");
        }
        List<Commodity> commoditys = shop.getCommodity();
        HashMap<Integer,String> shopMenu = new HashMap<>();
        for (Commodity com :
                commoditys) {
            shopMenu.put(com.getCommodityMenuId(), com.getShopMenuName());
        }
        //给菜单排序
        shop.getCommodity().sort(Comparator.comparing(Commodity::getShopMenuId));

        HashMap<Integer, List<Commodity>> commodity = new HashMap<>();

        for (Commodity c : commoditys) {
            if (commodity.get(c.getCommodityMenuId()) == null){
                List<Commodity> list = new LinkedList<>();
                list.add(c);
                commodity.put(c.getCommodityMenuId(), list);
                continue;
            }
            List<Commodity> list = commodity.get(c.getCommodityMenuId());
            list.add(c);
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("shopMenu", shopMenu);
        map.put("commodity",commodity);
        map.put("shop", shop);
        return Result.success(map);
    }

    @Override
    public Result<?> queryByName(String shopName) {

        List<Shop> shop = mapper.queryByName(shopName);

        return Result.success(shop);


    }

    @Override
    public Result<?> queryAllCommodity(Integer accountUserId) {
        if (accountUserId != null){
            Shop shop = mapper.selectShopInformation(accountUserId);
            if (shop ==null){
                return Result.fail();
            }else {
                List<Commodity> commodities = mapper.queryAllCommodity(shop.getShopId());
                return Result.success(commodities);
            }
        }else {
            List<Commodity> commodities = mapper.queryAllCommodity(accountUserId);
            return Result.success(commodities);
        }
    }

    @Override
    public Result<?> delectCommodity(int commodityId) {
        if (commodityId > 0) {
            int a = mapper.delectCommodity(commodityId);
            if (a == 1) {
                return Result.success();
            }
        }
        return Result.fail(-1);
    }

    @Override
    public Result<?> selectMenu() {
        List<Commodity> menu = mapper.selectMenu();
        return Result.success(menu);
    }

    @Override
    public Result<?> commodityAdd(Commodity commodity) {
        //根据sellerId 查询店铺信息，查询出店铺Id
        Shop shop = mapper.selectShopInformation(commodity.getAccountUserId());
        //设置商品里的店铺id
        commodity.setCommodityShopId(shop.getShopId());
        int msg = mapper.commodityAdd(commodity);
        return Result.success(msg);
    }

    @Override
    public Result<?> queryShopName(int sellerId) {
        Shop shop = mapper.queryShopName(sellerId);
        return Result.success(shop);
    }

    @Override
    public Result<?> addMenu(String shopMenuName) {
        int msg = mapper.addMenu(shopMenuName);

        return Result.success(msg);
    }

    @Override
    public Result<?> commodityEdit(Commodity commodity) {
        int msg = mapper.commodityEdit(commodity);
        return Result.success(msg);
    }

    @Override
    public Result<?> selectShopInformation(int sellerId) {
        Shop shop = mapper.selectShopInformation(sellerId);
        return Result.success(shop);
    }

    @Override
    public Result<?> updateShopInformation(Shop shop) {
        if(shop.getShopId() == 0){
            int msg = mapper.addShop(shop);
            return Result.success(msg);
        }else {
        int msg =mapper.updateShopInformation(shop);
        return Result.success(msg);}
    }

    @Override
    public Result<?> queryByType(int shopTypeId) {
        List<Shop> shop = mapper.queryByType(shopTypeId);
        return Result.success(shop);
    }

    @Override
    public Result<?> queryBuyerLikeShop(String buyerAccount) {
        List<Integer> msg = mapper.queryBuyerLikeShop(buyerAccount);
        return Result.success(msg);
    }

    @Override
    public Result<?> addBuyerLikeShop(String buyerAccount, int shopId) {
        int msg = mapper.addBuyerLikeShop(buyerAccount,shopId);
        return Result.success(msg);
    }

    @Override
    public Result<?> deleteBuyerLikeShop(String buyerAccount, int shopId) {
        int msg = mapper.deleteBuyerLikeShop(buyerAccount,shopId);
        return Result.success(msg);
    }

    @Override
    public Result<?> queryBuyerLikeShopInfo(String buyerAccount) {
        List<Shop> msg = mapper.queryBuyerLikeShopInfo(buyerAccount);
        return Result.success(msg);
    }

    @Override
    public Result<?> queryShopInfo(int shopId) {
        Shop shop = mapper.queryShopInfo(shopId);
        return Result.success(shop);
    }

    @Override
    public Result<?> selectShopType() {
        List<Shop> ShopType = mapper.selectShopType();
        return Result.success(ShopType);
    }
}

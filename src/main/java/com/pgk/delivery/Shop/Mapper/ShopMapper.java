package com.pgk.delivery.Shop.Mapper;

import com.pgk.delivery.Shop.Pojo.Commodity;
import com.pgk.delivery.Shop.Pojo.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {
    List<Shop> queryAll();

    Shop queryById(int shopId);

    String queryForName(int shopId);

    List<Shop> queryByName(String shopName);

    List<Commodity> queryAllCommodity(Integer commodityShopId);

    int delectCommodity(int commodityId);

    Commodity queryCommodityById(int commodityId);

    List<Commodity> selectMenu();

    int commodityAdd(Commodity commodity);

    Shop queryShopName(int sellerId);

    int addMenu(String shopMenuName);

    int commodityEdit(Commodity commodity);

    Shop selectShopInformation(int accountUserId);

    int updateShopInformation(Shop shop);

    int addShop(Shop shop);
}

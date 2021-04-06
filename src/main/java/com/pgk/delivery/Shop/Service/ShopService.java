package com.pgk.delivery.Shop.Service;

import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Shop.Pojo.Commodity;
import com.pgk.delivery.Shop.Pojo.Shop;

public interface ShopService {
    Result<?> queryAll( );

    Result<?> queryById(int shopId);

    Result<?> queryByName(String shopName);

    Result<?> queryAllCommodity(Integer accountUserId);

    Result<?> delectCommodity(int commodityId);

    Result<?> selectMenu();

    Result<?> commodityAdd(Commodity commodity);

    Result<?> queryShopName(int sellerId);

    Result<?> addMenu(String shopMenuName);

    Result<?> commodityEdit(Commodity commodity);

    Result<?> selectShopInformation(int sellerId);

    Result<?> updateShopInformation(Shop shop);

    Result<?> queryByType(int shopTypeId);

    Result<?> queryBuyerLikeShop(String buyerAccount);

    Result<?> addBuyerLikeShop(String buyerAccount, int shopId);

    Result<?> deleteBuyerLikeShop(String buyerAccount, int shopId);

    Result<?> queryBuyerLikeShopInfo(String buyerAccount);

    Result<?> queryShopInfo(int shopId);

    Result<?> selectShopType();
}

package com.pgk.delivery.Shop.Pojo;

import lombok.Data;

@Data
public class Commodity {
    private int commodityId;
    private String commodityName;
    private int commodityShopId;
    private int commodityNumber;
    private double commodityPrice;

    private String commodityImg;
    //购买的数量
    private int shoppingNumber;
    private int commodityMenuId;
    private int shopMenuId;
    private String shopMenuName;
    private String shopName;

    /**
     * 相当于sellerId
     */
    private int accountUserId;
}

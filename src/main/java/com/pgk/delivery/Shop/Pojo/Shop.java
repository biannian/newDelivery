package com.pgk.delivery.Shop.Pojo;


import lombok.Data;

import java.util.List;

@Data
public class Shop {
    private int shopId;
    private String shopName;
    private String shopImg;
    private int shopSellerId;//卖家账户ID
    private String shopAddress;//店铺地址
    private double shopStartPrice;//起送价格
    private double shopSendPrice;//配送费
    private double shopSalesVolume;//店铺销量
    private double shopScore;//店铺评分
    private int shopTypeId;//店铺种类id
    private String shopInfo;//店铺介绍

    private List<Commodity> commodity;


}

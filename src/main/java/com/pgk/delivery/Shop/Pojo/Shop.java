package com.pgk.delivery.Shop.Pojo;


import lombok.Data;

import java.util.List;

@Data
public class Shop {
    private int shopId;
    private String shopName;
    private String shopImg;
    private int shopSellerId;
    private String shopAddress;
    private double shopStartPrice;
    private double shopSendPrice;
    private double shopSalesVolume;
    private double shopScore;

    private List<Commodity> commodity;


}

package com.pgk.delivery.Order.Pojo;

import lombok.Data;

@Data
public class Shopping {
    /**
     * 商品编号
     */
    private int commodityId;
    /**
     * 商品数量
     */
    private int a;
    /**
     * 订单编号
     */
    private int shoppingOrderId;
    /**
     * 商品库存
     */
    private int commodityNumber;
    /**
     * 商品名
     */
    private String commodityName;
    /**
     * 商品照片
     */
    private String commodityImg;
    /**
     * 商品价格
     */
    private double commodityPrice;
}

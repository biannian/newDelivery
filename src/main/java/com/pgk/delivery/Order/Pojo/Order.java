package com.pgk.delivery.Order.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    /**
     * 订单编号
     */
    private int orderId;
    /**
     * 店铺编号
     */
    private int shopId;
    /**
     * 买家用户名
     */
    private String orderBuyerAccount;
    /**
     * 骑手用户名
     */
    private String orderRiderAccount;
    /**
     * 订单状态码
     */
    private int orderState;
    /**
     * 用户下单时间
     */
    private String orderBuyerTime;
    /**
     * 用户确认收货时间
     */
    private String orderBuyerTime1;
    /**
     * 商家确认订单时间
     */
    private String orderSellerTime;
    /**
     * 骑手取货时间
     */
    private String orderRiderTime;
    /**
     * 骑手送达时间
     */
    private String orderRiderTime1;
    /**
     * 存放商品的集合
     */
    private List<Shopping> shopping;

    /**
     * 是否将订单状态码当作查询条件
     */
    private boolean stateOpen;

    /**
     * 订单备注
     */
    private String orderTips;
    /**
     * 餐具数量
     */
    private String tableware;
    /**
     * 收货地址
     */
    private BuyerAddress buyerAddress;

    /**
     * 订单店铺名
     */
    private String shopName;


}

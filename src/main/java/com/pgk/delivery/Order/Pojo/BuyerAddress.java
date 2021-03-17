package com.pgk.delivery.Order.Pojo;


import lombok.Data;

@Data
public class BuyerAddress {
    private String buyerName;
    private String buyerAddress;
    private String buyerTel;
    private int orderId;

}

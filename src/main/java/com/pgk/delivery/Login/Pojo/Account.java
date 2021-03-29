package com.pgk.delivery.Login.Pojo;

import lombok.Data;

@Data
public class Account {
    private String accountName;
    private String accountPassword;
    private int accountLimit;
    private boolean accountBan;
    private int accountId;

    private String tableId;
    private int buyerId;
    private int sellerId;
    private int riderId;
    private int accountUserId;
    private String table;
    private String tableAccountName;
    private String tableName;
    private String tableAddress;
    private String tableTel;
    private String tableAge;
    private String tableSex;

    private String buyerName;
    private String openId;//微信验证码
    private String wxName;//微信用户名
    private String wxImage;//微信头像

    private String name;
    private int sex;
    private String address;
    private int age;
    private String tel;
}

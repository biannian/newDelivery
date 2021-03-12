package com.pgk.delivery.Login.Pojo;

import lombok.Data;

@Data
public class Account {
    private String accountName;
    private String accountPassword;
    private int accountLimit;
    private boolean accountBan;
    private int accountId;
    private int accountUserId;
    private String table;
    private String tableId;
    private String tableAccountName;
    private String buyerName;
    private String openId;//微信验证码
    private String wxName;//微信用户名
    private String wxImage;//微信头像
}

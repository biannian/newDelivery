package com.pgk.delivery.Login.Pojo;

import lombok.Data;

@Data
public class WxCode {
    private int limit;//账户权限
    private String code;
    private String encryptedData;
    private String iv;
    private String wxName;//微信用户名
    private String wxImage;//微信头像
}

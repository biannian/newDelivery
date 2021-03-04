package com.pgk.delivery.Model;

public enum ErrorCode {
    USERNAME_OR_PASSWORD_ERROR(100, "用户名或密码错误"),
    AUTH_ERROR(401, "身份验证失败，请重新登录"),
    REGISTER_ERRoR(208, "注册失败"),
    ACCOUNT_BAN(403, "账户被禁用");
    private Integer value;

    private String desc;

    ErrorCode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

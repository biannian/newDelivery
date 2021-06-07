package com.pgk.delivery.Shop.Pojo;

import lombok.Data;

@Data
public class Comment {
    private int orderId;
    private int shopId;
    private String commentImg;
    private String commentInfo;
    private String accountName;
    private String commentTime;
    private int commentScore;
    private String shopReply;
    private String shopReplyTime;
    private String wxName;
    private String wxImage;
}

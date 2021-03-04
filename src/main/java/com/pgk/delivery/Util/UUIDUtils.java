package com.pgk.delivery.Util;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");

        return uid;
    }
}

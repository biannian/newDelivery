package com.pgk.delivery.Config;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    public static String EncoderByMd5(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String NewPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        return NewPassword;
    }
}

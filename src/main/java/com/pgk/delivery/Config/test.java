package com.pgk.delivery.Config;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class test {
    public static void main(String[] args) {
        String str = "1234";
        System.out.println(Md5.EncoderByMd5(str));
    }
}

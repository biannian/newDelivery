package com.pgk.delivery.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

public class JWTUtil {
    public static String createToken(String accountName, int accountLimit , int accountUserId) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        Date expiresDate = nowTime.getTime();
        return JWT.create().withAudience(accountName)
                .withIssuedAt(new Date())
                .withExpiresAt(expiresDate)
                .withClaim("accountLimit", accountLimit)
                .withClaim("accountUserId", accountUserId)
                .sign(Algorithm.HMAC256(accountName + "Hellosiri"));
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     *
     * @param token
     */
    public static boolean verifyToken(String token, String secret) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret + "Hellosiri")).build();
            jwt = verifier.verify(token);
        } catch (TokenExpiredException e) {
            System.out.println("token已过期");
            return true;
        } catch (SignatureVerificationException e) {
            System.out.println("token错误");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    /**
     * 判断token还有五分钟过期
     *
     * @param token
     */
    public static boolean checkTokenDate(String token) {
        Date expiresDate = JWT.decode(token).getExpiresAt();
        Calendar nowDate = Calendar.getInstance();
        nowDate.add(Calendar.MINUTE, 5);
        Date nowDate1 = nowDate.getTime();
        if (new Date().before(expiresDate) && nowDate1.after(expiresDate)) {
            System.out.println("token还有不到5分钟过期");
            return true;
        }
        return false;
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return audience;

    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name) {

        return JWT.decode(token).getClaim(name);
    }


}

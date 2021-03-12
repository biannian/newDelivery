package com.pgk.delivery.Login.Controller;

import com.alibaba.fastjson.JSONObject;
import com.pgk.delivery.Login.Pojo.Account;
import com.pgk.delivery.Login.Pojo.WxCode;
import com.pgk.delivery.Login.Service.LoginService;
import com.pgk.delivery.Util.AesCbcUtil;
import com.pgk.delivery.Util.HttpRequest;
import com.pgk.delivery.Util.PassToken;
import com.pgk.delivery.Model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/Login")
public class LoginController {


    @Autowired
    private LoginService service;

    @PassToken
    @RequestMapping(value = "/login.do")
    public Result<?> login(String accountName, String accountPassword) {
        Result<?> result = service.login(accountName, accountPassword);
        return result;
    }

    @PassToken
    @RequestMapping(value = "/wxLogin.do")
    public Result<?> wxLogin(@RequestBody WxCode wxCode) {
        if (wxCode.getCode() == null || wxCode.getEncryptedData() == null || wxCode.getIv() == null) {
            return Result.fail(0);
        }
        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxAppid = "wxe9450ee23087084b";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxSecret = "b51a48cae547f0d777d6c81fea0ea4be";

        String grant_type = "authorization_code";

        String params = "appid=" + wxAppid + "&secret=" + wxSecret + "&js_code=" + wxCode.getCode() + "&grant_type="
                + grant_type;

        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);

        JSONObject json = JSONObject.parseObject(sr);
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        Result<?> result = service.wxLogin(openid,wxCode.getWxName(),wxCode.getWxImage());
        return result;
    }

    @PassToken
    @RequestMapping(value = "/queryById.do")
    public Result<?> queryById(String accountName) {
        Result<?> account = service.queryById(accountName);
        if (account.getCode() == 200) {
            return Result.success(-1);
        } else {
            return Result.success();
        }
    }

    @RequestMapping(value = "/queryAll.do")
    public Result<?> queryAll(HttpServletRequest req) {
        Result<?> result = service.queryAll();
        return result;
    }

    @RequestMapping(value = "/getLimit.do")
    public Result<?> getLimit(HttpServletRequest req) {

        int accountLimit = (int) req.getAttribute("accountLimit");
        int accountUserId = (int) req.getAttribute("accountUserId");
        String accountName = (String) req.getAttribute("accountName");
        Map map = new HashMap();
        map.put("accountUserId", accountUserId);
        map.put("accountLimit", accountLimit);
        map.put("accountName", accountName);
        return Result.success(map);
    }

    @RequestMapping(value = "/accountDelete.do")
    public Result<?> accountDelete(HttpServletRequest req, int accountId, HttpServletResponse response) {
        int accountLimit = (int) req.getAttribute("accountLimit");
        if (accountLimit == 4) {
            Result<?> result = service.accountDelete(accountId);
            if (result.getCode() == -1) {
                response.setStatus(500);
                return null;
            }
            return Result.success();
        }
        return Result.fail(-1);
    }

    @RequestMapping(value = "/banAccount.do")
    public Result<?> banAccount(HttpServletRequest req, Account account, HttpServletResponse response) {
        int accountLimit = (int) req.getAttribute("accountLimit");
        if (accountLimit == 4) {
            Result<?> result = service.banAccount(account);
            if (result.getCode() == -1) {
                response.setStatus(500);
                return null;
            }
            return Result.success();
        }
        return Result.fail(-1);
    }

    @PassToken
    @RequestMapping(value = "/register.do")
    public Result<?> register(@RequestBody Account account) {
        Result<?> msg = service.register(account);
        return msg;
    }

    @RequestMapping(value = "/queryByName.do")
    public Result<?> queryByName(String accountName) {
        Account account = service.queryByName(accountName);
        return Result.success(account);
    }

    @RequestMapping(value = "/updatePassword.do")
    public Result<?> updatePassword(Account account) {
        int msg = service.updatePassword(account);
        return Result.success(msg);
    }

    /**
     * 查看是否存到收货地址
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/selectAddress.do")
    public Result<?> selectAddress(Account account) {
        boolean msg = service.selectAddress(account);

        return Result.success(msg);
    }
}

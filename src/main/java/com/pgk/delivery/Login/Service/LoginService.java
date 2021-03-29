package com.pgk.delivery.Login.Service;

import com.pgk.delivery.Config.Md5;
import com.pgk.delivery.Login.Mapper.LoginMapper;
import com.pgk.delivery.Model.ErrorCode;
import com.pgk.delivery.Login.Pojo.Account;
import com.pgk.delivery.Util.JWTUtil;
import com.pgk.delivery.Model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LoginService {

    @Autowired
    private LoginMapper mapper;

    public Result<?> queryById(String accountName) {
        List<Account> accounts = mapper.queryById(accountName);

        if (accounts.size() == 0) {
            return Result.fail(-1);
        } else {
            return Result.success(accounts);
        }
    }

    public Result<?> queryAll() {
        List<Account> account = mapper.queryAll();
        if (account != null && account.size() == 0) {
            return Result.fail(-1, "查询失败");
        } else {
            return Result.success(account);
        }
    }

    public Account queryByName(String accountName) {
        return mapper.queryByName(accountName);
    }

    public Result<?> login(String accountName, String accountPassword) {

        Account account = mapper.login(accountName, Md5.EncoderByMd5(accountPassword));
        if (account == null) {
            return Result.fail(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        } else {
            if (!account.isAccountBan()) {
                String jwtToken = JWTUtil.createToken(accountName, account.getAccountLimit(), account.getAccountUserId());
                HashMap<String, String> map = new HashMap();
                map.put("token", jwtToken);
                map.put("wxName", account.getWxName());
                map.put("wxImage", account.getWxImage());
                return Result.success(map);
            } else {
                return Result.fail(ErrorCode.ACCOUNT_BAN);
            }
        }
    }

    public Result<?> register(Account account) {
        account.setAccountPassword(Md5.EncoderByMd5(account.getAccountPassword()));
        if (account.getAccountLimit() == 0) {
            return Result.fail(ErrorCode.REGISTER_ERRoR);
        } else {
            switch (account.getAccountLimit()) {
                case 1:
                    account.setTable("buyer");
                    account.setTableId("buyerId");
                    account.setTableAccountName("buyerAccountName");
                    break;
                case 2:
                    account.setTable("rider");
                    account.setTableId("riderId");
                    account.setTableAccountName("riderAccountName");
                    break;
                case 3:
                    account.setTable("seller");
                    account.setTableId("sellerId");
                    account.setTableAccountName("sellerAccountName");
                    break;
            }
            mapper.addInformation(account);

            int userId = mapper.selectUserId(account);

            account.setAccountUserId(userId);

            int msg = mapper.register(account);

            if (msg == 1) {
                String jwtToken = JWTUtil.createToken(account.getAccountName(), account.getAccountLimit(), account.getAccountUserId());
                return Result.success(jwtToken);
            } else {
                return Result.fail(ErrorCode.REGISTER_ERRoR);
            }
        }

    }

    public Result<?> accountDelete(int accountId) {
        if (accountId > 0) {
            int a = mapper.accountDelete(accountId);
            if (a == 1) {
                return Result.success();
            }
        }
        return Result.fail(-1);
    }

    public Result<?> banAccount(Account account) {
        int a = mapper.banAccount(account);
        if (a > 0) {
            return Result.success();
        }
        return Result.fail(-1);
    }

    public int updatePassword(Account account) {
        account.setAccountPassword(Md5.EncoderByMd5(account.getAccountPassword()));
        int msg = mapper.updatePassword(account);
        return msg;
    }

    public boolean selectAddress(Account account) {
        Account msg = mapper.selectAddress(account);
        return msg != null;
    }

    public Result<?> wxLogin(String openId, String wxName, String wxImage, int limit) {
        List<Account> account = mapper.wxLogin(openId,limit);
        HashMap<String, String> map = new HashMap<>();

        if (account.size() == 0) {
            mapper.addAccount(openId, wxName, wxImage,limit);
            map.put("openId", openId);
            map.put("token", "");
        } else {
            for (Account newAccount : account) {
                if (newAccount.getAccountLimit() == limit) {
                    String jwtToken = JWTUtil.createToken(newAccount.getAccountName(), newAccount.getAccountLimit(), newAccount.getAccountUserId());
                    map.put("token", jwtToken);
                }
            }
            if (map.get("token") == null){
                map.put("token","");
            }
        }
        return Result.success(map);
    }
}
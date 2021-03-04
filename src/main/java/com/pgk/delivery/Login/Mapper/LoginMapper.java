package com.pgk.delivery.Login.Mapper;

import com.pgk.delivery.Login.Pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {


     Account login(String accountName, String accountPassword);

     List<Account> queryById(String accountName);

     Account queryByName(String accountName);

     List<Account> queryAll();

     int register(Account account);

    int accountDelete(int accountId);

    int banAccount(Account account);

    int addInformation (Account account);

    int selectUserId(Account account);

    int updatePassword(Account account);

    Account selectAddress(Account account);
}

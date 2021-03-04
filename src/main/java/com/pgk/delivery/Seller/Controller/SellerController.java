package com.pgk.delivery.Seller.Controller;


import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Seller.Pojo.Seller;
import com.pgk.delivery.Seller.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/Seller")
public class SellerController {
    @Autowired
    private SellerService service;


    @RequestMapping("/getSellerAddress.do")
    public Result<?> getSellerAddress(String accountName){
        Result<?> msg = service.getSellerAddress(accountName);
        return msg;
    }
    @RequestMapping("/updateSellerAddress.do")
    public Result<?> updateSellerAddress(@RequestBody Seller seller){
        Result<?> msg = service.updateSellerAddress(seller);
        return msg;
    }





}

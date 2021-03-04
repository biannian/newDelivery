package com.pgk.delivery.Buyer.Controller;

import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Buyer.Service.BuyerService;
import com.pgk.delivery.Model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Buyer")
public class BuyerController {
    @Autowired
    private BuyerService service;

    @RequestMapping("/getBuyerAddress.do")
    public Result<?> getBuyerAddress(String accountName){
        Result<?> msg = service.getBuyerAddress(accountName);
        return msg;
    }
    @RequestMapping("/updateAddress.do")
    public Result<?> updateAddress(@RequestBody Buyer buyer){
        Result<?> msg = service.updateAddress(buyer);
        return msg;
    }



}

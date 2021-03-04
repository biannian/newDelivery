package com.pgk.delivery.Buyer.Service.ServiceImpl;

import com.pgk.delivery.Buyer.Mapper.BuyerMapper;
import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BuyerService implements com.pgk.delivery.Buyer.Service.BuyerService {

    @Autowired
    private BuyerMapper mapper;


    @Override
    public Result<?> getBuyerAddress(String accountName) {
        Buyer buyer = mapper.getBuyerAddress(accountName);
        if (buyer.getBuyerName() == null ||buyer.getBuyerName().equals("")){
            return Result.fail(-1);
        }else {
            return Result.success(buyer);
        }

    }
    @Override
    public Result<?> updateAddress(Buyer buyer) {
            int msg = mapper.updateAddress(buyer);
            return Result.success(msg);

    }
}

package com.pgk.delivery.Seller.Service.ServiceImpl;

import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Seller.Mapper.SellerMapper;
import com.pgk.delivery.Seller.Pojo.Seller;
import com.pgk.delivery.Seller.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper mapper;

    @Override
    public Result<?> getSellerAddress(String accountName) {
        Seller seller = mapper.getSellerAddress(accountName);
        if (seller.getSellerName() == null){
            return Result.fail(-1);
        }else {
            return Result.success(seller);
        }
    }

    @Override
    public Result<?> updateSellerAddress(Seller seller) {
        int msg = mapper.updateSellerAddress(seller);
        return Result.success(msg);

    }
}

package com.pgk.delivery.Seller.Service;

import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Model.Result;
import com.pgk.delivery.Seller.Pojo.Seller;


public interface SellerService {
    Result<?> getSellerAddress(String accountName);

    Result<?> updateSellerAddress(Seller seller);
}

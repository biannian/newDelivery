package com.pgk.delivery.Seller.Mapper;

import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Seller.Pojo.Seller;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerMapper {
    Seller getSellerAddress(String accountName);

    int updateSellerAddress(Seller seller);
}

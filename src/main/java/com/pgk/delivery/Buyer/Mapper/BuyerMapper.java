package com.pgk.delivery.Buyer.Mapper;

import com.pgk.delivery.Buyer.Pojo.Buyer;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface BuyerMapper {
    Buyer getBuyerAddress(String accountName);

    int updateAddress(Buyer buyer);

    int addAddress(Buyer buyer);
}

package com.pgk.delivery.Buyer.Service;

import com.pgk.delivery.Buyer.Pojo.Buyer;
import com.pgk.delivery.Model.Result;

public interface BuyerService {
    Result<?> getBuyerAddress(String accountName);

    Result<?> updateAddress(Buyer buyer);
}

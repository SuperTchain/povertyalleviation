package com.lx.povertyalleviation.service.impl;

import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.utils.Result;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCarService implements com.lx.povertyalleviation.service.ShoppingCarService {
    @Override
    public Result addToShoppingCar(Product product) {
        Result result = new Result();
        return result;
    }
}

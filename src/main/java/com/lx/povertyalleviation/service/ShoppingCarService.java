package com.lx.povertyalleviation.service;

import com.lx.povertyalleviation.pojo.Product;
import com.lx.povertyalleviation.utils.Result;

public interface ShoppingCarService {

    /**
     * 添加到购物车
     * @param product
     * @return
     */
    Result addToShoppingCar(Product product);
}

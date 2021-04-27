package com.shoppingzone.service;

import com.shoppingzone.entity.Cart;

import java.util.List;

public interface CartService {

    Cart getcartById(int cartid);
    Cart updateCart(Cart cart);
    List<Cart> getallcarts();
    double cartTotal(Cart cart);
    Cart addCart(Cart cart);
}
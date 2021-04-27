package com.shoppingzone.service;

import com.shoppingzone.entity.Cart;
import com.shoppingzone.entity.Items;
import com.shoppingzone.exception.ResourceNotFoundException;
import com.shoppingzone.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    /**
     * This method returns all the carts present in the database.
     */
    @Override
    public List<Cart> getallcarts() {
        List<Cart> cartList = cartRepository.findAll();
        if (cartList == null) {
            throw new ResourceNotFoundException("No Carts available");
        }
        return cartList;
    }

    /**
     * This method returns cart based on it's Id.
     */
    @Override
    public Cart getcartById(int cartid) {
        Cart cart = cartRepository.findById(cartid).orElseThrow(() ->
                new ResourceNotFoundException("No Cart available with ID:- " + cartid));
        return cart;
    }

    /**
     * This method returns the total cart value.
     */
    @Override
    public double cartTotal(Cart cart) {
        double totalPrice = 0.0;
        double cartPrice = 0.0;
        int count = 0;
        for (Items items : cart.getItems()) {
            System.out.println(++count + " ");
            System.out.println("      ");
            System.out.println(items);
            System.out.println("Product total is: " + items.getPrice() * items.getQuantity());
            totalPrice += items.getPrice() * items.getQuantity();
        }
        cartPrice = cartPrice + totalPrice;
        return cartPrice;
    }

    /**
     * This method adds cart in the database.
     */
    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);

    }

    /**
     * This method is updating a cart and save it to database.
     */
    @Override
    public Cart updateCart(Cart cart) {
        cart.setTotalPrice(cartTotal(cart));
        cartRepository.save(cart);
        return cart;
    }
}
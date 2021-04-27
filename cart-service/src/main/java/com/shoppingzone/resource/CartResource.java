package com.shoppingzone.resource;

import com.shoppingzone.entity.Cart;
import com.shoppingzone.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartResource {

    @Autowired
    private CartService service;

    private Cart updatedCart;

    // Getting all carts available in database
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = service.getallcarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // Adding a new Cart to database
    @PostMapping("/{cartid}")
    public void addCart(@PathVariable int cartid) {
        Cart cart = new Cart();
        cart.setCartId(cartid);
        service.addCart(cart);
    }

    // Fetching a single cart using CartId
    @GetMapping("/{cartid}")
    public ResponseEntity<Cart> getCart(@PathVariable int cartid) {
        Cart cart = service.getcartById(cartid);
        System.out.println(cart);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Updating an existing cart
    @PutMapping
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        System.out.println("new products"+cart);
        updatedCart = service.updateCart(cart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
}

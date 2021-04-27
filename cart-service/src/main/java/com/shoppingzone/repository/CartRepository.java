package com.shoppingzone.repository;

import com.shoppingzone.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer> {

    public Cart findByCartId(int cartid);

}
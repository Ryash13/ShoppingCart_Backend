package com.shoppingzone.repository;

import com.shoppingzone.orders.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Orders, Integer> {

    List<Orders> findByCustomerId(Integer customerId);
    Orders findFirstByOrderByOrderIdDesc();
}

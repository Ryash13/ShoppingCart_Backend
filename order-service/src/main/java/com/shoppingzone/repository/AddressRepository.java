package com.shoppingzone.repository;

import com.shoppingzone.orders.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends MongoRepository<Address, Integer> {

    List<Address> findByCustomerId(int customerId);
}

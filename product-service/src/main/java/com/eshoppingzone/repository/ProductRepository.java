package com.eshoppingzone.repository;

import com.eshoppingzone.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

    Optional<Product> findByProductName(String productName);
    List<Product> findByCategory(String category);
    List<Product> findByProductType(String productType);
}
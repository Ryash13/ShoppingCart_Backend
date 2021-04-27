package com.eshoppingzone.service;

import com.eshoppingzone.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProducts(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(int productId);
    Optional<Product> getProductByName(String productName);
    Product updateProducts(Product product);
    void deleteProductById(int productId);
    List<Product> getProductByCategory(String category);
    List<Product> getProductByType(String productType);
}
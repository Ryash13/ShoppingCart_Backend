package com.eshoppingzone.service;

import com.eshoppingzone.entity.Product;
import com.eshoppingzone.exception.CategoryNotFound;
import com.eshoppingzone.exception.ProductTypeNotFound;
import com.eshoppingzone.exception.ResourceNotFoundException;
import com.eshoppingzone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProducts(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if (productList == null) {
            throw new ResourceNotFoundException("No Product available in shop");
        }
        return productList;
    }

    @Override
    public Optional<Product> getProductById(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product == null) {
            throw new ResourceNotFoundException("Product doesn't exists with Id:- " + productId);
        }
        return product;
    }

    @Override
    public Optional<Product> getProductByName(String productName) {
        Optional<Product> product = productRepository.findByProductName(productName);
        if (product == null) {
            throw new ResourceNotFoundException("No Product Available with name:- " + productName);
        }
        return product;
    }

    @Override
    public Product updateProducts(Product product) {
        Product found = productRepository.findById(product.getProductId()).orElseThrow(() ->
                new ResourceNotFoundException("Product doesn't exists"));
        found.setProductType(product.getProductType());
        found.setProductName(product.getProductName());
        found.setCategory(product.getCategory());
        found.setRating(product.getRating());
        found.setReview(product.getReview());
        found.setImage(product.getImage());
        found.setPrice(product.getPrice());
        found.setDescription(product.getDescription());
        found.setSpecification(product.getSpecification());
        return productRepository.save(found);

    }

    @Override
    public void deleteProductById(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product doesn't exists with Id:- " + productId));
        productRepository.delete(product);

    }

    @Override
    public List<Product> getProductByCategory(String category) {
        List<Product> productCategory = productRepository.findByCategory(category);
        if (productCategory == null) {
            throw new CategoryNotFound("Category doesn't exist");
        }
        return productCategory;
    }

    @Override
    public List<Product> getProductByType(String productType) {
        List<Product> product = productRepository.findByProductType(productType);
        if (product == null) {
            throw new ProductTypeNotFound("Invalid Product Type");
        }
        return product;
    }

}

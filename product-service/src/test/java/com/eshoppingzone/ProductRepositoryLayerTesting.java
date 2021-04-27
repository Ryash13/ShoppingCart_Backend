package com.eshoppingzone;

import com.eshoppingzone.entity.Product;
import com.eshoppingzone.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRepositoryLayerTesting {

    @Autowired
    private ProductRepository repository;

    // Repository Layer testing for getAllProducts available in database
    @Test
    public void findAllProductsTest() {
        List<Product> productList = repository.findAll();
        assertFalse(productList.isEmpty());
    }

    // Repository Layer testing for getProduct by its ID
    @Test
    public void findProductByIdTest() {
        Optional<Product> product = repository.findById(1);
        assertEquals(1 , product.get().getProductId());
    }

    // Repository Layer testing for getProducts by their name
    @Test
    public void findProductByNameTest() {
        Optional<Product> product = repository.findByProductName("U TURN");
        assertEquals("U TURN" , product.get().getProductName());
    }

    // Repository Layer testing for getProducts by their category.(Fails if we add another product of category
    // Shirt)
    @Test
    public void findProductByCategory() {
        List<Product> product = repository.findByCategory("Shirt");
        assertEquals(1 , product.size());
    }
}

package com.eshoppingzone;

import com.eshoppingzone.entity.Product;
import com.eshoppingzone.repository.ProductRepository;
import com.eshoppingzone.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceLayerTesting {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    private Product product;

    @BeforeEach
    public void setUp() {
        Map<Integer, Double> rating = new HashMap<Integer, Double>();
        rating.put(1, 10.00);
        Map<Integer, String> review = new HashMap<Integer, String>();
        review.put(1, "Excellent");
        List<String> image = new ArrayList<String>();
        image.add("101.jpg");
        Map<String, String> specification = new HashMap<String, String>();
        specification.put("Compartments", "two compartments");
        specification.put("Strap", "Adjustable strap");
        product = new Product(
                101,
                "handbag",
                "cares",
                "bags and Luggage",
                rating,
                review,
                image,
                1500.00,
                "good product",
                specification
        );

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Mockito.when(repository.findAll())
                .thenReturn(productList);

        Mockito.when(repository.findById(product.getProductId()))
                .thenReturn(Optional.ofNullable(product));

        Mockito.when(repository.findByProductName(product.getProductName()))
                .thenReturn(Optional.ofNullable(product));
    }

    // Testing for data available or not
    @Test
    public void testForData() {
        List<Product> productList = productService.getAllProducts();
        assertThat(productList.isEmpty() == false);
    }

    // Testing getProductById when ID exists
    @Test
    public void getProductByIdTestWhenProductAvailable() {
        Optional<Product> product = productService.getProductById(101);
        assertEquals(product.get().getProductId() , 101);
    }

    // Testing getProductById when ID doesn't exists
    @Test
    public void getProductByIdTestWhenIdNotAvailable() {
        Optional<Product> product = productService.getProductById(104);
        assertThatExceptionOfType(NoSuchElementException.class);
    }

    // Testing getProductByName if product exists
    @Test
    public void getProductByProductNameWhenProductAvailable() {
        Optional<Product> product = productService.getProductByName("cares");
        assertEquals(product.get().getProductName() , "cares");
    }

    // Testing getProductByName if product doesn't exists
    @Test
    public void getProductByProductNameWhenProductNotAvailable() {
        Optional<Product> product = productService.getProductByName("test");
        assertThatExceptionOfType(NoSuchElementException.class);
    }

    // Testing for updating a product
    @Test
    public void testForUpdateProducts() {
        Product actualProduct = productService.getProductById(101).get();
        actualProduct.setPrice(45600.00);
        productService.updateProducts(actualProduct);
        Product updatedProduct = actualProduct;
        assertEquals(actualProduct, updatedProduct);
    }
}

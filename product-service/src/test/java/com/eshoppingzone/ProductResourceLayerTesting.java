package com.eshoppingzone;

import com.eshoppingzone.entity.Product;
import com.eshoppingzone.resource.ProductResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductResourceLayerTesting {

    @Autowired
    private TestRestTemplate testRestTemplate;

    // Testing getProductById when ID exists
    @Test
    public void testForGetProductByIdForAvailableProductId() {
        ResponseEntity<ProductResource> responseEntity = testRestTemplate.getForEntity("/products/1",
                ProductResource.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    // Testing getProductById when ID Doesn't exists
    @Test
    public void testForGetProductByIdForUnAvailableProductId() {
        ResponseEntity<ProductResource> responseEntity = testRestTemplate.getForEntity("/products/1009",
                ProductResource.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    // Testing for Invalid Method Type Request
    @Test
    public void testForInvalidMethodTypeURL() {
        Product product = new Product();
        ResponseEntity<?> responseEntity = testRestTemplate.postForEntity("/products/1" , product , Product.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , responseEntity.getStatusCode());
    }

    // Testing for Invalid URL Request
    @Test
    public void invalidURLTest() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/products/productId" ,String.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR , responseEntity.getStatusCode());
    }
}

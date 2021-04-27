package com.shoppingzone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartResourceLayerTesting {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCarts() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/carts", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetCartById() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/carts/1000", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCartNotFound() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/cart/110", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetCartByIdBadRequest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/carts/products", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

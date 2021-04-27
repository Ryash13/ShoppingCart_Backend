package com.shoppingzone;

import com.shoppingzone.entity.Cart;
import com.shoppingzone.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartRepositoryLayerTesting {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void findAllCartsAvailable() throws Exception {
        List<Cart> cartList = cartRepository.findAll();
        assertFalse(cartList.isEmpty());
    }

    @Test
    public void findCartByIdWhenCartIdExists() throws Exception {
        Cart cart = cartRepository.findByCartId(1000);
        assertEquals(1000 , cart.getCartId());
    }

    @Test
    public void findCartByIdWhenCartIdDoesNotExists() throws Exception {
        Cart cart = cartRepository.findByCartId(1);
        assertThatExceptionOfType(NullPointerException.class);
    }
}

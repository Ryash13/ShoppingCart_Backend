package com.shoppingzone;

import com.shoppingzone.entity.Cart;
import com.shoppingzone.entity.Items;
import com.shoppingzone.repository.CartRepository;
import com.shoppingzone.resource.CartResource;
import com.shoppingzone.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartServiceLayerTesting {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @BeforeEach
    public void setUp() {
        Items item = new Items("iPhone10" , 50000 ,1);
        List<Items> itemsList = new ArrayList<>();
        itemsList.add(item);

        Cart cart = new Cart(
                101,
                itemsList
        );

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        Mockito.when(cartRepository.findAll())
                .thenReturn(cartList);

        Mockito.when(cartRepository.findByCartId(cart.getCartId()))
                .thenReturn(cart);
    }

    @Test
    public void testForData() {
        List<Cart> cartList = cartService.getallcarts();
        assertThat(cartList.isEmpty() == false);
    }

    @Test
    public void cartNotFoundTest() {
        Cart cart = cartRepository.findByCartId(104);
        assertThatExceptionOfType(NullPointerException.class);
    }

    @Test
    public void testUpdateCart() throws Exception {

        Items item = new Items("Samsung-Galaxy", 5000, 3);
        List<Items> itemsSet = new ArrayList<Items>();
        itemsSet.add(item);
        Cart actual = cartRepository.findByCartId(101);
        actual.setItems(itemsSet);
        Cart expected = new Cart(101, itemsSet);
        assertEquals(expected.getItems(), actual.getItems());
    }

}

package com.shoppingzone;

import com.shoppingzone.orders.Orders;
import com.shoppingzone.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTesting {

    @Autowired
    OrderService orderService;

    // Fetches database for counting the no or records
    @Test
    public void getAllOrdersTest() {
        List<Orders> ordersList = orderService.getAllOrders();
        assertEquals(false, ordersList.isEmpty());
    }

    // As per the order increases this test case will fail because we have used static count of orders from database
    @Test
    public void numberOfOrdersAvailableTest() {
        List<Orders> ordersList = orderService.getAllOrders();
        assertEquals(2 , ordersList.size());
    }
}

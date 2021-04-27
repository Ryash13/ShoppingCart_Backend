package com.shoppingzone.service;

import com.shoppingzone.cart.Cart;
import com.shoppingzone.orders.Address;
import com.shoppingzone.orders.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Orders> getAllOrders();
    void placeOrder(Cart cart);
    String changeStatus(String orderStatus, int orderId);
    void deleteOrder(int orderId);
    List<Orders> getOrderByCustomerId(int customerId);
    void storeAddress(Address address);
    List<Address> getAddressByCustomerId(int customerId);
    List<Address> getAllAddress();
    Orders getOrderById();
    Optional<Orders> getOrderById(int orderId);
    void onlinePayment(Cart cart);
}

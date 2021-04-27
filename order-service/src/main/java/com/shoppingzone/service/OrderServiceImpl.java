package com.shoppingzone.service;

import com.shoppingzone.cart.Cart;
import com.shoppingzone.cart.Items;
import com.shoppingzone.exception.ResourceNotFoundException;
import com.shoppingzone.orders.Address;
import com.shoppingzone.orders.Orders;
import com.shoppingzone.repository.AddressRepository;
import com.shoppingzone.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    private static int orderId = 1;
    int customerId;

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public double cartTotal(Cart cart) {
        double totalPrice = 0.0;
        double cartPrice = 0.0;
        int count = 0;
        for (Items items : cart.getItems()) {
            System.out.println(++count + " ");
            System.out.println("      ");
            System.out.println(items);
            System.out.println("Product total is: " + items.getPrice() * items.getQuantity());
            totalPrice += items.getPrice() * items.getQuantity();
        }
        cartPrice = cartPrice + totalPrice;
        return cartPrice;
    }

    @Override
    public void placeOrder(Cart cart) {
        int totalquantity = 0;
        customerId = cart.getCartId();
        Orders orders = new Orders();
        orders.setAddress(orders.getAddress());
        orders.setAmmountPaid(cartTotal(cart));
        orders.setCustomerId(cart.getCartId());
        orders.setModeOfPayment("Cash on Delivery");
        orders.setOrderStatus("Delivered");
        List<Items> items = cart.getItems();
        for (Items item : items) {
            totalquantity = totalquantity + item.getQuantity();
        }
        orders.setQuantity(totalquantity);
        orders.setOrderDate(LocalDate.now());
        orders.setOrderId(orderId++);
        orderRepository.save(orders);
    }

    @Override
    public String changeStatus(String orderStatus, int orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Order ID:- " + orderId));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return orderStatus;
    }

    @Override
    public void deleteOrder(int orderId) {
        Orders delOrder = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Order ID:- " + orderId));
        orderRepository.delete(delOrder);
    }

    @Override
    public List<Orders> getOrderByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public void storeAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressByCustomerId(int customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Orders getOrderById() {
        return orderRepository.findFirstByOrderByOrderIdDesc();
    }

    @Override
    public Optional<Orders> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void onlinePayment(Cart cart) {
        int totalquantity = 0;
        customerId = cart.getCartId();
        Orders orders = new Orders();
        orders.setAddress(orders.getAddress());
        orders.setAmmountPaid(cartTotal(cart));
        orders.setCustomerId(cart.getCartId());
        orders.setModeOfPayment("Wallet Payment");
        orders.setOrderStatus("Delivered");
        List<Items> items = cart.getItems();
        for (Items item : items) {
            totalquantity = totalquantity + item.getQuantity();
        }
        orders.setQuantity(totalquantity);
        orders.setOrderDate(LocalDate.now());
        orders.setOrderId(orderId++);
        orderRepository.save(orders);
    }
}
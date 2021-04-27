package com.shoppingzone.resource;

import com.shoppingzone.cart.Cart;
import com.shoppingzone.orders.Address;
import com.shoppingzone.orders.Orders;
import com.shoppingzone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/address")
    public List<Address> getAllAddress() {
        return orderService.getAllAddress();
    }

    @GetMapping("/order/{customerId}")
    public List<Orders> getOrderByCustomerId(@PathVariable int customerId) {
        List<Orders> orders = orderService.getOrderByCustomerId(customerId);
        // Orders orders =optionalOrders.get();
        return orders;
    }

    @GetMapping("/{customerId}")
    public List<Address> getAddressByCustomerId(@PathVariable int customerId) {
        List<Address> address = orderService.getAddressByCustomerId(customerId);
        return address;
    }

    @GetMapping("/orderId")
    public Orders findMAXByOrderId() {
        Orders orders = orderService.getOrderById();
        return orders;
    }

    @PostMapping("/cod")
    public void placeOrder(@RequestBody Cart cart) {
        orderService.placeOrder(cart);
    }

    @PostMapping("/onlinepay")
    public void onlinePayment(@RequestBody Cart cart) {
        orderService.onlinePayment(cart);
    }

    @PostMapping("/address")
    public void storeAddress(@RequestBody Address address) {
        orderService.storeAddress(address);
    }

    @PutMapping("/{orderId}")
    public void changeOrderStatus(@RequestParam String orderStatus, @PathVariable int orderId) {
        orderService.changeStatus(orderStatus, orderId);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        Optional<Orders> order = orderService.getOrderById(orderId);
        if (!order.isPresent())
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        orderService.deleteOrder(orderId);
        return new ResponseEntity(HttpStatus.OK);

    }
}
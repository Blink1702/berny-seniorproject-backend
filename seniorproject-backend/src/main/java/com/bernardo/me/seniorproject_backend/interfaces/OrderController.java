package com.bernardo.me.seniorproject_backend.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.me.seniorproject_backend.entities.Orders;
import com.bernardo.me.seniorproject_backend.interfaces.dtos.OrdersDTO;
import com.bernardo.me.seniorproject_backend.security.PantryUserDetails;
import com.bernardo.me.seniorproject_backend.security.WrongUserException;
import com.bernardo.me.seniorproject_backend.services.UserService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    private UserService us;

    public OrderController(UserService us) {
        this.us = us;
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> save(Authentication authentication, @RequestBody OrdersDTO order) {

        PantryUserDetails details = (PantryUserDetails) authentication.getPrincipal();

        order.setUser(details.getUsername());
        String key;
        try {
            key = us.saveOrder(UUID.fromString(details.getUsername()), order);
            order.setOrderid(key);
        } catch (WrongUserException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(order);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        List<Orders> orders = us.findOrders();
        List<OrdersDTO> results = new ArrayList<OrdersDTO>();
        for (Orders o : orders) {
            results.add(new OrdersDTO(o));
        }
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/userOrder")
    public ResponseEntity<List<OrdersDTO>> getOrdersByUser(Authentication authentication) {
        PantryUserDetails details = (PantryUserDetails) authentication.getPrincipal();
        UUID id = UUID.fromString(details.getUsername());
        List<Orders> orders = us.findOrderByUser(id);
        List<OrdersDTO> results = new ArrayList<OrdersDTO>();
        for (Orders o : orders) {
            results.add(new OrdersDTO(o));
        }
        return ResponseEntity.ok().body(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable UUID id) {
        Orders order = us.findOrderById(id);
        OrdersDTO result = new OrdersDTO(order);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/{id}/fulfill")
    public ResponseEntity<OrdersDTO> fulfillOrder(@PathVariable UUID id) {
        Orders order = us.fulfillOrders(id);
        OrdersDTO result = new OrdersDTO(order);
        return ResponseEntity.ok().body(result);
    }

}
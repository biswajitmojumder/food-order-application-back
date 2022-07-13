package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.OrderStatus;
import com.group.foodorderapplicationback.model.Orders;
import com.group.foodorderapplicationback.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping(value = "/orders/all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        return ResponseEntity.ok().body(ordersService.findAll());
    }

    @GetMapping(value = "/orders", params = "status")
    public ResponseEntity<List<Orders>> getAllOrdersByOrderStatus(@RequestParam OrderStatus status) {
        return ResponseEntity.ok().body(ordersService.findByOrderStatus(status));
    }

    @PostMapping(value = "/orders/new", params = "userId")
    public ResponseEntity<Orders> newOrder(@RequestParam Long userId, @RequestBody Orders order) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/orders/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(ordersService.insertOrder(userId, order));
    }

    @PutMapping(value = "/orders/next-status", params = "id")
    public ResponseEntity<Orders> nextStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.nextStatus(id));
    }

    @PutMapping(value = "/orders/set-delivered-status", params = "id")
    public ResponseEntity<Orders> setDeliveredStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.setDeliveredStatus(id));
    }

    @PutMapping(value = "/orders/set-rejected-status", params = "id")
    public ResponseEntity<Orders> setRejectedStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.setRejectedStatus(id));
    }
}

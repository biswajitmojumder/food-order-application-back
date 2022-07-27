package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Address;
import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.OrderStatus;
import com.group.foodorderapplicationback.model.Orders;
import com.group.foodorderapplicationback.service.OrdersService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
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
        return ResponseEntity.ok().body(ordersService.findAllByOrderStatus(status));
    }

    @PostMapping(value = "/orders/new", params = "userId")
    public ResponseEntity<Orders> newOrder(@RequestParam Long userId, @RequestBody Orders order) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/orders/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(ordersService.insertOrder(userId, order));
    }

    @PostMapping(value = "/orders/new-authenticated")
    public ResponseEntity<Orders> newOrder(HttpServletRequest request, @RequestBody Orders order) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/orders/new-authenticated").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(ordersService.insertOrderForAuthenticatedUser(request, order));
    }

    @PutMapping(value = "/orders/accept-order")
    public ResponseEntity<Orders> setAcceptedStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.setAcceptedStatus(id));
    }

    @PutMapping(value = "/orders/prepare-order")
    public ResponseEntity<Orders> setPreparingStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.setPreparingStatus(id));
    }

    @PutMapping(value = "/orders/on-the-way")
    public ResponseEntity<Orders> setOnTheWayStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.setOnTheWay(id));
    }

    @PutMapping(value = "/orders/set-delivered-status", params = "id")
    public ResponseEntity<Orders> setDeliveredStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.setDeliveredStatus(id));
    }

    @PutMapping(value = "/orders/set-rejected-status", params = "id")
    public ResponseEntity<Orders> setRejectedStatus(@RequestParam Long id) {
        return ResponseEntity.ok().body(ordersService.setRejectedStatus(id));
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderMap {
        List<Food> foodList;
        Address address;
    }
}

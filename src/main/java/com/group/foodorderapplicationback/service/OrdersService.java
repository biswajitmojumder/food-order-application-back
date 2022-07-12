package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.OrderStatus;
import com.group.foodorderapplicationback.model.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll();
    List<Orders> findByOrderStatus(OrderStatus orderStatus);
    Orders insertOrder(Long userId, Orders order);
    Orders nextStatus(Long orderId);
    Orders setDeliveredStatus(Long orderId);
}

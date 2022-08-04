package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.controller.OrdersController;
import com.group.foodorderapplicationback.model.OrderStatus;
import com.group.foodorderapplicationback.model.Orders;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface OrdersService {

    List<Orders> findAll();
    List<Orders> findAllAcceptedOrders();
    List<Orders> findAllByOrderStatus(OrderStatus orderStatus);
    Orders insertOrder(Long userId, Orders order);
    Orders insertOrderForAuthenticatedUser(HttpServletRequest request, Orders order);
    Orders setAcceptedStatus(Long orderId);
    Orders setPreparingStatus(Long orderId);
    Orders setPickReadyStatus(Long orderId);
    Orders setOnTheWay(Long orderId);
    Orders setDeliveredStatus(Long orderId);
    Orders setRejectedStatus(Long orderId);
}

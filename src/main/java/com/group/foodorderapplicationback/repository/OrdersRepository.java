package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.OrderStatus;
import com.group.foodorderapplicationback.model.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
    List<Orders> findAllByOrderStatus(OrderStatus orderStatus);
    List<Orders> findAllByOrderByDateTimeDesc();
}

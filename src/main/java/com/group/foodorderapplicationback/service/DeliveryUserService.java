package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.model.Orders;
import com.group.foodorderapplicationback.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DeliveryUserService {
    List<DeliveryUser> findAll();
    DeliveryUser save(DeliveryUser deliveryUser);
    void deleteById(Long id);
    DeliveryUser getDeliveryUserInfo(HttpServletRequest request);
    Orders takeOrder(HttpServletRequest request, Long id);
    Orders getActiveOrder(HttpServletRequest request);
}

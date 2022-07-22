package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DeliveryUserService {
    List<DeliveryUser> findAll();
    DeliveryUser save(DeliveryUser deliveryUser);
    DeliveryUser getDeliveryUser(String username);
    void deleteById(Long id);
    DeliveryUser getDeliveryUserInfo(HttpServletRequest request);
}

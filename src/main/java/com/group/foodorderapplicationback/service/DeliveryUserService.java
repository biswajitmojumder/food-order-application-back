package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.DeliveryUser;

import java.util.List;

public interface DeliveryUserService {
    List<DeliveryUser> findAll();
    DeliveryUser save(DeliveryUser deliveryUser);
    DeliveryUser getDeliveryUser(String username);
    void deleteById(Long id);
}

package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.DeliveryUser;

public interface DeliveryUserService {
    DeliveryUser save(DeliveryUser deliveryUser);
    DeliveryUser getDeliveryUser(String username);
    void deleteById(Long id);
}

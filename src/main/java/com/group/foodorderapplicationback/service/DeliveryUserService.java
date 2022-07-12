package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.DeliveryUser;

public interface DeliveryUserService {
    DeliveryUser saveDeliveryUser(DeliveryUser deliveryUser);
    DeliveryUser getDeliveryUser(String username);
}

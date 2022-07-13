package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.DeliveryUser;
import org.springframework.data.repository.CrudRepository;

public interface DeliveryUserRepository extends CrudRepository<DeliveryUser, Long> {
    DeliveryUser findByUsername(String username);
}

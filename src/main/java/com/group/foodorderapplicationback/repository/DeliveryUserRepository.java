package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.DeliveryUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryUserRepository extends CrudRepository<DeliveryUser, Long> {
    List<DeliveryUser> findAll();
    DeliveryUser findByUsername(String username);
}

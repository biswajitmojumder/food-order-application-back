package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {
    Manager findByUsername(String username);
}

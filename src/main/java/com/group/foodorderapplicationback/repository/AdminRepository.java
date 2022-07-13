package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByUsername(String username);
}

package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Staff;
import com.group.foodorderapplicationback.model.User;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, Long> {
    Staff findByUsername(String username);
}

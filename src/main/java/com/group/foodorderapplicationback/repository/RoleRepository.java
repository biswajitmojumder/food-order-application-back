package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}

package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Account, Long> {
    User findByUsername(String username);
}

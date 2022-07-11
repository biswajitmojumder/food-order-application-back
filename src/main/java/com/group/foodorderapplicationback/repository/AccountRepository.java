package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);
}

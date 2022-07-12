package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Account;

public interface ManagerService {
    Account saveManager(Account account);
    Account getManager(String username);
}

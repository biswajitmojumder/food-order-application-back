package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.Role;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    Role saveRole(Role role);
    void addRoleToAccount(String username, String roleName);
    Account getAccount(String username);
    List<Account> getAccounts();  //Use pagination
}

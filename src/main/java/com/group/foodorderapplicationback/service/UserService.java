package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.model.User;

import java.util.List;

public interface UserService {
//    Account saveAccount(Account account);
//    Role saveRole(Role role);
//    void addRoleToAccount(String username, String roleName);
//    Account getAccount(String username);
//    List<Account> getAccounts();  //Use pagination
    User saveUser(User user);
    User getUser(String username);
    void deleteById(Long id);
}

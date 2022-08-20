package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AccountService {
    void refreshToken(HttpServletRequest request, HttpServletResponse response);
    Role saveRole(Role role);
}

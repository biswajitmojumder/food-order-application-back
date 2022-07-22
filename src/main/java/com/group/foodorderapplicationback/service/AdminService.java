package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Admin;
import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.model.User;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    Admin save(Admin admin);
    Admin getAdmin(String username);
    void deleteById(Long id);
    Admin getAdminInfo(HttpServletRequest request);
}

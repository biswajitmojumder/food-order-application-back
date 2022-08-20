package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    Admin save(Admin admin);
    void deleteById(Long id);
    Admin getAdminInfo(HttpServletRequest request);
    Admin update(HttpServletRequest request, Admin admin);
}

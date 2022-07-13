package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Admin;
import com.group.foodorderapplicationback.model.DeliveryUser;

public interface AdminService {
    Admin save(Admin admin);
    Admin getAdmin(String username);
    void deleteById(Long id);
}

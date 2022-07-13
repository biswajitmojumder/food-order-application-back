package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Staff;

public interface StaffService {
    Staff save(Staff account);
    Staff getStaff(String username);
    void deleteById(Long id);
}

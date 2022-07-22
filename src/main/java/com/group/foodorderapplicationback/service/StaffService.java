package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> findAll();
    Staff save(Staff account);
    Staff getStaff(String username);
    void deleteById(Long id);
}

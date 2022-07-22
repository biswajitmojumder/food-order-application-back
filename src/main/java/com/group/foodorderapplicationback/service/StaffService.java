package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Staff;
import com.group.foodorderapplicationback.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StaffService {
    List<Staff> findAll();
    Staff save(Staff account);
    Staff getStaff(String username);
    void deleteById(Long id);

    Staff getStaffInfo(HttpServletRequest request);
}

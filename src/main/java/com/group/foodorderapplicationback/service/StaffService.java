package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Staff;
import com.group.foodorderapplicationback.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StaffService {
    List<Staff> findAll();
    Staff save(Staff staff);
    void deleteById(Long id);
    Staff update(Staff staff);
    Staff updateAuthenticated(HttpServletRequest request, Staff staff);
    Staff getStaffInfo(HttpServletRequest request);
}

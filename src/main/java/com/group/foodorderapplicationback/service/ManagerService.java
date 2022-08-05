package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Manager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ManagerService {
    List<Manager> findAll();
    Manager save(Manager manager);
    Manager getManager(String username);
    void deleteById(Long id);
    Manager getManagerInfo(HttpServletRequest request);
    Manager update(Manager manager);
    Manager updateAuthenticated(HttpServletRequest request, Manager manager);
}

package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Manager;
import com.group.foodorderapplicationback.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ManagerService {
    List<Manager> findAll();
    Manager save(Manager manager);
    Manager getManager(String username);
    void deleteById(Long id);
    Manager getManagerInfo(HttpServletRequest request);
}

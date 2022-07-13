package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Manager;

public interface ManagerService {
    Manager save(Manager manager);
    Manager getManager(String username);
    void deleteById(Long id);
}

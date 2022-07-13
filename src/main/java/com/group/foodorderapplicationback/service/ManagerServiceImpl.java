package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Manager;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.repository.ManagerRepository;
import com.group.foodorderapplicationback.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Manager save(Manager manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));

        Role role = roleRepository.findByName("ROLE_MANAGER");

        manager.getRoleList().add(role);

        return managerRepository.save(manager);
    }

    @Override
    public Manager getManager(String username) {
        return managerRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }
}

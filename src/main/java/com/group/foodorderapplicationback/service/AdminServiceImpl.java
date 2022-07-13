package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Admin;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.repository.AdminRepository;
import com.group.foodorderapplicationback.repository.DeliveryUserRepository;
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
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin save(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");

        admin.getRoleList().add(role);

        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdmin(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
}

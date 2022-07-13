package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.model.Staff;
import com.group.foodorderapplicationback.repository.RoleRepository;
import com.group.foodorderapplicationback.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Staff save(Staff staff) {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));

        Role role = roleRepository.findByName("ROLE_STAFF");

        staff.getRoleList().add(role);

        return staffRepository.save(staff);
    }

    @Override
    public Staff getStaff(String username) {
        return staffRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }
}

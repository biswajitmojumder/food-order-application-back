package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.repository.AccountRepository;
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
public class StaffServiceImpl implements StaffService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account saveStaff(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Role role = roleRepository.findByName("ROLE_STAFF");

        account.getRoleList().add(role);

        return accountRepository.save(account);
    }
}

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
public class ManagerServiceImpl implements ManagerService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account saveManager(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Role role = roleRepository.findByName("ROLE_MANAGER");

        account.getRoleList().add(role);

        return accountRepository.save(account);
    }

    @Override
    public Account getManager(String username) {
        return null;
    }
}

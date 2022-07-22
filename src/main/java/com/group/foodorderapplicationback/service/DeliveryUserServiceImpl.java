package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.repository.DeliveryUserRepository;
import com.group.foodorderapplicationback.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeliveryUserServiceImpl implements DeliveryUserService {

    private final DeliveryUserRepository deliveryUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<DeliveryUser> findAll() {
        return deliveryUserRepository.findAll();
    }

    @Override
    public DeliveryUser save(DeliveryUser deliveryUser) {
        deliveryUser.setPassword(passwordEncoder.encode(deliveryUser.getPassword()));

        Role role = roleRepository.findByName("ROLE_DELIVERY_USER");

        deliveryUser.getRoleList().add(role);

        return deliveryUserRepository.save(deliveryUser);
    }

    @Override
    public DeliveryUser getDeliveryUser(String username) {
        return deliveryUserRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        deliveryUserRepository.deleteById(id);
    }
}

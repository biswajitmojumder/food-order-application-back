package com.group.foodorderapplicationback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group.foodorderapplicationback.model.Admin;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.model.User;
import com.group.foodorderapplicationback.repository.AdminRepository;
import com.group.foodorderapplicationback.repository.DeliveryUserRepository;
import com.group.foodorderapplicationback.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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

    @Override
    public Admin getAdminInfo(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());   //debug secret - same as authentication
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        log.info("Getting user info for authorized admin: {" + decodedJWT.getSubject() + "}");

        return adminRepository.findByUsername(decodedJWT.getSubject());
    }
}

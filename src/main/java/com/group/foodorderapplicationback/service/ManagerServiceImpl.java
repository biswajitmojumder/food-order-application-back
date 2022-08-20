package com.group.foodorderapplicationback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group.foodorderapplicationback.model.Admin;
import com.group.foodorderapplicationback.model.Manager;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.repository.ManagerRepository;
import com.group.foodorderapplicationback.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Manager> findAll() {
        return (List<Manager>) managerRepository.findAll();
    }

    @Override
    public Manager save(Manager manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));

        Role role = roleRepository.findByName("ROLE_MANAGER");

        manager.getRoleList().add(role);

        return managerRepository.save(manager);
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public Manager getManagerInfo(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());   //debug secret - same as authentication
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        log.info("Getting manager info for authorized manager: {" + decodedJWT.getSubject() + "}");

        return managerRepository.findByUsername(decodedJWT.getSubject());
    }

    @Override
    public Manager update(Manager manager) {
        Manager dbManager = managerRepository.findById(manager.getId()).get();

        dbManager.setFirstName(manager.getFirstName());
        dbManager.setLastName(manager.getLastName());
        dbManager.setEmail(manager.getEmail());

        if(manager.getPassword() != null && manager.getPassword().length() > 0) {
            dbManager.setPassword(passwordEncoder.encode(manager.getPassword()));
        }

        return managerRepository.save(dbManager);
    }

    @Override
    public Manager updateAuthenticated(HttpServletRequest request, Manager manager) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());   //debug secret - same as authentication
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        log.info("Updating manager: {" + decodedJWT.getSubject() + "}");

        Manager dbManager = managerRepository.findByUsername(decodedJWT.getSubject());

        dbManager.setFirstName(manager.getFirstName());
        dbManager.setLastName(manager.getLastName());
        dbManager.setEmail(manager.getEmail());

        if(manager.getPassword() != null) {
            dbManager.setPassword(passwordEncoder.encode(manager.getPassword()));
        }

        return managerRepository.save(dbManager);
    }
}

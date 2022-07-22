package com.group.foodorderapplicationback.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.repository.DeliveryUserRepository;
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

    @Override
    public DeliveryUser getDeliveryUserInfo(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());   //debug secret - same as authentication
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        log.info("Getting delivery user info for authorized delivery user: {" + decodedJWT.getSubject() + "}");

        return deliveryUserRepository.findByUsername(decodedJWT.getSubject());
    }
}

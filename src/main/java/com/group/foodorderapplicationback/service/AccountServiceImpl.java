package com.group.foodorderapplicationback.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.foodorderapplicationback.config.UserPrincipal;
import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.repository.AccountRepository;
import com.group.foodorderapplicationback.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor    //dependency injection
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);  //Get the refresh token
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());

                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());   //debug secret - same as authentication
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);

                String accessToken = JWT.create()
                        .withSubject(decodedJWT.getSubject())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", decodedJWT.getClaim("roles").asList(String.class))
                        .sign(algorithm);

                //Same refresh token

                //============ Map
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }
            catch (Exception e) {
                //Log the error
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                try {
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if(account == null) {
            log.error("Account with username {" + username + "} not found!");
            throw new UsernameNotFoundException("Username not found!");
        }

        log.info("Account with username {" + username + "} found!");

        UserPrincipal userPrincipal = new UserPrincipal(account);
        return userPrincipal;
    }
}

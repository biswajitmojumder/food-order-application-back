package com.group.foodorderapplicationback.config;

import com.group.foodorderapplicationback.filter.CustomAuthenticationFilter;
import com.group.foodorderapplicationback.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Config
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //ROLES: ROLE_USER, ROLE_DELIVERY_USER, ROLE_ADMIN, ROLE_MANAGER, ROLE_STAFF

        //Account endpoints
        http.authorizeRequests().antMatchers("/login", "/token/refresh").permitAll();

        //Image Endpoints
        http.authorizeRequests().antMatchers("/image/get").permitAll();
        http.authorizeRequests().antMatchers("/upload/image").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");

        //Orders Endpoints
        http.authorizeRequests().antMatchers("/orders/all", "/orders/accepted","/orders").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STAFF");
        http.authorizeRequests().antMatchers("/orders/accepted").hasAnyAuthority("ROLE_DELIVERY_USER");
        http.authorizeRequests().antMatchers("/orders/new-authenticated").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/orders/accept-order", "/orders/prepare-order", "/orders/pick-ready", "/orders/set-rejected-status").hasAnyAuthority("ROLE_STAFF");
        http.authorizeRequests().antMatchers("/orders/set-delivered-status").hasAnyAuthority("ROLE_DELIVERY_USER");
        http.authorizeRequests().antMatchers("/orders/on-the-way").hasAnyAuthority("ROLE_STAFF", "ROLE_DELIVERY_USER");

        //Food Endpoints
        http.authorizeRequests().antMatchers(GET, "/food**", "/food/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/food**", "/food/**").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT, "/food**", "/food/**").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(DELETE, "/food**", "/food/**").hasAnyAuthority("ROLE_MANAGER");

        //Restaurant Endpoints
        http.authorizeRequests().antMatchers(GET, "/restaurant/get", "/restaurant/get-all").permitAll();
        http.authorizeRequests().antMatchers(POST, "/restaurant/new").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT, "/restaurant/update").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(DELETE, "/restaurant/delete").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");

        //Admin Endpoints
        http.authorizeRequests().antMatchers(GET,"/admin/get-admin-info").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/admin/update").hasAnyAuthority("ROLE_ADMIN");

        //Manager Endpoints
        http.authorizeRequests().antMatchers(GET,"/admin/get-manager-info").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(GET,"/manager/get-all").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/manager/new").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/manager/update").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/manager/delete").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/manager/update-authenticated").hasAnyAuthority("ROLE_MANAGER");

        //User Endpoints
        http.authorizeRequests().antMatchers(GET,"/user/get-user-info").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET,"/user/get-all", "/user/search").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET,"/user/get-favorite-food", "/user/get-order-history").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(PUT,"/user/update", "/user/add-food-to-favorites").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(DELETE,"/user/remove-food-from-favourites").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(DELETE,"/user/delete").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/user/new").permitAll();

        //Staff Endpoints
        http.authorizeRequests().antMatchers(GET,"/staff/get-staff-info").hasAnyAuthority("ROLE_STAFF");
        http.authorizeRequests().antMatchers(GET,"/staff/get-all").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(POST,"/staff/new").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT,"/staff/update").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT,"/staff/update-authenticated").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers(DELETE,"/staff/delete").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");

        //Delivery User Endpoints
        http.authorizeRequests().antMatchers(GET,"/delivery-user/get-delivery-user-info", "/delivery-user/get-order-history", "/delivery-user/active-order").hasAnyAuthority("ROLE_DELIVERY_USER");
        http.authorizeRequests().antMatchers(GET,"/delivery-user/get-all").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(POST,"/delivery-user/new").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT,"/delivery-user/update").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");
        http.authorizeRequests().antMatchers(PUT,"/delivery-user/update-authenticated", "/delivery-user/take-order").hasAnyAuthority("ROLE_DELIVERY_USER");
        http.authorizeRequests().antMatchers(DELETE,"/delivery-user/delete").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER");

        //Websocket
        http.authorizeRequests().antMatchers("/websocket/**").permitAll();

        //Block remaining endpoints
        http.authorizeRequests().antMatchers("**").hasAnyAuthority("ROLE_ADMIN");

        //Filters
        http.addFilter(new CustomAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);  //! Before everything

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }
}

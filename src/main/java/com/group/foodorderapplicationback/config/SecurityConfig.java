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

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login", "/token/refresh", "/user/save").permitAll(); // open for everyone
        http.authorizeRequests().antMatchers(GET, "/food**", "/food/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
//        http.authorizeRequests().anyRequest().authenticated(); // everyone -> authenticated

        http.authorizeRequests().antMatchers(GET, "**").permitAll(); //ALLOW EVERYTHING - DEBUG

//        http.authorizeRequests().anyRequest().permitAll();

        http.addFilter(new CustomAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);  //! Before everything

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }
}

package com.group.foodorderapplicationback;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.Role;
import com.group.foodorderapplicationback.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FoodOrderApplicationBackApplication {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderApplicationBackApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AccountService accountService) {
		return args -> {
			accountService.saveRole(new Role(null, "ROLE_USER", null));
			accountService.saveRole(new Role(null, "ROLE_ADMIN", null));
			accountService.saveRole(new Role(null, "ROLE_MANAGER", null));

			accountService.saveAccount(new Account(null, "", "", "User_1", "user_1", "password", null));
			accountService.saveAccount(new Account(null, "", "", "User_2", "user_2", "password", null));
			accountService.saveAccount(new Account(null, "", "", "User_3", "user_3", "password", null));

			accountService.addRoleToAccount("user_1", "ROLE_USER");
			accountService.addRoleToAccount("user_2", "ROLE_MANAGER");
			accountService.addRoleToAccount("user_3", "ROLE_ADMIN");
			accountService.addRoleToAccount("user_3", "ROLE_USER");
		};
	}
}

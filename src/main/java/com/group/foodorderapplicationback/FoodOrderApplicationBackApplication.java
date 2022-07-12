package com.group.foodorderapplicationback;

import com.group.foodorderapplicationback.model.*;
import com.group.foodorderapplicationback.service.AccountService;
import com.group.foodorderapplicationback.service.FoodService;
import com.group.foodorderapplicationback.service.RestaurantService;
import com.group.foodorderapplicationback.service.UserService;
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
	CommandLineRunner run(AccountService accountService,
						  UserService userService,
						  FoodService foodService,
						  RestaurantService restaurantService) {
		return args -> {
			//region Roles + Admin account
			accountService.saveRole(new Role(null, "ROLE_USER", null));
			accountService.saveRole(new Role(null, "ROLE_DELIVERY_USER", null));
			accountService.saveRole(new Role(null, "ROLE_ADMIN", null));
			accountService.saveRole(new Role(null, "ROLE_MANAGER", null));
			accountService.saveRole(new Role(null, "ROLE_STAFF", null));

			accountService.saveAccount(new Account(null, "Admin", "User", "admin@email.com", "admin", "password", null));
			accountService.addRoleToAccount("admin", "ROLE_ADMIN");
			//endregion

			//region Test user
			User user = new User();
			user.setUsername("user_1");
			user.setPassword("password");
			userService.saveUser(user);
			//endregion

			//region Food
			Food food_1 = new Food();
			food_1.setName("Shaorma");

			Food food_2 = new Food();
			food_2.setName("Pizza Quatro Stagioni");

			Food food_3 = new Food();
			food_3.setName("Pizza Diavola");

			Food food_4 = new Food();
			food_4.setName("Burger One");

			Food food_5 = new Food();
			food_5.setName("Burger Cheese");

			foodService.save(food_1);
			foodService.save(food_2);
			foodService.save(food_3);
			foodService.save(food_4);
			foodService.save(food_5);
			//endregion

			//region Restaurant
			Restaurant restaurant_1 = new Restaurant();
			restaurant_1.setName("Restaurantul 1");

			Restaurant restaurant_2 = new Restaurant();
			restaurant_2.setName("Restaurantul 2");

			restaurantService.save(restaurant_1);
			restaurantService.save(restaurant_2);
			//endregion
		};
	}
}

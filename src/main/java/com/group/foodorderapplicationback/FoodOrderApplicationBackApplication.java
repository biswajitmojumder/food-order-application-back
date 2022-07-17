package com.group.foodorderapplicationback;

import com.group.foodorderapplicationback.model.*;
import com.group.foodorderapplicationback.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

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
						  AdminService adminService,
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

			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setPassword("password");
			admin.setEmail("admin@email.com");
			admin.setFirstName("Admin");
			admin.setLastName("User");
			adminService.save(admin);
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
			food_1.setDescription("Fast food");

			Food food_2 = new Food();
			food_2.setName("Pizza Quattro Stagioni");
			food_2.setDescription("30 cm - normal crust");

			Food food_3 = new Food();
			food_3.setName("Pizza Diavola");
			food_3.setDescription("30 cm - normal crust");

			Food food_4 = new Food();
			food_4.setName("Burger One");
			food_4.setDescription("Fast food burger");

			Food food_5 = new Food();
			food_5.setName("Burger Cheese");
			food_5.setDescription("Burger with cheese");

			Food food_6 = new Food();
			food_6.setName("Pizza Suprema");
			food_6.setDescription("30 cm - normal crust");

			Food food_7 = new Food();
			food_7.setName("Pizza Quattro Formaggi");
			food_7.setDescription("30 cm - normal crust");

			Food food_8 = new Food();
			food_8.setName("Pizza Quattro Stagioni - Mixed");
			food_8.setDescription("30 cm - normal crust");

			Food food_9 = new Food();
			food_9.setName("Pizza Pepperoni Classic");
			food_9.setDescription("30 cm - normal crust");

			Food food_10 = new Food();
			food_10.setName("Pizza Capricciosa");
			food_10.setDescription("30 cm - normal crust");

			Food food_11 = new Food();
			food_11.setName("Pizza Carnivora");
			food_11.setDescription("30 cm - normal crust");

			Food food_12 = new Food();
			food_12.setName("Pizza Mexicana");
			food_12.setDescription("30 cm - normal crust");

			foodService.save(food_1);
			foodService.save(food_2);
			foodService.save(food_3);
			foodService.save(food_4);
			foodService.save(food_5);
			foodService.save(food_6);
			foodService.save(food_7);
			foodService.save(food_8);
			foodService.save(food_9);
			foodService.save(food_10);
			foodService.save(food_11);
			foodService.save(food_12);
			//endregion

			//region Restaurant
			Restaurant restaurant_1 = new Restaurant();
			restaurant_1.setName("Restaurantul 1");

			Restaurant restaurant_2 = new Restaurant();
			restaurant_2.setName("Restaurantul 2");

			restaurant_1.setFoodList(Arrays.asList(food_1, food_2, food_3, food_4, food_5));

			restaurantService.save(restaurant_1);
			restaurantService.save(restaurant_2);
			//endregion
		};
	}
}

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
			food_1.setFoodCategory(FoodCategory.FAST_FOOD);

			Food food_2 = new Food();
			food_2.setName("Pizza Quattro Stagioni");
			food_2.setDescription("30 cm - normal crust");
			food_2.setFoodCategory(FoodCategory.PIZZA);

			Food food_3 = new Food();
			food_3.setName("Pizza Diavola");
			food_3.setDescription("30 cm - normal crust");
			food_3.setFoodCategory(FoodCategory.PIZZA);

			Food food_4 = new Food();
			food_4.setName("Burger One");
			food_4.setDescription("Top burger");
			food_4.setFoodCategory(FoodCategory.BURGER);

			Food food_5 = new Food();
			food_5.setName("Cheese Burger");
			food_5.setDescription("Burger with cheese");
			food_5.setFoodCategory(FoodCategory.BURGER);

			Food food_6 = new Food();
			food_6.setName("Pizza Suprema");
			food_6.setDescription("30 cm - normal crust");
			food_6.setFoodCategory(FoodCategory.PIZZA);

			Food food_7 = new Food();
			food_7.setName("Pizza Quattro Formaggi");
			food_7.setDescription("30 cm - normal crust");
			food_7.setFoodCategory(FoodCategory.PIZZA);

			Food food_8 = new Food();
			food_8.setName("Pizza Quattro Stagioni - Mixed");
			food_8.setDescription("30 cm - normal crust");
			food_8.setFoodCategory(FoodCategory.PIZZA);

			Food food_9 = new Food();
			food_9.setName("Pizza Pepperoni Classic");
			food_9.setDescription("30 cm - normal crust");
			food_9.setFoodCategory(FoodCategory.PIZZA);

			Food food_10 = new Food();
			food_10.setName("Pizza Capricciosa");
			food_10.setDescription("30 cm - normal crust");
			food_10.setFoodCategory(FoodCategory.PIZZA);

			Food food_11 = new Food();
			food_11.setName("Pizza Carnivora");
			food_11.setDescription("30 cm - normal crust");
			food_11.setFoodCategory(FoodCategory.PIZZA);

			Food food_12 = new Food();
			food_12.setName("Pizza Mexicana");
			food_12.setDescription("30 cm - normal crust");
			food_12.setFoodCategory(FoodCategory.PIZZA);

			Food food_13 = new Food();
			food_13.setName("Pizza Rustica");
			food_13.setDescription("30 cm - normal crust");
			food_13.setFoodCategory(FoodCategory.PIZZA);

			Food food_14 = new Food();
			food_14.setName("Pizza Hawai");
			food_14.setDescription("30 cm - normal crust");
			food_14.setFoodCategory(FoodCategory.PIZZA);

			Food food_15 = new Food();
			food_15.setName("Chicken Burger");
			food_15.setDescription("Burger with chicken");
			food_15.setFoodCategory(FoodCategory.BURGER);

			Food food_16 = new Food();
			food_16.setName("Cheesy Chicken Burger");
			food_16.setDescription("Burger with cheese and chicken");
			food_16.setFoodCategory(FoodCategory.BURGER);

			Food food_17 = new Food();
			food_17.setName("Chicken Fillet Burger");
			food_17.setDescription("Burger with chicken fillet");
			food_17.setFoodCategory(FoodCategory.BURGER);

			Food food_18 = new Food();
			food_18.setName("Tiramisu");
			food_18.setDescription("Cake");
			food_18.setFoodCategory(FoodCategory.DESSERT);

			Food food_19 = new Food();
			food_19.setName("Chocolate Mousse");
			food_19.setDescription("Cake");
			food_19.setFoodCategory(FoodCategory.DESSERT);

			Food food_20 = new Food();
			food_20.setName("White Chocolate Cheesecake");
			food_20.setDescription("Cake");
			food_20.setFoodCategory(FoodCategory.DESSERT);

			Food food_21 = new Food();
			food_21.setName("Chocolate Hazelnut Ice Cream");
			food_21.setDescription("Ice cream");
			food_21.setFoodCategory(FoodCategory.DESSERT);

			Food food_22 = new Food();
			food_22.setName("Black Forest Tart");
			food_22.setDescription("Cake");
			food_22.setFoodCategory(FoodCategory.DESSERT);

			Food food_23 = new Food();
			food_23.setName("Spicy Grilled Eggplant");
			food_23.setDescription("Vegetarian food");
			food_23.setFoodCategory(FoodCategory.GRILL);

			Food food_24 = new Food();
			food_24.setName("Grilled Pineapple with Lime Dip");
			food_24.setDescription("Vegetarian food");
			food_24.setFoodCategory(FoodCategory.GRILL);

			Food food_25 = new Food();
			food_25.setName("Stuffed Grilled Zucchini");
			food_25.setDescription("Vegetarian food");
			food_25.setFoodCategory(FoodCategory.GRILL);

			Food food_26 = new Food();
			food_26.setName("Grilled Cheese & Tomato Flatbreads");
			food_26.setDescription("Vegetarian food");
			food_26.setFoodCategory(FoodCategory.GRILL);

			Food food_27 = new Food();
			food_27.setName("Pork Ribs Grilled");
			food_27.setDescription("Grilled meat");
			food_27.setFoodCategory(FoodCategory.GRILL);

			Food food_28 = new Food();
			food_28.setName("Pork Chops Marinated Grilled");
			food_28.setDescription("Grilled meat");
			food_28.setFoodCategory(FoodCategory.GRILL);

			Food food_29 = new Food();
			food_29.setName("Lamb Chops Grilled");
			food_29.setDescription("Grilled meat");
			food_29.setFoodCategory(FoodCategory.GRILL);

			Food food_30 = new Food();
			food_30.setName("Pork Steak");
			food_30.setDescription("Grilled meat");
			food_30.setFoodCategory(FoodCategory.GRILL);

			Food food_31 = new Food();
			food_31.setName("Pepsi");
			food_31.setDescription("0.5l Drink");
			food_31.setFoodCategory(FoodCategory.DRINK);

			Food food_32 = new Food();
			food_32.setName("7UP");
			food_32.setDescription("0.5l Drink");
			food_32.setFoodCategory(FoodCategory.DRINK);

			Food food_33 = new Food();
			food_33.setName("Sprite");
			food_33.setDescription("0.5l Drink");
			food_33.setFoodCategory(FoodCategory.DRINK);

			Food food_34 = new Food();
			food_34.setName("Fanta");
			food_34.setDescription("0.5l Drink");
			food_34.setFoodCategory(FoodCategory.DRINK);

			Food food_35 = new Food();
			food_35.setName("Prigat");
			food_35.setDescription("0.5l Drink");
			food_35.setFoodCategory(FoodCategory.DRINK);

			Food food_36 = new Food();
			food_36.setName("Heineken");
			food_36.setDescription("0.5l Alcoholic Drink");
			food_36.setFoodCategory(FoodCategory.DRINK);

			Food food_37 = new Food();
			food_37.setName("Tuborg");
			food_37.setDescription("0.5l Alcoholic Drink");
			food_37.setFoodCategory(FoodCategory.DRINK);

			Food food_38 = new Food();
			food_38.setName("Ursus");
			food_38.setDescription("0.5l Alcoholic Drink");
			food_38.setFoodCategory(FoodCategory.DRINK);

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
			foodService.save(food_13);
			foodService.save(food_14);
			foodService.save(food_15);
			foodService.save(food_16);
			foodService.save(food_17);
			foodService.save(food_18);
			foodService.save(food_19);
			foodService.save(food_20);
			foodService.save(food_21);
			foodService.save(food_22);
			foodService.save(food_23);
			foodService.save(food_24);
			foodService.save(food_25);
			foodService.save(food_26);
			foodService.save(food_27);
			foodService.save(food_28);
			foodService.save(food_29);
			foodService.save(food_30);
			foodService.save(food_31);
			foodService.save(food_32);
			foodService.save(food_33);
			foodService.save(food_34);
			foodService.save(food_35);
			foodService.save(food_36);
			foodService.save(food_37);
			foodService.save(food_38);
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

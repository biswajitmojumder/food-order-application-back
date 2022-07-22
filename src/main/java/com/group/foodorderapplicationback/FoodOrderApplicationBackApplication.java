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
						  ManagerService managerService,
						  RestaurantService restaurantService,
						  StaffService staffService,
						  DeliveryUserService deliveryUserService) {
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

			//region Test users
			User user = new User();
			user.setUsername("user_1");
			user.setPassword("password");

			user.setEmail("abc@email.com");
			user.setFirstName("Firstname");
			user.setLastName("Lastname");
			user.setPhoneNumber("0712345690");

			userService.saveUser(user);

			User user_2 = new User();
			user_2.setUsername("user_2");
			user_2.setPassword("password");
			userService.saveUser(user_2);

			User user_3 = new User();
			user_3.setUsername("user_3");
			user_3.setPassword("password");
			userService.saveUser(user_3);

			User user_4 = new User();
			user_4.setUsername("user_4");
			user_4.setPassword("password");
			userService.saveUser(user_4);

			User user_5 = new User();
			user_5.setUsername("user_5");
			user_5.setPassword("password");
			userService.saveUser(user_5);

			User user_6 = new User();
			user_6.setUsername("user_6");
			user_6.setPassword("password");
			userService.saveUser(user_6);

			User user_7 = new User();
			user_7.setUsername("user_7");
			user_7.setPassword("password");
			userService.saveUser(user_7);

			User user_8 = new User();
			user_8.setUsername("user_8");
			user_8.setPassword("password");
			userService.saveUser(user_8);

			User user_9 = new User();
			user_9.setUsername("user_9");
			user_9.setPassword("password");
			userService.saveUser(user_9);

			User user_10 = new User();
			user_10.setUsername("user_10");
			user_10.setPassword("password");
			userService.saveUser(user_10);
			//endregion

			//region Manager
			Manager manager = new Manager();
			manager.setFirstName("Firstname");
			manager.setLastName("Lastname");
			manager.setUsername("manager_1");
			manager.setPassword("password");
			managerService.save(manager);
			//endregion

			//region Staff
			Staff staff = new Staff();
			staff.setFirstName("Firstname");
			staff.setLastName("Lastname");
			staff.setUsername("staff_1");
			staff.setPassword("password");
			staffService.save(staff);
			//endregion Staff

			//region Delivery User
			DeliveryUser deliveryUser = new DeliveryUser();
			deliveryUser.setFirstName("Firstname");
			deliveryUser.setLastName("Lastname");
			deliveryUser.setUsername("delivery_user_1");
			deliveryUser.setPassword("password");
			deliveryUser.setVehicleManufacturer("BMW");
			deliveryUser.setVehicleNumber("B 123 ABC");
			deliveryUser.setVehicleColor("RED");
			deliveryUser.setPhoneNumber("0712312312");

			deliveryUserService.save(deliveryUser);
			//endregion Delivery User

			//region Food
			Food food_1 = new Food();
			food_1.setName("Shaorma");
			food_1.setDescription("Fast food");
			food_1.setFoodCategory(FoodCategory.FAST_FOOD);
			food_1.setPrice("35");

			Food food_2 = new Food();
			food_2.setName("Pizza Quattro Stagioni");
			food_2.setDescription("30 cm - normal crust");
			food_2.setFoodCategory(FoodCategory.PIZZA);
			food_2.setPrice("35");

			Food food_3 = new Food();
			food_3.setName("Pizza Diavola");
			food_3.setDescription("30 cm - normal crust");
			food_3.setFoodCategory(FoodCategory.PIZZA);
			food_3.setPrice("33");

			Food food_4 = new Food();
			food_4.setName("Burger One");
			food_4.setDescription("Top burger");
			food_4.setFoodCategory(FoodCategory.BURGER);
			food_4.setPrice("30");

			Food food_5 = new Food();
			food_5.setName("Cheese Burger");
			food_5.setDescription("Burger with cheese");
			food_5.setFoodCategory(FoodCategory.BURGER);
			food_5.setPrice("38");

			Food food_6 = new Food();
			food_6.setName("Pizza Suprema");
			food_6.setDescription("30 cm - normal crust");
			food_6.setFoodCategory(FoodCategory.PIZZA);
			food_6.setPrice("33");

			Food food_7 = new Food();
			food_7.setName("Pizza Quattro Formaggi");
			food_7.setDescription("30 cm - normal crust");
			food_7.setFoodCategory(FoodCategory.PIZZA);
			food_7.setPrice("32");

			Food food_8 = new Food();
			food_8.setName("Pizza Quattro Stagioni - Mixed");
			food_8.setDescription("30 cm - normal crust");
			food_8.setFoodCategory(FoodCategory.PIZZA);
			food_8.setPrice("38");

			Food food_9 = new Food();
			food_9.setName("Pizza Pepperoni Classic");
			food_9.setDescription("30 cm - normal crust");
			food_9.setFoodCategory(FoodCategory.PIZZA);
			food_9.setPrice("33");

			Food food_10 = new Food();
			food_10.setName("Pizza Capricciosa");
			food_10.setDescription("30 cm - normal crust");
			food_10.setFoodCategory(FoodCategory.PIZZA);
			food_10.setPrice("29");

			Food food_11 = new Food();
			food_11.setName("Pizza Carnivora");
			food_11.setDescription("30 cm - normal crust");
			food_11.setFoodCategory(FoodCategory.PIZZA);
			food_11.setPrice("31");

			Food food_12 = new Food();
			food_12.setName("Pizza Mexicana");
			food_12.setDescription("30 cm - normal crust");
			food_12.setFoodCategory(FoodCategory.PIZZA);
			food_12.setPrice("35");

			Food food_13 = new Food();
			food_13.setName("Pizza Rustica");
			food_13.setDescription("30 cm - normal crust");
			food_13.setFoodCategory(FoodCategory.PIZZA);
			food_13.setPrice("30");

			Food food_14 = new Food();
			food_14.setName("Pizza Hawai");
			food_14.setDescription("30 cm - normal crust");
			food_14.setFoodCategory(FoodCategory.PIZZA);
			food_14.setPrice("32");

			Food food_15 = new Food();
			food_15.setName("Chicken Burger");
			food_15.setDescription("Burger with chicken");
			food_15.setFoodCategory(FoodCategory.BURGER);
			food_15.setPrice("42");

			Food food_16 = new Food();
			food_16.setName("Cheesy Chicken Burger");
			food_16.setDescription("Burger with cheese and chicken");
			food_16.setFoodCategory(FoodCategory.BURGER);
			food_16.setPrice("39");

			Food food_17 = new Food();
			food_17.setName("Chicken Fillet Burger");
			food_17.setDescription("Burger with chicken fillet");
			food_17.setFoodCategory(FoodCategory.BURGER);
			food_17.setPrice("38");

			Food food_18 = new Food();
			food_18.setName("Tiramisu");
			food_18.setDescription("Cake");
			food_18.setFoodCategory(FoodCategory.DESSERT);
			food_18.setPrice("15");

			Food food_19 = new Food();
			food_19.setName("Chocolate Mousse");
			food_19.setDescription("Cake");
			food_19.setFoodCategory(FoodCategory.DESSERT);
			food_19.setPrice("17");

			Food food_20 = new Food();
			food_20.setName("White Chocolate Cheesecake");
			food_20.setDescription("Cake");
			food_20.setFoodCategory(FoodCategory.DESSERT);
			food_20.setPrice("14");

			Food food_21 = new Food();
			food_21.setName("Chocolate Hazelnut Ice Cream");
			food_21.setDescription("Ice cream");
			food_21.setFoodCategory(FoodCategory.DESSERT);
			food_21.setPrice("22");

			Food food_22 = new Food();
			food_22.setName("Black Forest Tart");
			food_22.setDescription("Cake");
			food_22.setFoodCategory(FoodCategory.DESSERT);
			food_22.setPrice("24");

			Food food_23 = new Food();
			food_23.setName("Spicy Grilled Eggplant");
			food_23.setDescription("Vegetarian food");
			food_23.setFoodCategory(FoodCategory.GRILL);
			food_23.setPrice("24");

			Food food_24 = new Food();
			food_24.setName("Grilled Pineapple with Lime Dip");
			food_24.setDescription("Vegetarian food");
			food_24.setFoodCategory(FoodCategory.GRILL);
			food_24.setPrice("28");

			Food food_25 = new Food();
			food_25.setName("Stuffed Grilled Zucchini");
			food_25.setDescription("Vegetarian food");
			food_25.setFoodCategory(FoodCategory.GRILL);
			food_25.setPrice("26");

			Food food_26 = new Food();
			food_26.setName("Grilled Cheese & Tomato Flatbreads");
			food_26.setDescription("Vegetarian food");
			food_26.setFoodCategory(FoodCategory.GRILL);
			food_26.setPrice("22");

			Food food_27 = new Food();
			food_27.setName("Pork Ribs Grilled");
			food_27.setDescription("Grilled meat");
			food_27.setFoodCategory(FoodCategory.GRILL);
			food_27.setPrice("18");

			Food food_28 = new Food();
			food_28.setName("Pork Chops Marinated Grilled");
			food_28.setDescription("Grilled meat");
			food_28.setFoodCategory(FoodCategory.GRILL);
			food_28.setPrice("20");

			Food food_29 = new Food();
			food_29.setName("Lamb Chops Grilled");
			food_29.setDescription("Grilled meat");
			food_29.setFoodCategory(FoodCategory.GRILL);
			food_29.setPrice("22");

			Food food_30 = new Food();
			food_30.setName("Pork Steak");
			food_30.setDescription("Grilled meat");
			food_30.setFoodCategory(FoodCategory.GRILL);
			food_30.setPrice("24");

			Food food_31 = new Food();
			food_31.setName("Pepsi");
			food_31.setDescription("0.5l Drink");
			food_31.setFoodCategory(FoodCategory.DRINK);
			food_31.setPrice("8");

			Food food_32 = new Food();
			food_32.setName("7UP");
			food_32.setDescription("0.5l Drink");
			food_32.setFoodCategory(FoodCategory.DRINK);
			food_32.setPrice("8");

			Food food_33 = new Food();
			food_33.setName("Sprite");
			food_33.setDescription("0.5l Drink");
			food_33.setFoodCategory(FoodCategory.DRINK);
			food_33.setPrice("8");

			Food food_34 = new Food();
			food_34.setName("Fanta");
			food_34.setDescription("0.5l Drink");
			food_34.setFoodCategory(FoodCategory.DRINK);
			food_34.setPrice("8");

			Food food_35 = new Food();
			food_35.setName("Prigat");
			food_35.setDescription("0.5l Drink");
			food_35.setFoodCategory(FoodCategory.DRINK);
			food_35.setPrice("12");

			Food food_36 = new Food();
			food_36.setName("Heineken");
			food_36.setDescription("0.5l Alcoholic Drink");
			food_36.setFoodCategory(FoodCategory.DRINK);
			food_36.setPrice("10");

			Food food_37 = new Food();
			food_37.setName("Tuborg");
			food_37.setDescription("0.5l Alcoholic Drink");
			food_37.setFoodCategory(FoodCategory.DRINK);
			food_37.setPrice("8");

			Food food_38 = new Food();
			food_38.setName("Ursus");
			food_38.setDescription("0.5l Alcoholic Drink");
			food_38.setFoodCategory(FoodCategory.DRINK);
			food_38.setPrice("8");

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
			restaurant_1.setDescription("The main restaurant");

			Address address_1 = new Address();
			address_1.setStreetAddress("Str. Abc nr. 222 sect. 3");
			address_1.setCity("Bucharest");
			address_1.setZipCode("012123");

			restaurant_1.setAddress(address_1);

			Restaurant restaurant_2 = new Restaurant();
			restaurant_2.setName("Restaurantul 2");
			restaurant_2.setDescription("The secondary restaurant");

			Address address_2 = new Address();
			address_2.setStreetAddress("Str. Def nr. 100 sect. 1");
			address_2.setCity("Bucharest");
			address_2.setZipCode("010223");

			restaurant_2.setAddress(address_2);

			restaurant_1.setFoodList(
					Arrays.asList(
							food_1,
							food_2,
							food_3,
							food_4,
							food_5,
							food_6,
							food_7,
							food_8,
							food_9,
							food_10,
							food_11,
							food_12,
							food_13,
							food_14,
							food_15,
							food_16,
							food_17,
							food_18,
							food_19,
							food_20,
							food_21,
							food_22,
							food_23,
							food_24,
							food_25,
							food_26,
							food_27,
							food_28,
							food_29,
							food_30,
							food_31,
							food_32,
							food_33,
							food_34,
							food_35,
							food_36,
							food_37,
							food_38
					));

			restaurant_2.setFoodList(
					Arrays.asList(
							food_1,
							food_2,
							food_3,
							food_7,
							food_8,
							food_9,
							food_10,
							food_13,
							food_14,
							food_15,
							food_18,
							food_19,
							food_20,
							food_21,
							food_22,
							food_23,
							food_24
					));

			restaurantService.save(restaurant_1);
			restaurantService.save(restaurant_2);
			//endregion
		};
	}
}

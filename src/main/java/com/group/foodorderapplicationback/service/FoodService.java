package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.FoodCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodService {
    Page<Food> findAll(Pageable pageable);
    Page<Food> findByFoodCategoryName(Pageable pageable, String categoryName);
    Page<Food> findByFoodCategoryNameFromRestaurant(Pageable pageable, String category, Long restaurantId);
    Food insertFood(Food food, Long restaurantId);
    Food save(Food food);
    Food setRestaurant(Long foodId, Long restaurantId);
    void deleteById(Long id);
    Page<Food> searchByFoodCategoryAndRestaurant(Pageable pageable, String foodCategory, String restaurantName);
}

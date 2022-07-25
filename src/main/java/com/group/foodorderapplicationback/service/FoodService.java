package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FoodService {
    Page<Food> findAll(Pageable pageable);
    Page<Food> findByFoodCategoryName(Pageable pageable, String categoryName);
    Page<Food> findByFoodCategoryNameFromRestaurant(Pageable pageable, String category, Long restaurantId);
    Food insertFood(Food food, String category, Long[] restaurantId);
    Food update(Food food);
    Food save(Food food);
    Food setRestaurant(Long foodId, Long restaurantId);
    Optional<Food> findById(Long id);
    void deleteById(Long id);
    Page<Food> searchByFoodCategoryAndRestaurant(Pageable pageable, String foodCategory, String restaurantName);
}

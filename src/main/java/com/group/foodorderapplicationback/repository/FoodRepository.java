package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.FoodCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {
    Page<Food> findByFoodCategory(Pageable pageable, FoodCategory foodCategory);
    Page<Food> findByFoodCategoryAndRestaurantListId(Pageable pageable, FoodCategory foodCategory, Long restaurantId);

}

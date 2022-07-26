package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.FoodCategory;
import com.group.foodorderapplicationback.model.Restaurant;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long>, QueryByExampleExecutor<Food> {
    Page<Food> findByFoodCategoryName(Pageable pageable, String name);
    Page<Food> findByFoodCategoryNameAndRestaurantListId(Pageable pageable, String category, Long restaurantId);


    Page<Food> findDistinctByFoodCategoryNameContainsAndRestaurantListNameContainsOrderById(Pageable pageable, String foodCategory, String restaurantName);

}

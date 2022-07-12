package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.Food;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {
}

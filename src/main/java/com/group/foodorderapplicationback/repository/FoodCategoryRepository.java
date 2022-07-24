package com.group.foodorderapplicationback.repository;

import com.group.foodorderapplicationback.model.FoodCategory;
import org.springframework.data.repository.CrudRepository;

public interface FoodCategoryRepository extends CrudRepository<FoodCategory, Long> {
}

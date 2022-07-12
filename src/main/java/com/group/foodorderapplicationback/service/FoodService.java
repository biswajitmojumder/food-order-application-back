package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodService {
    Page<Food> findAll(Pageable pageable);
    Food save(Food food);
}

package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.FoodCategory;
import com.group.foodorderapplicationback.repository.FoodCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public FoodCategory save(FoodCategory foodCategory) {
        return foodCategoryRepository.save(foodCategory);
    }
}

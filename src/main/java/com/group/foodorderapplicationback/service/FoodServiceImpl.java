package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Page<Food> findAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }
}

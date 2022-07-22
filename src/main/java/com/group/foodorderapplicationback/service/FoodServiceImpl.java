package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.FoodCategory;
import com.group.foodorderapplicationback.model.Restaurant;
import com.group.foodorderapplicationback.repository.FoodRepository;
import com.group.foodorderapplicationback.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<Food> findAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Page<Food> findByFoodCategory(Pageable pageable, FoodCategory foodCategory) {
        return foodRepository.findByFoodCategory(pageable, foodCategory);
    }

    @Override
    public Page<Food> findByFoodCategoryFromRestaurant(Pageable pageable, FoodCategory foodCategory, Long restaurantId) {
        return foodRepository.findByFoodCategoryAndRestaurantListId(pageable, foodCategory, restaurantId);
    }

    @Override
    public Food insertFood(Food food, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        restaurant.getFoodList().add(food);
        return foodRepository.save(food);
    }

    @Override
    public Food save(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food setRestaurant(Long foodId, Long restaurantId) {
        Food food = foodRepository.findById(foodId).get();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        restaurant.getFoodList().add(food);

        return food;
    }
}

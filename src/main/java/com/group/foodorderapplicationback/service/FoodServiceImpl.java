package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.FoodCategory;
import com.group.foodorderapplicationback.model.Restaurant;
import com.group.foodorderapplicationback.repository.FoodCategoryRepository;
import com.group.foodorderapplicationback.repository.FoodRepository;
import com.group.foodorderapplicationback.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<Food> findAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Page<Food> findByFoodCategoryName(Pageable pageable, String name) {
        return foodRepository.findByFoodCategoryName(pageable, name);
    }

    @Override
    public Page<Food> findByFoodCategoryNameFromRestaurant(Pageable pageable, String category, Long restaurantId) {
        return foodRepository.findByFoodCategoryNameAndRestaurantListId(pageable, category, restaurantId);
    }

    @Override
    public Food insertFood(Food food, String category, Long[] restaurantId) {
        FoodCategory foodCategory = foodCategoryRepository.findByName(category);
        food.setFoodCategory(foodCategory);

        for(int i=0; i<restaurantId.length; i++) {
            Restaurant restaurant = restaurantRepository.findById(restaurantId[i]).get();
            restaurant.getFoodList().add(food);
            foodRepository.save(food);
        }
        return food;
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

    @Override
    public void deleteById(Long id) {
        Food food = foodRepository.findById(id).get();
        List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();

        for(Restaurant restaurant : restaurantList) {
            restaurant.getFoodList().remove(food);
        }

        foodRepository.deleteById(id);
    }

    @Override
    public Page<Food> searchByFoodCategoryAndRestaurant(Pageable pageable, String foodCategory, String restaurantName) {
        return foodRepository.findByFoodCategoryNameContainsAndRestaurantListNameContains(pageable, foodCategory, restaurantName);
    }
}

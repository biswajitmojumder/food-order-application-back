package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.FoodCategory;
import com.group.foodorderapplicationback.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FoodController {
    private final FoodService foodService;

    @GetMapping(value = "/food")
    public ResponseEntity<Food> getFood(@RequestParam Long id) {
        return ResponseEntity.ok().body(foodService.findById(id).get());
    }

    @GetMapping(value = "/food", params = "page")
    public ResponseEntity<Page<Food>> getFood(@RequestParam int page) {
        return ResponseEntity.ok().body(foodService.findAll(PageRequest.of(page, 9)));
    }

    @GetMapping(value = "/food/search")
    public ResponseEntity<Page<Food>> searchByFoodCategoryOrRestaurant(@RequestParam String category,
                                                                       @RequestParam String restaurantName,
                                                                       @RequestParam int page) {
        return ResponseEntity.ok().body(foodService.searchByFoodCategoryAndRestaurant(
                PageRequest.of(page, 9),
                category,
                restaurantName));
    }

    @GetMapping(value = "/food/{category}")
    public ResponseEntity<Page<Food>> getFoodByCategory(@PathVariable String category, @RequestParam int page) {
        return ResponseEntity.ok().body(foodService.findByFoodCategoryName(PageRequest.of(page, 9), category));
    }

    @GetMapping(value = "/food/{category}/{restaurantId}")
    public ResponseEntity<Page<Food>> getFoodByCategoryFromRestaurant(@PathVariable String category,
                                                                      @PathVariable Long restaurantId,
                                                                      @RequestParam int page) {
        return ResponseEntity.ok().body(foodService.findByFoodCategoryNameFromRestaurant(PageRequest.of(page, 9), category, restaurantId));
    }

    @PostMapping(value = "/food/insert")
    public ResponseEntity<Food> insertFood(@RequestBody Food food, @RequestParam String category, @RequestParam Long[] restaurantId) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food/insert").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(foodService.insertFood(food, category, restaurantId));
    }

    @PutMapping(value = "/food/update")
    public ResponseEntity<Food> updateFood(@RequestBody Food food) {
        return ResponseEntity.ok().body(foodService.update(food));
    }

    @PutMapping(value = "/food/set-restaurant/{foodId}/{restaurantId}")
    public ResponseEntity<Food> setRestaurant(@PathVariable Long foodId, @PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(foodService.setRestaurant(foodId, restaurantId));
    }

    @DeleteMapping(value = "/food/delete", params = "id")
    public ResponseEntity<Long> deleteFood(@RequestParam Long id) {
        try {
            foodService.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        catch (Exception exception) {
            log.error("Delete :: Id not found! :: id:{" + id + "}; EXCEPTION: " + exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

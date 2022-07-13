package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping(value = "/food", params = "page")
    public ResponseEntity<Page<Food>> getFood(@RequestParam int page) {
        return ResponseEntity.ok().body(foodService.findAll(PageRequest.of(page, 2)));
    }

    @PostMapping(value = "/food/insert", params = "restaurantId")
    public ResponseEntity<Food> insertFood(@RequestBody Food food, @RequestParam Long restaurantId) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food/insert").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(foodService.insertFood(food, restaurantId));
    }

    @PutMapping(value = "food/update")
    public ResponseEntity<Food> updateFood(@RequestBody Food food) {
        return ResponseEntity.ok().body(foodService.save(food));
    }

    @PutMapping(value = "food/set-restaurant/{foodId}/{restaurantId}")
    public ResponseEntity<Food> setRestaurant(@PathVariable Long foodId, @PathVariable Long restaurantId) {
        return ResponseEntity.ok().body(foodService.setRestaurant(foodId, restaurantId));
    }
}

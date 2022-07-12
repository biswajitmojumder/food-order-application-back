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

    @GetMapping(value = "/food/get", params = "page")
    public ResponseEntity<Page<Food>> getFood(@RequestParam int page) {
        return ResponseEntity.ok().body(foodService.findAll(PageRequest.of(page, 2)));
    }

    @PostMapping(value = "/food/insert")
    public ResponseEntity<Food> insertFood(@RequestBody Food food) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food/insert").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(foodService.save(food));
    }
}

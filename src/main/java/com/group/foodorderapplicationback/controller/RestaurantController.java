package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Restaurant;
import com.group.foodorderapplicationback.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping(value = "/restaurant/get-all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok().body(restaurantService.findAll());
    }

    @GetMapping(value = "/restaurant/get", params = "id")
    public ResponseEntity<Restaurant> getRestaurantById(@RequestParam Long id) {
        return ResponseEntity.ok().body(restaurantService.findById(id).get());
    }

    @PostMapping(value = "/restaurant/save")
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/restaurant/save").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(restaurantService.save(restaurant));
    }

}

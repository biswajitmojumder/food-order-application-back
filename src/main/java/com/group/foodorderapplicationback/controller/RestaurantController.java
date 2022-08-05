package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Restaurant;
import com.group.foodorderapplicationback.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    @PostMapping(value = "/restaurant/new")
    public ResponseEntity<Restaurant> newRestaurant(@RequestBody Restaurant restaurant) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/restaurant/save").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(restaurantService.save(restaurant));
    }

    @PutMapping(value = "/restaurant/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.ok().body(restaurantService.update(restaurant));
    }

    @DeleteMapping(value = "/restaurant/delete", params = "id")
    public ResponseEntity<Long> deleteRestaurant(@RequestParam Long id) {
        try {
            restaurantService.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        catch (Exception exception) {
            log.error("Delete :: Id not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

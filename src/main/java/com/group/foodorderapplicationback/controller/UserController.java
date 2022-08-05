package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Food;
import com.group.foodorderapplicationback.model.Orders;
import com.group.foodorderapplicationback.model.User;
import com.group.foodorderapplicationback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user/search")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam String username, @RequestParam String email) {
        return ResponseEntity.ok().body(userService.searchByUsernameOrEmail(username, email));
    }

    @GetMapping(value = "/user/get-all", params = "page")
    public ResponseEntity<Page<User>> getAllUsersPaginated(@RequestParam int page) {
        return ResponseEntity.ok().body(userService.findAll(PageRequest.of(page, 9)));
    }

    @GetMapping("/user/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/user/get-favorite-food")
    public ResponseEntity<List<Food>> getFavoriteFood(HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.findFavouriteFoodForUser(request));
    }

    @GetMapping("/user/get-order-history")
    public ResponseEntity<List<Orders>> getUserOrders(HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.findAllOrdersForUser(request));
    }

    @GetMapping("/user/get-user-info")
    public ResponseEntity<User> getUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.getUserInfo(request));
    }

    @PostMapping("/user/new")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(HttpServletRequest request, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.update(request, user));
    }

    @DeleteMapping(value = "/user/delete", params = "id")
    public ResponseEntity<Long> deleteUser(@RequestParam Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        catch (Exception exception) {
            log.error("Delete :: Id not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/user/add-food-to-favorites", params = "foodId")
    public ResponseEntity<Food> addFoodToFavorites(HttpServletRequest request, @RequestParam Long foodId) {
        return ResponseEntity.ok().body(userService.addFoodToFavorites(foodId, request));
    }

    @DeleteMapping(value = "/user/remove-food-from-favourites", params = "id")
    public ResponseEntity<Long> removeFoodFromFavourites(HttpServletRequest request, @RequestParam Long id) {
        try {
            userService.removeFoodFromFavourites(id, request);
            return ResponseEntity.ok().body(id);
        }
        catch (Exception exception) {
            log.error("Remove food from favourites :: Id not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

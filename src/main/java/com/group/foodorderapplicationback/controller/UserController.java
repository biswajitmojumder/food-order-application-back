package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Manager;
import com.group.foodorderapplicationback.model.User;
import com.group.foodorderapplicationback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user/new")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
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
}

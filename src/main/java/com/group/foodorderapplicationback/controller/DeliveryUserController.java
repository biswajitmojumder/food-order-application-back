package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.service.DeliveryUserService;
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
public class DeliveryUserController {

    private final DeliveryUserService deliveryUserService;

    @PostMapping("/delivery-user/new")
    public ResponseEntity<DeliveryUser> saveDeliveryUser(@RequestBody DeliveryUser deliveryUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delivery-user/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(deliveryUserService.save(deliveryUser));
    }

    @PutMapping("/delivery-user/update")
    public ResponseEntity<DeliveryUser> updateDeliveryUser(@RequestBody DeliveryUser deliveryUser) {
        return ResponseEntity.ok().body(deliveryUserService.save(deliveryUser));
    }

    @DeleteMapping(value = "/delivery-user/delete", params = "id")
    public ResponseEntity<Long> deleteDeliveryUser(@RequestParam Long id) {
        try {
            deliveryUserService.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        catch (Exception exception) {
            log.error("Delete :: Id not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

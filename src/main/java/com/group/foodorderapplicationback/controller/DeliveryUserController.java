package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.model.User;
import com.group.foodorderapplicationback.service.DeliveryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class DeliveryUserController {

    private final DeliveryUserService deliveryUserService;

    @PostMapping("/delivery-user/save")
    public ResponseEntity<DeliveryUser> saveDeliveryUser(@RequestBody DeliveryUser deliveryUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delivery-user/save").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(deliveryUserService.saveDeliveryUser(deliveryUser));
    }

}

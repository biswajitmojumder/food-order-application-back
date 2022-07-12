package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/staff/save")
    public ResponseEntity<Account> saveManager(@RequestBody Account account) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/staff/save").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(staffService.saveStaff(account));
    }

}

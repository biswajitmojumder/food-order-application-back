package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.DeliveryUser;
import com.group.foodorderapplicationback.model.Staff;
import com.group.foodorderapplicationback.model.User;
import com.group.foodorderapplicationback.service.StaffService;
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
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/staff/new")
    public ResponseEntity<Account> saveManager(@RequestBody Staff staff) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/staff/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(staffService.save(staff));
    }

    @PutMapping("/staff/update")
    public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok().body(staffService.save(staff));
    }

    @DeleteMapping(value = "/staff/delete", params = "id")
    public ResponseEntity<Long> deleteStaff(@RequestParam Long id) {
        try {
            staffService.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        catch (Exception exception) {
            log.error("Delete :: Id not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

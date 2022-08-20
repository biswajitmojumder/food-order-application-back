package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.*;
import com.group.foodorderapplicationback.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class StaffController {

    private final StaffService staffService;

    @GetMapping("staff/get-all")
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok().body(staffService.findAll());
    }

    @PostMapping("/staff/new")
    public ResponseEntity<Staff> saveStaff(@RequestBody Staff staff) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/staff/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(staffService.save(staff));
    }

    @PutMapping("/staff/update")
    public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok().body(staffService.update(staff));
    }

    @PutMapping("/staff/update-authenticated")
    public ResponseEntity<Staff> updateStaff(HttpServletRequest request, @RequestBody Staff staff) {
        return ResponseEntity.ok().body(staffService.updateAuthenticated(request, staff));
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

    @GetMapping("/staff/get-staff-info")
    public ResponseEntity<Staff> getStaffInfo(HttpServletRequest request) {
        return ResponseEntity.ok().body(staffService.getStaffInfo(request));
    }

}
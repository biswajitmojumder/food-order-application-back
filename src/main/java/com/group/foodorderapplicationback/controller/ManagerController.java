package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Account;
import com.group.foodorderapplicationback.model.Manager;
import com.group.foodorderapplicationback.service.ManagerService;
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
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/manager/get-all")
    public ResponseEntity<List<Manager>> getAllManagers() {
        return ResponseEntity.ok().body(managerService.findAll());
    }

    @PostMapping("/manager/new")
    public ResponseEntity<Account> saveManager(@RequestBody Manager manager) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/manager/new").toUriString());    //Status 201 - created
        return ResponseEntity.created(uri).body(managerService.save(manager));
    }

    @PutMapping("/manager/update")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) {
        return ResponseEntity.ok().body(managerService.update(manager));
    }

    @PutMapping("/manager/update-authenticated")
    public ResponseEntity<Manager> updateManager(HttpServletRequest request, @RequestBody Manager manager) {
        return ResponseEntity.ok().body(managerService.updateAuthenticated(request, manager));
    }

    @DeleteMapping(value = "/manager/delete", params = "id")
    public ResponseEntity<Long> deleteManager(@RequestParam Long id) {
        try {
            managerService.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        catch (Exception exception) {
            log.error("Delete :: Id not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/manager/get-manager-info")
    public ResponseEntity<Manager> getManagerInfo(HttpServletRequest request) {
        return ResponseEntity.ok().body(managerService.getManagerInfo(request));
    }

}

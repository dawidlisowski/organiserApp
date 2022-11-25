package pl.dawidlisowski.OrganiserApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dawidlisowski.OrganiserApp.models.forms.RegisterForm;
import pl.dawidlisowski.OrganiserApp.models.services.ApiService;

import java.util.List;

@RestController
public class ApiController {

    final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id) {
        return apiService.getUserById(id);
    }
    @GetMapping("/api/user")
    public ResponseEntity getAllUsers() {
        return apiService.getAllUsers();
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id) {
        return apiService.deleteUserById(id);
    }

    @PostMapping(value = "/api/user", consumes = "application/json")
    public ResponseEntity saveNewUser(@RequestBody RegisterForm user) {
        return apiService.saveUser(user);
    }
}
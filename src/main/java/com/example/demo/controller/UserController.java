package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getMapping")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/postMapping")
    public ResponseEntity<User> saveStudent(@RequestBody User user) {
        user = userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/putMapping")
    public ResponseEntity<User> putExample(@RequestBody User user) {
        user = userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMapping")
    public ResponseEntity<String> deleteExample(@RequestParam("user-id") String userId) {
        String response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

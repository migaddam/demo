package com.example.demo.service;

import com.example.demo.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);
        return users;
    }

    public User saveUser(User user) {
        user.setId(1);
        user.setName("Arun");
        return user;
    }

    public User updateUser(User user) {
        user.setId(2);
        user.setName("John");
        return user;
    }

    public String deleteUser(String userId) {
        return "User is deleted";
    }
}

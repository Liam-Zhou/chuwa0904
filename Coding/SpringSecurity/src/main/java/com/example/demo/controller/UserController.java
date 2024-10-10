package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.rest.UserNotFoundException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("users/{username}")
    public List<User> getUserById(@PathVariable String username) {
        if(username.isEmpty()) {
            throw new UserNotFoundException("Not Found " + username);
        }
        List<User> users = userService.findByUsername(username);
        if(users.isEmpty()) throw new UserNotFoundException("Not Found " + username);
        return users;
    }

    @PostMapping("users")
    public User addUser(@RequestBody User user) {
        User _user = userService.save(user);
        return _user;
    }

}

package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    void update(User user);
    void delete(int id);

    List<User> findAll();
    List<User> findByUsername(String username);

}

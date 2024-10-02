package com.example.demo.dao;
import com.example.demo.entity.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    void update(User user);
    void delete(int id);

    List<User> findAll();
    List<User> findByUsername(String username);

}

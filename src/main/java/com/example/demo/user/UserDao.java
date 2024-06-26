package com.example.demo.user;

import java.util.Optional;

public interface UserDao {
    Optional<User> getUserByUsername(String username);

    void save(User user);
}

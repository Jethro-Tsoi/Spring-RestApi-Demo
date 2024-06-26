package com.example.demo.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserJpaDataAccessService")
public class UserJpaDataAccessService implements UserDao {
    private final UserRepository userRepository;

    public UserJpaDataAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

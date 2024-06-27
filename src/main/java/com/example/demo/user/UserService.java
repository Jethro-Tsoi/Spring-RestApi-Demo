package com.example.demo.user;

import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record UserService (
        @Qualifier("UserJpaDataAccessService")
        UserDao userDao
) {
        public User getUserByUsername(String username) {
                log.info("Getting user by username: {}", username);
                return userDao.getUserByUsername(username)
                        .orElseThrow(() -> {
                                log.error("User not found");
                                return new ResourceNotFoundException("User not found");
                        });
        }

        public User addNewUser(UserDto userDto) {
                try {
                        log.info("Adding new user: {}", userDto);
                        userDao.getUserByUsername(userDto.username()).ifPresent(existingUser -> {
                                throw new ResourceAlreadyExistException("Username already taken");
                        });
                        User user = User.builder()
                                .username(userDto.username())
                                .telephone(userDto.telephone())
                                .language(userDto.language())
                                .build();
                        User saved = userDao.save(user);
                        log.info("New user added with id: {}", saved.getId());
                        return saved;
                } catch (Exception e) {
                        log.error("Failed to add new user: {}", e.getMessage());
                        throw e;
                }
        }
}

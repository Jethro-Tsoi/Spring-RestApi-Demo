package com.example.demo.user;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public record UserService (
        @Qualifier("UserJpaDataAccessService")
        UserDao userDao
) {
        public User getUserByUsername(String username) {
                return userDao.getUserByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }

        public void addNewUser(UserDto userDto) {
                User user = User.builder()
                        .username(userDto.username())
                        .telephone(userDto.telephone())
                        .language(userDto.language())
                        .build();
                userDao.save(user);
        }
}

package com.example.demo.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public record UserController (
        UserService userService
) {

    @GetMapping(path = "/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public User registerNewUser(@Validated @RequestBody UserDto userDto) {
        return userService.addNewUser(userDto);
    }
}

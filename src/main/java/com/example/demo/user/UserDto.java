package com.example.demo.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto (
        @Size(max = 20, message = "Username must be less than 20 characters")
        @NotBlank(message = "Username is required")
        String username,

        @NotNull(message = "Telephone is required")
        Integer telephone,

        @Size(max = 20, message = "Language must be less than 20 characters")
        @NotBlank(message = "Language is required")
        String language
) {
}

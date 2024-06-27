package com.example.demo.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto (
        @Size(max = 20)
        @NotBlank
        String username,

        @NotBlank
        Integer telephone,

        @Size(max = 20)
        @NotBlank
        String language
) {
}

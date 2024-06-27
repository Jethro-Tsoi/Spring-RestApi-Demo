package com.example.demo.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserJpaDataAccessServiceTest {

    private UserJpaDataAccessService underTest;
    private AutoCloseable autoCloseable;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserJpaDataAccessService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void isShouldGetUserByUsername() {
        // Given
        String username = "test";
        Optional<User> user = Optional.of(new User(1L, username, 123456789, "English"));
        Mockito.when(userRepository.findByUsername(username)).thenReturn(user);

        // When
        Optional<User> expected = underTest.getUserByUsername(username);

        // Then
        assertThat(expected).isEqualTo(user);
    }

    @Test
    void isShouldSave() {
        // Given
        User user = new User(1L, "test", 123456789, "English");

        // When
        underTest.save(user);

        // Then
        Mockito.verify(userRepository).save(user);

    }

    @Test
    void itShouldReturnEmptyWhenUserNotFound() {
        // Given
        String username = "nonexistent";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When
        Optional<User> expected = underTest.getUserByUsername(username);

        // Then
        assertThat(expected).isEmpty();
        Mockito.verify(userRepository).findByUsername(username);
    }
}
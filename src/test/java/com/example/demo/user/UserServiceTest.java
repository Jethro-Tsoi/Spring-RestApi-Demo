package com.example.demo.user;

import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userDao);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldGetUserByUsername() {
        // Given
        when(userDao.getUserByUsername("test"))
                .thenReturn(
                        Optional.of(new User(1L, "test", 12345678, "English"))
                );
        // When
        User result = underTest.getUserByUsername("test");
        // Then
        verify(userDao).getUserByUsername("test");
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("test");
    }

    @Test
    void itShouldThrowWhenUserNotFound() {
        // Given
        when(userDao.getUserByUsername("test")).thenReturn(Optional.empty());
        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> underTest.getUserByUsername("test"));
    }

    @Test
    void itShouldAddNewUser() {
        // Given
        String username = "test";
        int telephone = 12345678;
        String language = "English";
        UserDto userDto = new UserDto(username, telephone, language);

        User savedUser = new User(1L, username, telephone, language);
        when(userDao.save(any(User.class))).thenReturn(savedUser);

        // When
        User result = underTest.addNewUser(userDto);

        // Then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDao).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getId()).isNull(); // The ID should be null before saving
        assertThat(capturedUser.getUsername()).isEqualTo(username);
        assertThat(capturedUser.getTelephone()).isEqualTo(telephone);
        assertThat(capturedUser.getLanguage()).isEqualTo(language);

        // Verify the saved user
        assertThat(result).isEqualTo(savedUser);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void itShouldThrowWhenAddNewUserWithNullDto() {
        // Given
        UserDto userDto = null;
        // When / Then
        assertThrows(NullPointerException.class, () -> underTest.addNewUser(userDto));
    }

    @Test
    void itShouldThrowWhenAddNewUserWithInvalidDto() {
        // Given
        String username = "test";
        int telephone = 123; // Invalid length
        String language = ""; // Invalid language
        UserDto userDto = new UserDto(username, telephone, language);

        // When / Then
        assertThrows(Exception.class, () -> underTest.addNewUser(userDto));
    }

    @Test
    void itShouldThrowWhenAddNewUserWithExistingUsername() {
        // Given
        String username = "test";
        int telephone = 12345678;
        String language = "English";
        UserDto userDto = new UserDto(username, telephone, language);

        when(userDao.getUserByUsername(username)).thenReturn(Optional.of(new User(2L, username, telephone, language)));

        // When / Then
        assertThrows(ResourceAlreadyExistException.class, () -> underTest.addNewUser(userDto));
        verify(userDao).getUserByUsername(username);
    }
}

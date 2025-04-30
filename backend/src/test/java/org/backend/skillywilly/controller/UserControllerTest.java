package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.User;
import org.backend.skillywilly.service.PasswordService;
import org.backend.skillywilly.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private PasswordService passwordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_ValidInput_SuccessfulRegistration() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");

        User createdUser = new User();
        createdUser.setId(1L);
        createdUser.setUsername("testuser");
        createdUser.setPassword("hashedPassword123");
        createdUser.setEmail("testuser@example.com");

        when(passwordService.hashPassword("password123")).thenReturn("hashedPassword123");
        when(userService.createUser(user)).thenReturn(createdUser);

        ResponseEntity<?> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdUser, response.getBody());
    }

    @Test
    void testCreateUser_InvalidInput_RespondsBadRequest() {
        User user = new User();
        user.setUsername(null);
        user.setPassword(null);
        user.setEmail(null);

        ResponseEntity<?> response = userController.createUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input: Username, password, or email is missing.", response.getBody());
    }

    @Test
    void testCreateUser_ExceptionThrown_RespondsInternalServerError() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setEmail("testuser@example.com");

        when(passwordService.hashPassword("password123")).thenThrow(new RuntimeException("Hashing failed"));

        ResponseEntity<?> response = userController.createUser(user);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error: Hashing failed", response.getBody());
    }
}

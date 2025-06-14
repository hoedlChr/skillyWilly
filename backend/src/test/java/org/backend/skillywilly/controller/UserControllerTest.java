package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.User;
import org.backend.skillywilly.service.EmailService;
import org.backend.skillywilly.service.PasswordService;
import org.backend.skillywilly.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private PasswordService passwordService;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_ValidInput_SuccessfulRegistration() {
        // Setup
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

        // Act
        ResponseEntity<?> response = userController.createUser(user);

        // Assert Status
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Assert Body ist eine Map mit User und Message
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof Map, "Body sollte eine Map sein");

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        // Prüfe den User-Eintrag
        assertEquals(createdUser, body.get("user"));

        // Prüfe die Erfolgsmeldung
        String expectedMsg = "Benutzer erfolgreich erstellt. Bitte überprüfen Sie Ihre E-Mail, um Ihr Konto zu aktivieren.";
        assertEquals(expectedMsg, body.get("message"));
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

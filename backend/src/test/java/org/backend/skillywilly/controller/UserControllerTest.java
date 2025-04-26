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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerifyUser_Success() {
        User user = new User();
        user.setId(1L);
        user.setUsername("validUser");
        user.setPassword("validPassword");

        when(userService.findUserByUsername("validUser")).thenReturn(Optional.of(user));
        when(passwordService.verifyPassword(any(), any())).thenReturn(true);

        ResponseEntity<User> response = userController.verifyUser("validUser", "validPassword");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).findUserByUsername("validUser");
        verify(passwordService, times(1)).verifyPassword(any(), any());
    }

    @Test
    void testVerifyUser_UsernameNotFound() {
        when(userService.findUserByUsername("invalidUser")).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.verifyUser("invalidUser", "anyPassword");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(userService, times(1)).findUserByUsername("invalidUser");
        verifyNoInteractions(passwordService);
    }

    @Test
    void testVerifyUser_PasswordIncorrect() {
        User user = new User();
        user.setId(1L);
        user.setUsername("validUser");
        user.setPassword("hashedPassword");

        when(userService.findUserByUsername("validUser")).thenReturn(Optional.of(user));
        when(passwordService.verifyPassword("hashedPassword", "invalidPassword")).thenReturn(false);

        ResponseEntity<User> response = userController.verifyUser("validUser", "invalidPassword");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(userService, times(1)).findUserByUsername("validUser");
        verify(passwordService, times(1)).verifyPassword("hashedPassword", "invalidPassword");
    }

    @Test
    void testGetAllUsers_Success() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");

        List<User> userList = List.of(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testGetAllUsers_NoUsersFound() {
        when(userService.getAllUsers()).thenReturn(List.of());

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testCreateUser_Success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("plaintextpassword");
        user.setEmail("test@example.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setPassword("hashedpassword");
        savedUser.setEmail("test@example.com");

        when(passwordService.hashPassword("plaintextpassword")).thenReturn("hashedpassword");
        when(userService.createUser(any(User.class))).thenReturn(savedUser);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
        verify(passwordService, times(1)).hashPassword("plaintextpassword");
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void testCreateUser_BadRequest() {
        User user = new User(); // Empty user to trigger bad request

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verifyNoInteractions(passwordService);
        verify(userService, never()).createUser(any(User.class));
    }

    @Test
    void testGetUserById_Success() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userService.getUserById(100L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(100L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(100L);
    }

    @Test
    void testUpdateUser_Success() {
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("updatedusername");
        updatedUser.setEmail("updatedemail@example.com");

        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(Optional.of(updatedUser));

        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
        verify(userService, times(1)).updateUser(eq(1L), any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        User updatedUser = new User();
        updatedUser.setUsername("nonexistentuser");
        updatedUser.setEmail("nonexistentemail@example.com");

        when(userService.updateUser(eq(100L), any(User.class))).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.updateUser(100L, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).updateUser(eq(100L), any(User.class));
    }

    @Test
    void testDeleteUser_Success() {
        when(userService.deleteUser(1L)).thenReturn(true);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userService.deleteUser(100L)).thenReturn(false);

        ResponseEntity<Void> response = userController.deleteUser(100L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).deleteUser(100L);
    }
}
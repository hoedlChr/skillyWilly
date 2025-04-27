package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.User;
import org.backend.skillywilly.service.PasswordService;
import org.backend.skillywilly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordService passwordService;

    // Endpoint to create a new user
    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            // Check if the user object is valid
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
                return new ResponseEntity<>("Invalid input: Username, password, or email is missing.", HttpStatus.BAD_REQUEST);
            }

            // Hash the password before saving the user
            String hashedPassword = passwordService.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            // Create the user via the UserService and return the created user
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

        } catch (Exception e) {
            // Return the exception message in the response
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint to get a single user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUser(id, user);
        return updatedUser.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to verify a user's credentials (username and password)
    @PostMapping("/verify")
    public ResponseEntity<User> verifyUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
        // Retrieve user by username
        Optional<User> userOptional = userService.findUserByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Verify the password
            boolean isPasswordValid = passwordService.verifyPassword(user.getPassword(), password);

            if (isPasswordValid) {
                // Return the user if verification is successful
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }

        // Return unauthorized if user is not found or password doesn't match
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
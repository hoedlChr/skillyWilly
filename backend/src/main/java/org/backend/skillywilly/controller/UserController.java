package org.backend.skillywilly.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.backend.skillywilly.model.User;
import org.backend.skillywilly.service.PasswordService;
import org.backend.skillywilly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;

/**
 * Rest Controller responsible for managing user-related operations.
 * Provides API endpoints for creating, retrieving, updating, and deleting user entities
 * and verifying user credentials.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * An instance of UserService that handles business logic for user-related operations.
     * This service manages the creation, retrieval, updating, and deletion of user entities,
     * as well as user-specific functionality such as finding a user by their username.
     * <p>
     * This field is automatically injected by Spring's dependency injection container.
     */
    @Autowired
    private UserService userService;
    /**
     * An instance of the PasswordService class that handles password-related functionality
     * such as hashing and verifying passwords for user authentication processes.
     * Utilized in the UserController class to ensure secure management of user credentials.
     */
    @Autowired
    private PasswordService passwordService;


    /**
     * Creates a new user account with the given user details.
     * Validates the input, hashes the password, and stores the user in the system.
     *
     * @param user the user object containing details such as username, password, and email
     * @return a {@code ResponseEntity} containing the created user object and a {@code 201 CREATED} status,
     * or an error message with the appropriate HTTP status in case of failure
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
                return new ResponseEntity<>("Invalid input: Username, password, or email is missing.", HttpStatus.BAD_REQUEST);
            }

            String hashedPassword = passwordService.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing a list of all users with an HTTP status of 200 (OK).
     * In the event of an error, returns an appropriate error response.
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Retrieves a user based on the provided user ID.
     *
     * @param id the ID of the user to retrieve
     * @return a {@code ResponseEntity} containing the user object if found, or
     * {@code HttpStatus.NOT_FOUND} if the user does not exist. Returns
     * an appropriate error response if an exception occurs.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Updates an existing user with the given information.
     *
     * @param id   The ID of the user to be updated.
     * @param user The updated information for the user.
     * @return A ResponseEntity containing the updated user and an HTTP status of OK if the update is successful,
     * or an HTTP status of NOT_FOUND if the user with the provided ID does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            Optional<User> updatedUser = userService.updateUser(id, user);
            return updatedUser.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return A {@code ResponseEntity} with the status:
     * {@code HttpStatus.NO_CONTENT} if the user was successfully deleted,
     * {@code HttpStatus.NOT_FOUND} if the user was not found,
     * or an appropriate error response in case of an exception.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            boolean isDeleted = userService.deleteUser(id);
            return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletResponse response) {
        try {
            Optional<User> userOptional = userService.findUserByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (passwordService.verifyPassword(user.getPassword(), password)) {
                    String token = generateJwtToken(user);

                    Cookie authCookie = new Cookie("auth-token", token);
                    authCookie.setHttpOnly(true);
                    authCookie.setSecure(true);
                    authCookie.setPath("/");
                    authCookie.setMaxAge(7 * 24 * 60 * 60);

                    // Direkt das Cookie zur Response hinzufügen
                    response.addCookie(authCookie);

                    // SameSite über Header-Parameter setzen
                    response.setHeader("Set-Cookie",
                            String.format("%s=%s; Max-Age=%d; Path=%s; HttpOnly; Secure; SameSite=Strict",
                                    authCookie.getName(), authCookie.getValue(), authCookie.getMaxAge(),
                                    authCookie.getPath()));

                    user.setPassword(null);

                    return ResponseEntity.ok()
                            .body(new HashMap<String, Object>() {{
                                put("user", user);
                                put("message", "Login erfolgreich");
                            }});
                }
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new HashMap<String, String>() {{
                        put("message", "Ungültige Anmeldedaten");
                    }});

        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie authCookie = new Cookie("auth-token", null);
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(0);

        // Direkt das Cookie zur Response hinzufügen
        response.addCookie(authCookie);

        // SameSite über Header-Parameter setzen
        response.setHeader("Set-Cookie",
                String.format("%s=%s; Max-Age=%d; Path=%s; HttpOnly; Secure; SameSite=Strict",
                        authCookie.getName(), authCookie.getValue(), authCookie.getMaxAge(),
                        authCookie.getPath()));

        return ResponseEntity.ok()
                .body(new HashMap<String, String>() {{
                    put("message", "Erfolgreich ausgeloggt");
                }});
    }


    private String generateJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


    /**
     * Retrieves a list of all usernames.
     *
     * @return a ResponseEntity containing a list of usernames with an HTTP status of 200 (OK).
     * In the event of an error, returns an appropriate error response.
     */
    @GetMapping("/usernames")
    public ResponseEntity<?> getAllUsernames() {
        try {
            List<String> usernames = userService.getAllUsers()
                    .stream()
                    .map(User::getUsername)
                    .toList();
            return new ResponseEntity<>(usernames, HttpStatus.OK);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

}
package org.backend.skillywilly.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.backend.skillywilly.model.User;
import org.backend.skillywilly.service.EmailService;
import org.backend.skillywilly.service.PasswordService;
import org.backend.skillywilly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Key;
import java.sql.Date;
import java.util.*;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private EmailService emailService;

    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @CrossOrigin
    @PostMapping(value = "/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
                return new ResponseEntity<>("Invalid input: Username, password, or email is missing.", HttpStatus.BAD_REQUEST);
            }


            String hashedPassword = passwordService.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);

            String token = UUID.randomUUID().toString();
            user.setVerificationToken(token);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 24);
            user.setTokenExpiryDate(new Date(calendar.getTimeInMillis()));

            user.setVerified(false);

            User createdUser = userService.createUser(user);

            emailService.sendVerificationEmail(user.getEmail(), token);

            createdUser.setPassword(null);
            createdUser.setVerificationToken(null);

            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("user", createdUser);
                        put("message", "Benutzer erfolgreich erstellt. Bitte überprüfen Sie Ihre E-Mail, um Ihr Konto zu aktivieren.");
                    }},
                    HttpStatus.CREATED
            );

        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getAllUsers(@CookieValue(name = "auth-token", required = false) String token,
                                         HttpServletResponse response) {
        try {
            if (token == null || token.isEmpty() || !validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new HashMap<String, String>() {{
                            put("message", "Nicht autorisiert: Bitte melden Sie sich an");
                        }});
            }

            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
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
    @GetMapping("/verify")
    public RedirectView verifyEmail(@RequestParam String token) {
        boolean ok = userService.verifyUserEmail(token) != null;
        return new RedirectView(ok
                ? "/verify_success.html"
                : "/verify_error.html");
    }

    @CrossOrigin
    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyAuthToken(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new HashMap<String, String>() {{
                            put("message", "Kein Auth-Token gefunden");
                        }});
            }

            String token = null;
            for (Cookie cookie : cookies) {
                if ("auth-token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }

            if (token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new HashMap<String, String>() {{
                            put("message", "Kein Auth-Token gefunden");
                        }});
            }

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                Long userId = claims.get("userId", Long.class);

                Optional<User> userOptional = userService.getUserById(userId);
                if (!userOptional.isPresent() || !userOptional.get().getId().equals(userId)) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new HashMap<String, String>() {{
                                put("message", "Ungültiges Token");
                            }});
                }

                User user = userOptional.get();
                user.setPassword(null);

                return ResponseEntity.ok()
                        .body(new HashMap<String, Object>() {{
                            put("user", user);
                            put("message", "Token ist gültig");
                        }});

            } catch (ExpiredJwtException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new HashMap<String, String>() {{
                            put("message", "Token ist abgelaufen");
                        }});
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new HashMap<String, String>() {{
                            put("message", "Ungültiges Token");
                        }});
            }

        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }


    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       HttpServletResponse response) {
        try {
            Optional<User> userOptional = userService.findUserByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (!user.isVerified()) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new HashMap<String, String>() {{
                                put("message", "Bitte bestätigen Sie zuerst Ihre E-Mail-Adresse");
                            }});
                }

                if (passwordService.verifyPassword(user.getPassword(), password)) {
                    String token = generateJwtToken(user);

                    Cookie authCookie = new Cookie("auth-token", token);
                    authCookie.setHttpOnly(true);
                    authCookie.setSecure(true);
                    authCookie.setPath("/");
                    authCookie.setMaxAge(7 * 24 * 60 * 60);

                    response.addCookie(authCookie);

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

        response.addCookie(authCookie);

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
        return SIGNING_KEY;
    }

    private boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return !claims.getExpiration().before(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            return false;
        }
    }

    @CrossOrigin
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

    @PostMapping(value = "/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (email == null || email.isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("E-Mail darf nicht leer sein");
        }

        return userService.findByEmail(email)
                .map(user -> {
                    String rawPw = UUID.randomUUID().toString().substring(0, 8);
                    user.setPassword(passwordService.hashPassword(rawPw));
                    userService.save(user);

                    String subject = "Ihr neues Passwort";
                    String text = String.format(
                            "Hallo %s,%n%n" +
                                    "Ihr Passwort wurde zurückgesetzt. Ihr neues Passwort lautet:%n%n" +
                                    "%s%n%n" +
                                    "Bitte ändern Sie es nach dem ersten Login.%n%n" +
                                    "Viele Grüße,%nIhr Support-Team",
                            user.getUsername(), rawPw
                    );
                    emailService.sendSimpleMessage(email, subject, text);

                    return new ResponseEntity<>(email, HttpStatus.OK);
                })
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body("User mit dieser E-Mail nicht gefunden")
                );
    }
}
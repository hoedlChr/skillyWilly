package org.backend.skillywilly.service;

import org.backend.skillywilly.model.User;
import org.backend.skillywilly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setLocation(user.getLocation());
                    return userRepository.save(existingUser);
                });
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    userRepository.delete(existingUser);
                    return true;
                }).orElse(false);
    }

    /**
     * Verifies a username and a plain-text password.
     *
     * @param username The username to verify.
     * @param password The plain-text password to check.
     * @return true if username and password match, false otherwise.
     */
    public boolean verifyUser(String username, String password) {
        // Fetch the user from the database
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return false; // If user doesn't exist, return false
        }
        // Use PasswordService to verify the plain-text password against the hashed password
        return user.map(u -> passwordService.verifyPassword(password, u.getPassword())).orElse(false);
    }

    public Optional<User> findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username); // Beschreibender Variablename
        return userOptional;
    }


}

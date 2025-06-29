package org.backend.skillywilly.service;

import org.backend.skillywilly.model.User;
import org.backend.skillywilly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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


    public Optional<User> findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional;
    }

    public User verifyUserEmail(String token) {
        User user = userRepository.findByVerificationToken(token)
                .orElse(null);

        if (user == null) {
            return null;
        }


        if (user.getTokenExpiryDate().before(new Date(System.currentTimeMillis()))) {
            return null;
        }


        user.setVerified(true);
        user.setVerificationToken(null);
        user.setTokenExpiryDate(null);

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

}

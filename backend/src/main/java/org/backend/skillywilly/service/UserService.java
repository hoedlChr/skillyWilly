package org.backend.skillywilly.service;

import org.backend.skillywilly.model.User;
import org.backend.skillywilly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing user-related operations.
 * <p>
 * This class provides methods to handle business logic for creating, retrieving, updating,
 * and deleting {@link User} entities. It acts as an intermediary between the controller
 * layer and the data access layer, encapsulating interactions with the {@link UserRepository}.
 * <p>
 * The class is annotated with {@code @Service}, making it a Spring-managed component
 * suitable for dependency injection.
 */
@Service
public class UserService {

    /**
     * Repository instance used for managing user-related operations in the database.
     * <p>
     * This field provides an interface for performing CRUD (Create, Read, Update, Delete)
     * operations and additional custom queries specific to {@link User} entities. It is
     * automatically injected by Spring's Dependency Injection mechanism.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new User entity in the database.
     *
     * @param user the User object to be created and persisted; must not be null.
     * @return the persisted User object including the generated ID and any updates made during persistence.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all User entities available in the repository
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to be retrieved
     * @return an Optional containing the User if found, otherwise an empty Optional
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Updates an existing user in the system by their ID. If the user with the specified ID exists,
     * the method updates their username, email, password, and location based on the provided user details.
     *
     * @param id   the unique identifier of the user to update
     * @param user the user details containing the updated username, email, password, and location
     * @return an {@code Optional} containing the updated {@code User} if the user with the given ID exists,
     * or an empty {@code Optional} if no such user is found
     */
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

    /**
     * Deletes a user from the repository based on their unique identifier.
     * <p>
     * The method attempts to locate a user with the provided ID in the repository.
     * If a user is found, it deletes the user and returns true. If no user is found,
     * it returns false.
     *
     * @param id the unique identifier of the user to be deleted
     * @return true if the user was successfully deleted, false if the user was not found
     */
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    userRepository.delete(existingUser);
                    return true;
                }).orElse(false);
    }


    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user to search for. Must not be null.
     * @return an {@link Optional} containing the user if found, or an empty {@link Optional} if no user is found with the provided username.
     */
    public Optional<User> findUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username); // Beschreibender Variablename
        return userOptional;
    }
}

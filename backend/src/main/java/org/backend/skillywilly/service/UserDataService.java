package org.backend.skillywilly.service;

import org.backend.skillywilly.model.UserData;
import org.backend.skillywilly.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing `UserData` entities. This class provides business logic
 * to interact with the persistence layer, handling CRUD operations for `UserData`
 * records through the integration of Spring Data JPA.
 * <p>
 * Responsibilities:
 * - Retrieve all `UserData` records or a specific record by its unique identifier.
 * - Create and save new `UserData` instances into the repository.
 * - Delete `UserData` records by their unique identifier.
 * <p>
 * Dependencies:
 * - Relies on `UserDataRepository` for persistence operations, which is injected
 * using Spring's dependency injection mechanism.
 * <p>
 * Annotations:
 * - `@Service`: Indicates that this class is a Spring-managed service component used
 * for implementing business logic and facilitating interaction with the repository layer.
 */
@Service
public class UserDataService {

    /**
     * Repository instance that provides access to the persistence layer for `UserData` entities.
     * This is used to perform CRUD operations and facilitate interaction with the database,
     * leveraging Spring Data JPA capabilities.
     * <p>
     * Responsibilities:
     * - Retrieve all `UserData` records or a specific record by its ID.
     * - Save new `UserData` instances or update existing ones.
     * - Delete `UserData` records based on their ID.
     * <p>
     * Dependency Injection:
     * - Automatically injected into the containing service class using Spring's `@Autowired` annotation.
     */
    private final UserDataRepository userDataRepository;

    /**
     * Constructs a new UserDataService instance and initializes it with the provided UserDataRepository.
     *
     * @param userDataRepository the repository used for managing and accessing UserData entities
     */
    @Autowired
    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    /**
     * Retrieves a list of all UserData objects from the repository.
     *
     * @return a list of UserData entities available in the repository
     */
    public List<UserData> getAllUserData() {
        return userDataRepository.findAll();
    }

    /**
     * Retrieves user data by its unique identifier.
     *
     * @param id the unique identifier of the user data to be retrieved
     * @return the {@code UserData} object corresponding to the specified id
     * @throws RuntimeException if no user data is found with the specified id
     */
    public UserData getUserDataById(Long id) {
        return userDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found"));
    }

    /**
     * Creates and saves a new UserData entity in the repository.
     *
     * @param userData the UserData object to be created and saved
     * @return the saved UserData object
     */
    public UserData createUserData(UserData userData) {
        return userDataRepository.save(userData);
    }

    /**
     * Deletes user data by its unique identifier.
     * <p>
     * This method removes the user data associated with the provided ID
     * from the repository.
     *
     * @param id The unique identifier of the user data to be deleted.
     */
    public void deleteUserData(Long id) {
        userDataRepository.deleteById(id);
    }
}
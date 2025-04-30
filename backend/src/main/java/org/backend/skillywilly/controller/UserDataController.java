package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.UserData;
import org.backend.skillywilly.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;

/**
 * UserDataController handles HTTP REST requests related to user data operations.
 * This controller provides endpoints to manage user data including retrieving all users,
 * fetching a user by ID, creating new user data, and deleting user data.
 * <p>
 * The controller interacts with the UserDataService to perform CRUD operations on the UserData entity.
 */
@RestController
@RequestMapping("/api/userdata")
public class UserDataController {

    /**
     * Service layer bean for managing user data operations.
     * Provides methods to retrieve, create, and delete user data by interacting with the database.
     * This is an autowired dependency in the controller to handle the business logic.
     */
    @Autowired
    private UserDataService userDataService;

    /**
     * Retrieves all user data from the system and returns it as a response.
     *
     * @return a ResponseEntity containing a list of all UserData objects if the operation is successful,
     * or an appropriate error response if an exception occurs.
     */
    @GetMapping
    public ResponseEntity<?> getAllUserData() {
        try {
            List<UserData> users = userDataService.getAllUserData();
            return createOkResponse(users);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Retrieves user data for the specified user ID.
     *
     * @param id the ID of the user whose data is to be retrieved
     * @return a {@code ResponseEntity} containing the user data if found, or a {@code ResponseEntity}
     * with a 404 Not Found status if the user is not found. In case of an exception, returns
     * a {@code ResponseEntity} with a 500 Internal Server Error status and an error message.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDataById(@PathVariable Long id) {
        try {
            UserData userData = userDataService.getUserDataById(id);
            return createOkResponse(userData);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Creates a new user data entry in the system.
     *
     * @param userData The user data object containing the details to be created.
     * @return A ResponseEntity containing the created UserData object and an HTTP status of 200
     * if successful, or an error response with an appropriate HTTP status if an exception occurs.
     */
    @PostMapping
    public ResponseEntity<?> createUserData(@RequestBody UserData userData) {
        try {
            UserData createdUserData = userDataService.createUserData(userData);
            return createOkResponse(createdUserData);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Deletes the user data associated with the specified ID.
     *
     * @param id the ID of the user data to be deleted
     * @return a ResponseEntity indicating the result of the operation:
     * - 204 No Content if the user data is successfully deleted
     * - 404 Not Found if no user data is found with the specified ID
     * - 500 Internal Server Error for any unexpected exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserData(@PathVariable Long id) {
        try {
            userDataService.deleteUserData(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }
}
package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.UserData;
import org.backend.skillywilly.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;

@RestController
@RequestMapping("/api/userdata")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getAllUserData() {
        try {
            List<UserData> users = userDataService.getAllUserData();
            return createOkResponse(users);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
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

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createUserData(@RequestBody UserData userData) {
        try {
            UserData createdUserData = userDataService.createUserData(userData);
            return createOkResponse(createdUserData);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserData(@PathVariable Long id) {
        try {
            userDataService.deleteUserData(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }
}
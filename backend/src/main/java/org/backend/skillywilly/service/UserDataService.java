package org.backend.skillywilly.service;

import org.backend.skillywilly.model.UserData;
import org.backend.skillywilly.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public List<UserData> getAllUserData() {
        return userDataRepository.findAll();
    }

    public UserData getUserDataById(Long id) {
        return userDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID: " + id + " not found"));
    }

    public UserData createUserData(UserData userData) {
        return userDataRepository.save(userData);
    }

    public void deleteUserData(Long id) {
        userDataRepository.deleteById(id);
    }
}
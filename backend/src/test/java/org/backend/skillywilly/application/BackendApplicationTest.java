package org.backend.skillywilly.application;

import org.backend.skillywilly.BackendApplication;
import org.backend.skillywilly.model.User;
import org.backend.skillywilly.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class BackendApplicationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testDatabaseConnection() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            System.out.println("no users found");
        } else {
            users.forEach(user -> System.out.println("User: " + user.getId() + ": " + user.getName()));
        }
    }
}
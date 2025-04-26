package org.backend.skillywilly.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

/**
 * Service class for handling password hashing and verification.
 * Utilizes the Argon2 hashing algorithm for secure password storage and validation.
 */
@Service
public class PasswordService {
    private final Argon2 argon2 = Argon2Factory.create();

    /**
     * Hashes a provided plaintext password using the Argon2 hashing algorithm.
     *
     * @param password the plaintext password to be hashed
     * @return the hashed password as a String
     */
    public String hashPassword(String password) {
        return argon2.hash(2, 65536, 1, password.toCharArray());
    }

    /**
     * Verifies if the provided plaintext password matches the given hashed password.
     * Utilizes the Argon2 hash verification method for secure comparison.
     *
     * @param hash the hashed password to be verified
     * @param password the plaintext password to be checked
     * @return true if the plaintext password matches the hashed password, false otherwise
     */
    public boolean verifyPassword(String hash, String password) {
        return argon2.verify(hash, password.toCharArray());
    }
}
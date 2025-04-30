package org.backend.skillywilly.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * A utility class that provides general helper methods for creating standardized HTTP responses.
 */
public class GeneralHelper {
    /**
     * Creates an HTTP response entity with the HTTP status set to 200 OK.
     *
     * @param <T>  the type of the response body
     * @param body the body of the response to be included in the ResponseEntity
     * @return a ResponseEntity object containing the given body and an HTTP status of 200 OK
     */
    public static <T> ResponseEntity<T> createOkResponse(T body) {
        return ResponseEntity.ok(body);
    }

    /**
     * Creates a standardized HTTP response for exceptions.
     *
     * @param e the exception for which the response is to be created
     * @return a ResponseEntity containing the exception message and an HTTP status of INTERNAL_SERVER_ERROR
     */
    public static ResponseEntity<String> createExceptionResponse(Exception e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package org.backend.skillywilly.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GeneralHelper {
    public static <T> ResponseEntity<T> createOkResponse(T body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<String> createExceptionResponse(Exception e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

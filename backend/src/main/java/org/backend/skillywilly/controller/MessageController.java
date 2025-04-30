package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.Message;
import org.backend.skillywilly.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;


/**
 * A REST controller responsible for handling HTTP requests related to messages.
 * Provides endpoints for CRUD operations on messages.
 * All endpoints return an appropriate HTTP response indicating the outcome.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    /**
     * The MessageService instance that provides business logic operations
     * and interaction with the data layer for handling messages.
     * It is injected into the controller using Spring's @Autowired annotation.
     */
    @Autowired
    private MessageService messageService;


    /**
     * Retrieves all messages from the data source.
     *
     * @return ResponseEntity containing a list of messages if successful, or an appropriate error response in case of an exception.
     */
    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        try {
            return createOkResponse(messageService.getAllMessages());
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Retrieves a message by its unique identifier.
     *
     * @param id the unique identifier of the message to be retrieved
     * @return a ResponseEntity containing the message if found,
     * a 404 NOT FOUND response if the message does not exist,
     * or a 500 INTERNAL SERVER ERROR response if an unexpected error occurs
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getMessageById(@PathVariable Long id) {
        try {
            return createOkResponse(messageService.getMessageById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Handles the creation of a new message.
     * Accepts a Message object in the request body, processes it through the service layer,
     * and returns an appropriate HTTP response.
     *
     * @param message the message object to be created, containing sender, recipient, and content details
     * @return a ResponseEntity containing the created message in the response body if successful,
     * or an error message and appropriate HTTP status in case of failure
     */
    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        try {
            return createOkResponse(messageService.createMessage(message));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Deletes a specific message identified by its unique ID.
     * If the message is found and successfully deleted, a HTTP 204 (No Content) response is returned.
     * If the message is not found, a HTTP 404 (Not Found) response is returned.
     * In case of an unexpected error, a generic error response is returned.
     *
     * @param id the unique ID of the message to be deleted
     * @return a {@code ResponseEntity} that indicates the result of the delete operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        try {
            messageService.deleteMessage(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }
}
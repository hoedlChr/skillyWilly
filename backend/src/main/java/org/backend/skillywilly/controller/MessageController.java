package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.Message;
import org.backend.skillywilly.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;


@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;


    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getAllMessages() {
        try {
            return createOkResponse(messageService.getAllMessages());
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
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

    @CrossOrigin
    @PostMapping("/{fromUserId}/{toUserId}")
    public ResponseEntity<?> createMessage(
            @PathVariable Long fromUserId,
            @PathVariable Long toUserId,
            @RequestBody String messageContent) {
        try {
            Message message = new Message();
            message.setUserFromId(fromUserId);
            message.setUserToId(toUserId);
            message.setMessage(messageContent);
            message.setCreated(new Timestamp(System.currentTimeMillis()));
            
            return createOkResponse(messageService.createMessage(message));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }


    @CrossOrigin
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

    @CrossOrigin
    @GetMapping("/all/{userId}")
    public ResponseEntity<?> getAllUserMessages(@PathVariable("userId") Long userId) {
        try {
            List<Message> messages = messageService.getAllUserMessages(userId);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }


}
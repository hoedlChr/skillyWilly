package org.backend.skillywilly.service;

import org.backend.skillywilly.model.Message;
import org.backend.skillywilly.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing {@link Message} entities.
 * <p>
 * Provides application-level business logic for handling messages, including
 * retrieving, creating, and deleting Message entities. This service acts as an
 * intermediary between the {@link MessageRepository} and the higher layers of
 * the application to ensure proper data handling and interaction with the
 * database layer.
 */
@Service
public class MessageService {

    /**
     * Repository instance used for managing {@link Message} entities.
     * <p>
     * Provides an interface for performing CRUD operations and interacting with the
     * database layer to handle persistence of Message data. It is injected into the
     * service class to facilitate database access for message-related operations.
     */
    private final MessageRepository messageRepository;

    /**
     * Constructs a new instance of {@code MessageService}.
     * <p>
     * Initializes the service with the provided {@link MessageRepository} instance,
     * which is used for interacting with the database layer to manage {@link Message} entities.
     *
     * @param messageRepository the repository used for data access and persistence operations
     */
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Retrieves all messages from the message repository.
     *
     * @return a list of all messages
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Retrieves a message by its ID.
     * If no message is found with the provided ID, a {@link RuntimeException} is thrown.
     *
     * @param id The unique identifier of the message to retrieve.
     * @return The message associated with the specified ID.
     * @throws RuntimeException if no message is found with the given ID.
     */
    public Message getMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
    }

    /**
     * Persists a new Message entity in the database.
     *
     * @param message the Message object to be created and saved in the database
     * @return the saved Message object with any auto-generated fields populated
     */
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    /**
     * Deletes a message by its unique identifier from the repository.
     *
     * @param id the unique identifier of the message to be deleted
     */
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    /**
     * Retrieves all messages that a specific user has sent or received.
     * Messages are ordered by creation timestamp, with the most recent messages first.
     *
     * @param userId ID of the user whose messages should be retrieved
     * @return List of messages where the specified user is either sender or recipient
     * @throws RuntimeException if the user doesn't exist or if there's an error retrieving the messages
     */
    public List<Message> getAllUserMessages(Long userId) {
        return messageRepository.findAllUserMessages(userId);
    }

}

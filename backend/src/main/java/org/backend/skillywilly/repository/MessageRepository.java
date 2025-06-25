package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.userFromId = :userId OR m.userToId = :userId " +
            "ORDER BY m.created DESC")
    List<Message> findAllUserMessages(@Param("userId") Long userId);
}


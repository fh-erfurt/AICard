package de.aicard.storages;

import de.aicard.domains.Social.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findBySender(String sender);

}

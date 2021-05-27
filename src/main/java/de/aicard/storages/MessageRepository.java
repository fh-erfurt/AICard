package de.aicard.storages;

import de.aicard.domains.Social.Message;
import de.aicard.domains.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findBySender(Account sender);

}

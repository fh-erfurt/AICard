package de.aicard.storages;


import de.aicard.domains.Social.Chat;
import de.aicard.domains.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findByParticipants(Account participant);
}

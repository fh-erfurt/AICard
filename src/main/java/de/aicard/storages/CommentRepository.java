package de.aicard.storages;

import de.aicard.domains.Social.Comment;
import de.aicard.domains.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findBySender(Account sender);

}

package de.aicard.storages;

import de.aicard.domains.Social.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO only in beans remove?
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

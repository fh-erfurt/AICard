package de.aicard.storages;

import de.aicard.domains.learnset.LearningSession;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LearningSessionRepository extends JpaRepository<LearningSession, Long>
{

}

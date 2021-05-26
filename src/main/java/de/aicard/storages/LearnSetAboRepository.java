package de.aicard.storages;

import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LearnSetAboRepository extends JpaRepository<LearnSetAbo, Long> {

}

package de.aicard.storages;

import de.aicard.domains.learnset.LearnSet;
import org.springframework.data.jpa.repository.JpaRepository;



public interface LearnSetRepository extends JpaRepository<LearnSet, Long> {

}

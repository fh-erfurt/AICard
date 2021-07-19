package de.aicard.storages;

import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LearnSetAboRepository extends JpaRepository<LearnSetAbo, Long> {
    
    
    @Query(nativeQuery = true, value = "" +
            "SELECT * " +
            "FROM LEARNSETABO as l " +
            "JOIN ACCOUNT_LEARNSETABO as AL on AL.FAVORITELEARNSTETS_ID = l.ID "+
            "WHERE id = ?1")
    List<LearnSetAbo> findLearnSetAboByAccountId(Long accountID);
}

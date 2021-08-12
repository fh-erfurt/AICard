package de.aicard.storages;

import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface LearnSetAboRepository extends JpaRepository<LearnSetAbo, Long> {
    
    
    @Query(nativeQuery = true, value = "" +
            "SELECT * " +
            "FROM LEARNSETABO as l " +
            "JOIN ACCOUNT_LEARNSETABO as AL on AL.FAVORITELEARNSETS_ID = l.ID "+
            "WHERE id = ?1")
    List<LearnSetAbo> findLearnSetAboByAccountId(Long accountID);

    List<LearnSetAbo> findAllByLearnSetId(Long learnSetId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "DELETE FROM LEARNSETABO WHERE LEARNSET_ID = :id ; ")
    void deleteAllByLearnSet(@Param("id") Long id);

//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true,value = "DELETE FROM LEARNSETABO WHERE ID = :id ; ")
//    void deleteById(@Param("id") Long id);
}

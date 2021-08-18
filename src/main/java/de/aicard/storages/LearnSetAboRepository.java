package de.aicard.storages;

import de.aicard.domains.learnset.LearnSetAbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LearnSetAboRepository extends JpaRepository<LearnSetAbo, Long> {

    /**
     * find all learnsetabos to a given learnset by learnsetId
     *
     * @param learnSetId learnSetId
     * @return learnsetabosList
     */
    @Query(nativeQuery = true, value = "SELECT * FROM LEARNSETABO WHERE LEARNSET_ID = :id ;")
    List<LearnSetAbo> findAllByLearnSetId(@Param("id") Long learnSetId);

}

package de.aicard.storages;

import de.aicard.domains.learnset.LearnSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface LearnSetRepository extends JpaRepository<LearnSet, Long>
{
    @Query(nativeQuery = true, value = "" +
            "SELECT * "+
            "FROM LEARNSET as ls " +
            "JOIN CARDLIST_CARD as CC on ls.CARDLIST_ID = CC.CARDLIST_ID " +
            "where cc.LISTOFCARDS_ID = ?1")
    Optional<LearnSet> getLearnSetByCardId(Long cardId);

}

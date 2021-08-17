package de.aicard.storages;

import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSet;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface LearnSetRepository extends JpaRepository<LearnSet, Long>
{


    @Query(nativeQuery = true, value = "" +
            "SELECT * "+
            "FROM LEARNSET as ls " +
            "JOIN CARDLIST_CARD as CC on ls.CARDLIST_ID = CC.CARDLIST_ID " +
            "where cc.LISTOFCARDS_ID = ?1")
    Optional<LearnSet> getLearnSetByCardId(Long cardId);
    //check for usages
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "DELETE FROM LEARNSET WHERE ID = :id ;")
    void deleteById(@Param("id") Long id);

}

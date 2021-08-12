package de.aicard.storages;

import de.aicard.domains.card.CardStatus;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CardStatusRepository extends JpaRepository<CardStatus, Long>
{
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM CARDSTATUS WHERE ID = :id ; ")
    void deleteCardStatusById(@Param("id") Long id);
    
    @Query(nativeQuery = true, value = "SELECT ID FROM CARDSTATUS where CARD_ID = :id1 ;")
    List<Long> findCardStatusIdByCardId(@Param("id1") Long cardId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value="DELETE FROM LEARNINGSESSION_CARDSTATUS where CARDSTATUSLIST_ID = :id ;"+
            "DELETE  FROM LEARNSETABO_CARDSTATUS WHERE CARDSTATUS_ID = :id ;"+
            "DELETE  FROM CARDSTATUS WHERE ID = :id ;")
    void deleteCardStatusesByCardID(@Param("id")Long id);
}

package de.aicard.storages;
import de.aicard.domains.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CardRepository extends JpaRepository<Card, Long> {
    //TODO mach mal sod dass nur eine karte reingegeben wird
    // TODO es sollte auch mit der automatisch generierten Methode delete gehen bei der man 1 Card mitgibt
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DELETE FROM CARDLIST_CARD WHERE LISTOFCARDS_ID = :id ; " +
            "DELETE FROM CARD WHERE ID = :id ; "+
            "DELETE FROM CARDCONTENT WHERE ID = :id1 or ID = :id2 ; "
    )
    void deleteById(@Param("id") Long Id, @Param("id1") Long id1,@Param("id2") Long id2);

}

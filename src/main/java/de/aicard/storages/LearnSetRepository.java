package de.aicard.storages;

import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSet;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LearnSetRepository extends JpaRepository<LearnSet, Long>
{
    //Learnset -> LearnSetaccount(adminliste) -> account
    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM LEARNSET as l " +
            "JOIN LEARNSET_ACCOUNT as la " +
            "on l.ID = la.LEARNSET_ID " +
            "WHERE la.ADMINLIST_ID = ?1")
    List<LearnSet> findAdminLearnsets(Long adminID);

    
    
    @Query(nativeQuery = true, value = "" +
            "SELECT * " +
            "FROM LEARNSET as l " +
            "JOIN LEARNSETABO as L2 on l.ID = L2.LEARNSET_ID "+
            "join ACCOUNT_LEARNSETABO as al on L2.ID = al.FAVORITELEARNSETS_ID " +
            "WHERE al.ACCOUNT_ID = ?1")
    List<LearnSet> findFollwedLearnsets(Long accountID);
    
}

package de.aicard.storages;

import de.aicard.domains.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>
{
    /**
     * find all accounts that have learnsetAbos with a given learnset
     *
     * @param id learnsetsaboId
     * @return accountList
     */
    @Query(nativeQuery = true, value = "SELECT * FROM ACCOUNT A JOIN ACCOUNT_LEARNSETABO AL on A.ID = AL.ACCOUNT_ID JOIN LEARNSETABO LA on AL.LEARNSETABOS_ID = LA.ID WHERE LA.LEARNSET_ID = :id ;")
    List<Account> findAllAccountsByLearnsetIdInLearnSetAbo(@Param("id") Long id);
    
    /**
     * find one account by email (unique)
     *
     * @param email email
     * @return account
     */
    Optional<Account> findByEmail(String email);
    
}

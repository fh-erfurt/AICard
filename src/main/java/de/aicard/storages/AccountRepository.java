package de.aicard.storages;

import de.aicard.domains.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>
{

    Optional<Account> findByName(String name);
    Optional<Account> findByEmail(String email);

}

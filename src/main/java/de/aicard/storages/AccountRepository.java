package de.aicard.storages;

import de.aicard.domains.account.Account;
import de.aicard.core.H2Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByName(String name);
}

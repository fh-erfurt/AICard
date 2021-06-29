package de.aicard.storages;

import de.aicard.domains.account.Account;
import de.aicard.core.H2Controller;
import de.aicard.domains.account.Professor;
import de.aicard.domains.account.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;


public interface AccountRepository extends JpaRepository<Account, Long>
{
    @Query(nativeQuery = true, value = "SELECT * FROM ACCOUN")
    List<Account> findAccount();
    
    Student findStudentById(Long id);
    Professor findProfessorById(Long id);
    Optional<Account> findByName(String name);
    Optional<Account> findByEmail(String email);
    
    // is used to determain what if Account is Student or Professor
    List<Account> findAllById(Long id);

}

package de.aicard.storages;

import de.aicard.domains.account.Account;
import de.aicard.domains.enums.AcademicGrade;
import de.aicard.domains.enums.Faculty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test class for the functions of Account
 *
 * @author Clemens Berger
 */
@DataJpaTest
public class AccountRepositoryTest
{
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//        accountRepository.deleteAll();
//    }
//
//   // String _email, String _password, String _name, String _description,int _semester,Faculty _faculty)
//    @Test
//    void save() {
//        // GIVEN
//        Account given = new Student("student@mail.de","1234","Markus Mustermann","ich bin sehr bescheiden",3,Faculty.APPLIED_COMPUTER_SCIENCE);
//
//        // WHEN
//        Account result = accountRepository.save(given);
//        Long resultID = result.getId();
//
//        // THEN
//        Assertions.assertTrue(resultID != null && resultID > 0);
//    }
////String _email, String _password, String _name, String _description, AcademicGrade _academic
//    @Test
//    void findAll(){
//        Account given1 = new Student("student@mail.de","1234","Markus Mustermann","ich bin ein anderer",3,Faculty.APPLIED_COMPUTER_SCIENCE);
//        Account given2 = new Professor("prof@mail.de","5678","Marion Winters","bin ein Lehrer",AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
//
//        List<Long> idsOfPersisted = new ArrayList<>();
//        Account saved1 = accountRepository.save(given1);
//        Account saved2 = accountRepository.save(given2);
//        idsOfPersisted.add(saved1.getId());
//        idsOfPersisted.add(saved2.getId());
//
//        List<Account> result = accountRepository.findAll();
//
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertFalse(idsOfPersisted.isEmpty());
//    }
//    @Test
//    void findByName(){
//        Account given1 = new Student("student@mail.de","1234","Markus Mustermann","ich bin ein anderer",3,Faculty.APPLIED_COMPUTER_SCIENCE);
//        Account given2 = new Professor("prof@mail.de","5678","Marion Winters","bin ein Lehrer",AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
//
//        accountRepository.save(given1);
//        accountRepository.save(given2);
//
//        Optional<Account> result = accountRepository.findByName("Markus Mustermann");
//
//        Assertions.assertTrue(result.isPresent());
//        Assertions.assertEquals(result.get().getName(),"Markus Mustermann");
//    }
}
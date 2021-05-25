package de.aicard.storages;

import de.aicard.domains.account.Account;
import de.aicard.domains.account.Professor;
import de.aicard.domains.account.Student;
import de.aicard.domains.enums.AcademicGrade;
import de.aicard.domains.enums.Faculty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test class for the functions of Account
 *
 * @author Clemens Berger
 */
public class AccountRepositoryTest
{
    AccountRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new AccountRepository();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

   // String _email, String _password, String _name, String _description,int _semester,Faculty _faculty)
    @Test
    void save() {
        // GIVEN
        Account given = new Student("student@mail.de","1234","Markus Mustermann","ich bin sehr bescheiden",3,Faculty.APPLIED_COMPUTER_SCIENCE);

        // WHEN
        Long result = repository.save(given);

        // THEN
        Assertions.assertTrue(result != null && result > 0);
    }
//String _email, String _password, String _name, String _description, AcademicGrade _academic
    @Test
    void findAll(){
        Account given1 = new Student("student@mail.de","1234","Markus Mustermann","ich bin ein anderer",3,Faculty.APPLIED_COMPUTER_SCIENCE);
        Account given2 = new Professor("prof@mail.de","5678","Marion Winters","bin ein Lehrer",AcademicGrade.UNIVERSITY_PROFESSOR);

        List<Long> idsOfPersisted = new ArrayList<>();
        idsOfPersisted.add(repository.save(given1));
        idsOfPersisted.add(repository.save(given2));

        List<Account> result = repository.findAll();

        Assertions.assertTrue(result != null && !result.isEmpty());
        Assertions.assertFalse(idsOfPersisted.isEmpty());
    }
    @Test
    void findbyName(){
        Account given1 = new Student("student@mail.de","1234","Markus Mustermann","ich bin ein anderer",3,Faculty.APPLIED_COMPUTER_SCIENCE);
        Account given2 = new Professor("prof@mail.de","5678","Marion Winters","bin ein Lehrer",AcademicGrade.UNIVERSITY_PROFESSOR);

        repository.save(given1);
        repository.save(given2);

        Optional<Account> result = repository.findBy("Markus Mustermann");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get().getName(),"Markus Mustermann");
    }
}
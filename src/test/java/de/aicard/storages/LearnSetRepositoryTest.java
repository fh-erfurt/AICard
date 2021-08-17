package de.aicard.storages;

import de.aicard.domains.learnset.LearnSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the functions of LearnSetRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class LearnSetRepositoryTest
{
    
    // TODO : test save(), getLearnSetByCardId()
    
    @Autowired
    private LearnSetRepository learnSetRepository;


    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {

        learnSetRepository.deleteAll();
    }


    @Test
    void save() {
        // GIVEN
        LearnSet given = new LearnSet();

        // WHEN
        LearnSet result = learnSetRepository.save(given);
        Long resultID = result.getId();

        // THEN
        Assertions.assertTrue(resultID != null && resultID > 0);
    }

    @Test
    void findAll(){
        //given
        LearnSet given1 = new LearnSet();
        LearnSet given2 = new LearnSet();

        List<Long> idsOfPersisted = new ArrayList<>();
        LearnSet saved1 = learnSetRepository.save(given1);
        LearnSet saved2 = learnSetRepository.save(given2);
        idsOfPersisted.add(saved1.getId());
        idsOfPersisted.add(saved2.getId());

        List<LearnSet> result;
        result = learnSetRepository.findAll();

        Assertions.assertTrue(result != null && !result.isEmpty());
        Assertions.assertFalse(idsOfPersisted.isEmpty());
    }

}
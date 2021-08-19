package de.aicard.storages;

import de.aicard.domains.learnset.LearnSetAbo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the functions of LearnSetAboRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class LearnSetAboRepositoryTest
{
    
    
    @Autowired
    private LearnSetAboRepository learnSetAboRepository;
    
    
    @BeforeEach
    public void beforeEach()
    {
    
    }
    
    @AfterEach
    public void afterEach()
    {
        
        learnSetAboRepository.deleteAll();
    }
    
    
    @Test
    void save()
    {
        // GIVEN
        LearnSetAbo given = new LearnSetAbo();
        
        // WHEN
        LearnSetAbo result = learnSetAboRepository.save(given);
        Long resultID = result.getId();
        
        // THEN
        Assertions.assertTrue(resultID != null && resultID > 0);
    }
    
    @Test
    void findAll()
    {
        //given
        LearnSetAbo given1 = new LearnSetAbo();
        LearnSetAbo given2 = new LearnSetAbo();
        
        List<Long> idsOfPersisted = new ArrayList<>();
        LearnSetAbo saved1 = learnSetAboRepository.save(given1);
        LearnSetAbo saved2 = learnSetAboRepository.save(given2);
        idsOfPersisted.add(saved1.getId());
        idsOfPersisted.add(saved2.getId());
        
        List<LearnSetAbo> result;
        result = learnSetAboRepository.findAll();
        
        Assertions.assertTrue(result != null && ! result.isEmpty());
        Assertions.assertFalse(idsOfPersisted.isEmpty());
    }
    
}
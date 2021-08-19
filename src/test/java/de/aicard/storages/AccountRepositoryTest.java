package de.aicard.storages;

import de.aicard.domains.account.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

/**
 * Test class for the functions of Account
 *
 * @author Clemens Berger
 */
@DataJpaTest
public class AccountRepositoryTest
{
    
    
    @Autowired
    private AccountRepository accountRepository;
    
    @BeforeEach
    public void beforeEach()
    {
    
    }
    
    @AfterEach
    public void afterEach()
    {
        accountRepository.deleteAll();
    }
    
    
    @Test
    void findByEmail()
    {
        Account given1 = new Account();
        
        Account given2 = new Account();
        
        given1.setEmail("max.mustermann@web.de");
        given1.setName("Markus Mustermann");
        given2.setEmail("wrong@mustermann@web.de");
        
        accountRepository.save(given1);
        accountRepository.save(given2);
        
        Optional<Account> result = accountRepository.findByEmail("max.mustermann@web.de");
        
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get().getName(), "Markus Mustermann");
    }
}
package de.aicard.domains.account;

import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for the functions of Account
 *
 * @author Antonio Blechschmidt, Semlali Amine
 */



public class AccountTest
{


    @Test
    void testCreateNewOwnLearnSet(){

        //setup
        Account Account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);

        Account1.createNewOwnLearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PRIVATE);

        Assertions.assertTrue(!Account1.getLearnsetAbos().isEmpty());

    }

    @Test
    void testAddLearnSetAbo()
    {
        Account Account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
        LearnSet learnSet1 = new LearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, new CardList(),Account1,Visibility.PUBLIC);
        Account1.addLearnSetAbo(learnSet1);
        Assertions.assertTrue(!Account1.getLearnsetAbos().isEmpty());
    }

    @Test
    void testRemoveLearnSetAbo()
    {
        Account Account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
        LearnSet learnSet1 = new LearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, new CardList(),Account1,Visibility.PUBLIC);
        Account1.addLearnSetAbo(learnSet1);
        Account1.removeLearnSetAbo(Account1.getLearnsetAbos().get(0));
        Assertions.assertTrue(Account1.getLearnsetAbos().isEmpty());
    }


    @Test
    void testRemoveLearnSetAboByLearnSet()
    {

        Account Account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);

        Account1.createNewOwnLearnSet("IT1", "This is an IT Learnset1", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PRIVATE);
        Account1.createNewOwnLearnSet("IT2", "This is an IT Learnset2", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PRIVATE);
        Account1.createNewOwnLearnSet("IT3", "This is an IT Learnset3", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PRIVATE);

        LearnSet learnSet1 = new LearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, new CardList(),Account1,Visibility.PRIVATE);

//        LearnSetAbo learnSetAbo1 = new LearnSetAbo(learnSet1);
        Account1.addLearnSetAbo(learnSet1);
        Account1.removeLearnSetAboByLearnSet(learnSet1);


        Assertions.assertEquals(Account1.getLearnsetAbos().size(),3);

    }

    @Test
    void testAddRemoveFriends ()
    {
        Account Account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
        Account Account2 = new Account("Account2@fh-erfurt.de","adminAccount2","Account2","Descrip2", Faculty.APPLIED_COMPUTER_SCIENCE);
        Account1.addFriend(Account2);
        Account1.removeFriend(Account2);
        Assertions.assertTrue(Account1.getFriends().isEmpty());

    }
}
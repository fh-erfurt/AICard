package de.aicard.learnset;

import de.aicard.card.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Test class for all the functions of CardList
 *
 * @author Martin Kühlborn
 */

public class CardListTest
{
    private static final Logger logger = Logger.getLogger(CardList.class.getName());

    @Test
    public void testingCardListConstructors()
    {
        // Given: Two empty Cards
        Card Card1 = new Card();
        Card Card2 = new Card();

        //when: creating two CardLists: one with an ArrayList of the two Cards, one without parameters
        ArrayList CardArrayList = new ArrayList<>();
        CardArrayList.add(Card1);
        CardArrayList.add(Card2);
        
        CardList CardList1 = new CardList(CardArrayList);
        CardList CardList2 = new CardList();

        //when: adding the Cards to the empty List
        CardList2.getCardList().add(Card1);
        CardList2.getCardList().add(Card2);
    
    
        //then: the Cards in the Lists should be identically
        try
        {
            Assertions.assertEquals(Card2, CardList1.getCardByIndex(1));
            Assertions.assertEquals(Card2, CardList2.getCardByIndex(1));
        }
        catch(Exception e)
        {
        
        }

        //then: the current Card Pointer/Index in the CardList points to the first Card.
        Assertions.assertEquals(Card1, CardList1.getCurrentCard());
        Assertions.assertEquals(Card1, CardList2.getCurrentCard());
        
    }
    
    @Test
    public void testingCardListEdibility()
    {
        // Before
        Card Card1 = new Card();
        Card Card2 = new Card();
        
        CardList CardList1 = new CardList();
        CardList1.addToList(Card1);
        CardList1.addToList(Card2);
        
        // Testing
        try
        {
            CardList1.addToList(Card1);
            Assertions.assertEquals(Card1, CardList1.getCardByIndex(2), "Adding Card to CardList");

            CardList1.removeFromList(0);
            Assertions.assertEquals(Card2, CardList1.getCardByIndex(0));

            CardList1.removeFromList(Card2);
            Assertions.assertEquals(Card1, CardList1.getCardByIndex(0));
        }
        catch(Exception e)
        {

        }


        
    }
    
    @Test
    public void testingCardListGetNextPrevious()
    {
        // Before
        Card Card1 = new Card();
        Card Card2 = new Card();
        Card Card3 = new Card();
    
        CardList CardList1 = new CardList();
        CardList1.addToList(Card1);
        CardList1.addToList(Card2);
        CardList1.addToList(Card3);
    
    
        // Testing
        Assertions.assertEquals(3, CardList1.getListLength(), "Should show same List Length");
        try
        {
            Assertions.assertEquals(Card1, CardList1.getCardByIndex(0));
        }
        catch(Exception e)
        {

        }

        
        CardList1.next();
        Assertions.assertEquals(Card2, CardList1.getCurrentCard());
    
        CardList1.next();
        Assertions.assertEquals(Card3, CardList1.getCurrentCard());
        
        CardList1.previous();
        Assertions.assertEquals(Card2, CardList1.getCurrentCard());
        
    }
    
    @Test
    public void shouldDisplayLoggerWarningAndNotIncreaseSizePast200()
    {
        CardList testCardList = new CardList();
        for(int index = 0; index < 200; index++)
        {
            testCardList.addToList(new Card());
        }
        
        testCardList.addToList(new Card());
        Assertions.assertEquals(200, testCardList.getListLength());
    }
    
    @Test
    void testingRemoveFromCardList()
    {
        // before
        CardList testCardList = new CardList();
        Card testCard1 = new Card();
        Card testCard2 = new Card();
        
        testCardList.addToList(testCard1);
        testCardList.addToList(testCard2);
        testCardList.addToList(testCard1);
        // testing
        testCardList.removeFromList(1);
        Assertions.assertEquals(2, testCardList.getListLength());
        Assertions.assertEquals(testCard1, testCardList.getCurrentCard());
        
        testCardList.removeFromList(testCard1);
        Assertions.assertEquals(1, testCardList.getListLength());

    }
    
}

package de.aicard.learnset;

import de.aicard.card.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CardListTest
{

    @Test
    public void testingCardListConstructors()
    {
        // Before
        Card Card1 = new Card();
        Card Card2 = new Card();
        Card [] CardArray = {Card1, Card2};
        
        ArrayList CardArrayList = new ArrayList<>();
        CardArrayList.add(Card1);
        CardArrayList.add(Card2);
        
        CardList CardList1 = new CardList();
        CardList CardList2 = new CardList(CardArray);
        CardList CardList3 = new CardList(CardArrayList);
        
        // Testing
        Assertions.assertEquals(Card2, CardList1.getCardByIndex(1));
        Assertions.assertEquals(Card2, CardList2.getCardByIndex(1));
        Assertions.assertEquals(Card2, CardList3.getCardByIndex(1));
    
        Assertions.assertEquals(Card1, CardList1.getCurrentCard());
        Assertions.assertEquals(Card1, CardList2.getCurrentCard());
        Assertions.assertEquals(Card1, CardList3.getCurrentCard());
        
    }
    
    @Test
    public void testingCardListEditability()
    {
        // Before
        Card Card1 = new Card();
        Card Card2 = new Card();
        Card [] CardArray = {Card1, Card2};
        
        CardList CardList1 = new CardList(CardArray);
        
        // Testing
        CardList1.addToList(Card1);
        Assertions.assertEquals(Card1, CardList1.getCardByIndex(2), "Adding Card to CardList");
        
        CardList1.removeFromList(0);
        Assertions.assertEquals(Card2, CardList1.getCardByIndex(0));
        
        CardList1.removeFromList(Card2);
        Assertions.assertEquals(Card1, CardList1.getCardByIndex(0));
        
    }
    
    @Test
    public void testingCardListGetNextPrevious()
    {
        // Before
        Card Card1 = new Card();
        Card Card2 = new Card();
        Card Card3 = new Card();
        Card [] CardArray = {Card1, Card2, Card3};
    
        CardList CardList1 = new CardList(CardArray);
    
        // Testing
        Assertions.assertEquals(CardArray.length, CardList1.getListLength(), "Should show same List Length");
        Assertions.assertEquals(Card1, CardList1.getCardByIndex(0));
        
        CardList1.next();
        Assertions.assertEquals(Card2, CardList1.getCurrentCard());
    
        CardList1.next();
        Assertions.assertEquals(Card3, CardList1.getCurrentCard());
        
        CardList1.previous();
        Assertions.assertEquals(Card2, CardList1.getCurrentCard());
        
    }
    
}

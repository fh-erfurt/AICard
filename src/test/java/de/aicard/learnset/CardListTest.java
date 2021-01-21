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
        
        ArrayList CardArrayList = new ArrayList<>();
        CardArrayList.add(Card1);
        CardArrayList.add(Card2);
        
        CardList CardList1 = new CardList(CardArrayList);
        CardList CardList2 = new CardList();
        CardList2.getCardList().add(Card1);
        CardList2.getCardList().add(Card2);
    
    
        // Testing
        try{
            Assertions.assertEquals(Card2, CardList1.getCardByIndex(1));
            Assertions.assertEquals(Card2, CardList2.getCardByIndex(1));
        }
        catch(NullPointerException e){

    }
        catch(Exception e){
            //just added this to compile the project ;)
        }

    
        Assertions.assertEquals(Card1, CardList1.getCurrentCard());
        Assertions.assertEquals(Card1, CardList2.getCurrentCard());
        
    }
    
    @Test
    public void testingCardListEditability()
    {
        // Before
        Card Card1 = new Card();
        Card Card2 = new Card();
        
        CardList CardList1 = new CardList();
        CardList1.addToList(Card1);
        CardList1.addToList(Card2);
        
        // Testing
        try{
            CardList1.addToList(Card1);
            Assertions.assertEquals(Card1, CardList1.getCardByIndex(2), "Adding Card to CardList");

            CardList1.removeFromList(0);
            Assertions.assertEquals(Card2, CardList1.getCardByIndex(0));

            CardList1.removeFromList(Card2);
            Assertions.assertEquals(Card1, CardList1.getCardByIndex(0));
        }
        catch(Exception e){

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
        try{
            Assertions.assertEquals(Card1, CardList1.getCardByIndex(0));
        }
        catch(Exception e){

        }

        
        CardList1.next();
        Assertions.assertEquals(Card2, CardList1.getCurrentCard());
    
        CardList1.next();
        Assertions.assertEquals(Card3, CardList1.getCurrentCard());
        
        CardList1.previous();
        Assertions.assertEquals(Card2, CardList1.getCurrentCard());
        
    }
    
}

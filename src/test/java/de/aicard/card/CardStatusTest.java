package de.aicard.card;

import de.aicard.enums.CardKnowledgeLevel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardStatusTest
{
    @Test
    public void testingCardKnowledgeLevelIncreaseAndDecrease()
    {
        // before
        Card testCard = new Card();
        CardStatus testCardStatus = new CardStatus(testCard);
        
        // testing
        testCardStatus.increaseCardKnowledgeLevel();
        testCardStatus.increaseCardKnowledgeLevel();
        testCardStatus.increaseCardKnowledgeLevel();
        testCardStatus.increaseCardKnowledgeLevel();
        Assertions.assertEquals(CardKnowledgeLevel.KNOWVERYWELL, testCardStatus.getCardKnowledgeLevel());
        
        testCardStatus.decreaseCardKnowledgeLevel();
        Assertions.assertEquals(CardKnowledgeLevel.KNOWWELL, testCardStatus.getCardKnowledgeLevel());
        
    }
    
    @Test
    public void testingCardKnowledgeLevelReset()
    {
        // before
        Card testCard = new Card();
        CardStatus testCardStatus = new CardStatus(testCard);
        // testing
        testCardStatus.increaseCardKnowledgeLevel();
        testCardStatus.increaseCardKnowledgeLevel();
        testCardStatus.increaseCardKnowledgeLevel();
        
        Assertions.assertEquals(CardKnowledgeLevel.KNOWWELL, testCardStatus.getCardKnowledgeLevel());
        
        testCardStatus.resetCardKnowledgeLevel();
        Assertions.assertEquals(CardKnowledgeLevel.NOINFORMATION, testCardStatus.getCardKnowledgeLevel());
    }
}

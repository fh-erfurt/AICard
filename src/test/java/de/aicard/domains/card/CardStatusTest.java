package de.aicard.domains.card;

import de.aicard.domains.enums.CardKnowledgeLevel;

import de.aicard.domains.enums.DataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Provides test of functionalities of the CardStatus class
 *
 * @author Martin Kuehlborn
 */
public class CardStatusTest
{
    @Test
    public void testingCardKnowledgeLevelIncreaseAndDecrease()
    {
        // before
        Card testCard = new Card(new CardContent("title1","textDataFront", DataType.TextFile), new CardContent("title2","textDataBack",DataType.TextFile));
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
        Card testCard = new Card(new CardContent("title1","textDataFront", DataType.TextFile), new CardContent("title2","textDataBack",DataType.TextFile));
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

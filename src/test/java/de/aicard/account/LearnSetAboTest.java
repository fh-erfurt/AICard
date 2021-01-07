package de.aicard.account;

import de.aicard.card.Card;
import de.aicard.card.CardContent;
import de.aicard.card.TextFile;
import de.aicard.enums.CardKnowledgeLevel;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LearnSetAboTest {
    @Test
    public void testingCreatingLearnSetAbo()
    {
        //given
        LearnSet learnSet = new LearnSet("testSet", "test Description", Faculty.AppliedComputerScience);
        Card card = null;
        CardContent front = null;
        CardContent back = null;
        for(int i = 0; i<5; i++){
            learnSet.createCard();
            card = learnSet.getCardList().getCardByIndex(i);
            front = new TextFile("Vorderseite von Karte " + i);
            back = new TextFile("Rückseite von Karte " + i);
            card.setCardFront(front);
            card.setCardBack(back);
        }

        LearnSetAbo abo = new LearnSetAbo(learnSet);

        //when
        CardKnowledgeLevel level = abo.getM_cardStatus().get(4).getCardKnowledgeLevel();
        String frontText = ((TextFile) abo.getM_cardStatus().get(4).getCard().getCardFront()).getTextData();
        //then
        //Assertions.assertEquals(CardKnowledgeLevel.NOINFORMATION, level, "The level never changed");
        Assertions.assertEquals("Vorderseite von Karte 4", frontText);

    }

}

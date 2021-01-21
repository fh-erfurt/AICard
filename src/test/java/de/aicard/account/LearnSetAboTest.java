package de.aicard.account;

import de.aicard.card.Card;
import de.aicard.card.CardContent;
import de.aicard.card.TextFile;
import de.aicard.enums.CardKnowledgeLevel;
import de.aicard.enums.Faculty;
import de.aicard.learnset.CardList;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearningSession;
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
        //learnSet.createCardList(); //TODO maybe add emty CardList by default constructor?
        CardList testList = learnSet.getCardList();
        for(int i = 0; i<20; i++){
            card = new Card();
            front = new TextFile("Vorderseite von Karte " + i);
            back = new TextFile("Rückseite von Karte " + i);
            card.setCardFront(front);
            card.setCardBack(back);
            testList.addToList(card);
        }

        LearnSetAbo abo = new LearnSetAbo(learnSet);

        //when
        CardKnowledgeLevel level = abo.getM_cardStatus().get(4).getCardKnowledgeLevel();
        String frontText = ((TextFile) abo.getM_cardStatus().get(4).getCard().getCardFront()).getTextData();
        //then
        //Assertions.assertEquals(CardKnowledgeLevel.NOINFORMATION, level, "The level never changed");
        Assertions.assertEquals("Vorderseite von Karte 4", frontText);

        //this part test creation of learning session
        //given

        //The learnset has 20 cards. in LearningSession 1 should be the first 10 cards. (0-9)
        int numOfCards = 10;
        LearningSession session = abo.createLearningSession(numOfCards);
        while(session.getIsActive()){
            session.cardKnown();
        }

        //then
        //All cards that where in the LearningSession where known, thus the knowledgelevel was raised.
        //The knowledgelevel of card 9 should now be
        try{
            Assertions.assertEquals(CardKnowledgeLevel.SOMEINFORMATION, learnSet.getCardList().getCardByIndex(9));
        }catch(Exception e){
            System.out.println("Hupsi, den Index gabs wohl nicht...");
        }


        //The second LearningSession consists of only 5 cards. It is expected that the next five cards
        //are picked (10-14)
        int alsoANumberofCards = 5;
        LearningSession nextsession = abo.createLearningSession(alsoANumberofCards);
        while(session.getIsActive()) {
            session.cardKnown();
        }




    }

}

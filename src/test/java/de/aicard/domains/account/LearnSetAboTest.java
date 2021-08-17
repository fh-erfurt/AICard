package de.aicard.domains.account;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.State;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import static de.aicard.domains.enums.CardKnowledgeLevel.NOINFORMATION;
//import static de.aicard.domains.learnset.LearnSetTest.getTestLearnSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * Tests the functions of the Class LearnSetAbo
 *
 * @author Daniel Michel
 */
public class LearnSetAboTest
{

    public static LearnSet getTestLearnSet()
    {
        String learnSetTitle = "Title";
        String learnSetDescription = "Description of my Learnset";
        Faculty faculty = Faculty.APPLIED_COMPUTER_SCIENCE;
        CardList cardList = new CardList();
        ArrayList<CardContent> front = new ArrayList<>();
        ArrayList<CardContent> back = new ArrayList<>();
        ArrayList<Card> card = new ArrayList<Card>();
        for(int i = 0; i<20;i++)
        {
            front.add(new CardContent("title"+i,"data"+i, DataType.TextFile));
            back.add(new CardContent("title"+i,"data"+i, DataType.TextFile));
            card.add(i, new Card(front.get(i), back.get(i)));
            cardList.addToList(card.get(i));
        }

        Account Account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
        LearnSet learnSet = new LearnSet(learnSetTitle, learnSetDescription, faculty, cardList, Account1, Visibility.PUBLIC);

        return learnSet;
    }

    private static final Logger logger = Logger.getLogger(LearnSetAboTest.class.getName());
    @Test
    public void testCreatingLearnSetAbo()
    {
        //given: A learnset with a cardlist of 20 cards.

        LearnSet learnSet = getTestLearnSet();

        //when: creating an abo of this learnset
        try
        {
            LearnSetAbo abo = new LearnSetAbo(learnSet);

            //then: in the LearnSet of the Abo should be the 20 Cards of the TestLearnSet
            CardContent backOfCard = (abo.getLearnSet().getCardList().getCardByIndex(19).getCardBack());
            Assertions.assertEquals("data19", backOfCard.getData());

            //then: the same is true for the Cards in the CardStatusList of the LearnSetAbo
            Assertions.assertEquals(20, abo.getCardStatus().size());

            CardContent backOfSameCard =  (abo.getCardStatus().get(19).getCard().getCardBack());
            Assertions.assertEquals("data19", backOfSameCard.getData());

            //then: the cards of the same index in the learnSet and the CardStatusList should be really exactly the same
            Assertions.assertEquals(abo.getLearnSet().getCardList().getCardByIndex(3), abo.getCardStatus().get(3).getCard());

            // the CardStatus of any Card should be NOINFORMATION
            Assertions.assertEquals(NOINFORMATION, abo.getCardStatus().get(19).getCardKnowledgeLevel());

        }
        catch (NullPointerException e)
        {
            logger.warning("this is a nullpointer Exeption" + e);
        }
        catch (Exception e)
        {
            logger.warning("Offenbar macht die CardList Probleme." + e);
        }
    }

}

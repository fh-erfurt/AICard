package de.aicard.account;

import de.aicard.card.TextFile;
import de.aicard.enums.State;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearnSetAbo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.aicard.enums.CardKnowledgeLevel.NOINFORMATION;
import static de.aicard.learnset.LearnSetTest.getTestLearnSet;

/**
 * Tests the functions of the Class LearnSetAbo
 *
 * @Author Daniel Michel
 */

public class LearnSetAboTest
{


    @Test
    public void testCreatingLearnSetAbo()
    {
        //given: A learnset with a cardlist of 20 cards.

        LearnSet learnSet = getTestLearnSet();

        //when: creating an abo of this learnset
        try
        {
            LearnSetAbo abo = new LearnSetAbo(learnSet);
            //then: The Status of the LearnSet should be new
            Assertions.assertEquals(State.NEW, abo.getLearnSetStatus());

            //then: in the LearnSet of the Abo should be the 20 Cards of the TestLearnSet
            TextFile backOfCard = (TextFile) (abo.getLearnSet().getCardList().getCardByIndex(19).getCardBack());
            Assertions.assertEquals("Back of card 19", backOfCard.getTextData());

            //then: the same is true for the Cards in the CardStatusList of the LearnSetAbo
            Assertions.assertEquals(20, abo.getCardStatus().size());

            TextFile backOfSameCard = (TextFile) (abo.getCardStatus().get(19).getCard().getCardBack());
            Assertions.assertEquals("Back of card 19", backOfSameCard.getTextData());

            //then: the cards of the same index in the learnSet and the CardStatusList should be really exactly the same
            Assertions.assertEquals(abo.getLearnSet().getCardList().getCardByIndex(3), abo.getCardStatus().get(3).getCard());

            // the CardStatus of any Card should be NOINFORMATION
            Assertions.assertEquals(NOINFORMATION, abo.getCardStatus().get(19).getCardKnowledgeLevel());

        } catch (NullPointerException e)
        {
            System.out.println("this is a nullpointer Exeption" + e);
        } catch (Exception e)
        {
            System.out.println("Offenbar macht die CardList Probleme." + e);
        }
    }

    @Test
    public void testCreatingAndDeletingEvaluation()
    {
        //given: Our TestLearnSet and an abo of it
        LearnSet learnSet = getTestLearnSet();
        try
        {
            LearnSetAbo abo = new LearnSetAbo(learnSet);

            //when: we are giving an evaluation to the learnset.

            abo.set_evaluation(5);

            //then: it is the only evaluation of the learnSet

            Assertions.assertEquals(1, learnSet.getNumberOfEvaluations());
            Assertions.assertEquals(5, learnSet.getEvaluation());

            //when: deleting the evaluation
            abo.delete_evaluation();

            //then: should be deleted in LearnSet too.
            Assertions.assertEquals(0, learnSet.getNumberOfEvaluations());
            Assertions.assertEquals(0, learnSet.getEvaluation());
        }
        catch (Exception e){
            //oh no!!
            System.out.println("OOPs. something went wrong.");
        }

    }

}

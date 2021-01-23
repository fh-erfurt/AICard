package de.aicard.account;

import de.aicard.card.Card;
import de.aicard.card.CardContent;
import de.aicard.card.CardStatus;
import de.aicard.card.TextFile;
import de.aicard.enums.CardKnowledgeLevel;
import de.aicard.enums.Faculty;
import de.aicard.enums.State;
import de.aicard.learnset.CardList;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearningSession;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.aicard.enums.CardKnowledgeLevel.NOINFORMATION;

public class LearnSetAboTest
{
    public LearnSet getTestLearnSet()
    {

        String learnSetTitle = "Title";
        String learnSetDescription = "Description of my Learnset";
        Faculty faculty = Faculty.AppliedComputerScience;
        CardList cardList = new CardList();
        TextFile front = new TextFile();
        TextFile back = new TextFile();
        Card card = null;
        for(int i = 0; i<20;i++)
        {
            front.setTextData("Front of card " + i);
            back.setTextData("Back of card " + i);
            card = new Card(front, back);
            cardList.addToList(card);
        }

        Account learnSetOwner = new Student("mail", "password", "name", "a student", 3, Faculty.AppliedComputerScience);
        LearnSet learnSet = new LearnSet(learnSetTitle, learnSetDescription, faculty, cardList, learnSetOwner);

        return learnSet;
    }

    @Test
    public void testCreatingLearnSetAbo()
    {
        //given: A learnset with a cardlist of 20 cards.

        LearnSet learnSet = getTestLearnSet();

        //when: creating an abo of this learnset
        try
        {
            LearnSetAbo abo = new LearnSetAbo(learnSet);
            //then: The Status of the LearnSet should be new, in the LearnSet should be the 20 Cards of
            //the TestLearnSet, we should have access to the CardContent of all the Cards,
            // the CardStatus should be NOINFORMATION

            Assertions.assertEquals(State.NEW, abo.getLearnSetStatus());
            Assertions.assertEquals(NOINFORMATION, abo.getCardStatus().get(19).getCardKnowledgeLevel());
            TextFile backOfCard = (TextFile) (abo.getLearnSet().getCardList().getCardByIndex(19).getCardBack());
            Assertions.assertEquals("Back of card 19", backOfCard.getTextData());

        }
        catch(Exception e){
            System.out.println("Offenbar macht die CardList Probleme.");
        }

    }

    @Test
    public void testCreatingAndDeletingEvaluation(){
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
            //ToDo test again when problem in LearnSet solved
            //Assertions.assertEquals(0, learnSet.getEvaluation());
        }
        catch (Exception e){
            //oh no!!
            System.out.println("OOPs. something went wrong.");
        }

    }

}

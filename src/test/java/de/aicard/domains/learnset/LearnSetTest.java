package de.aicard.domains.learnset;

import de.aicard.domains.Social.Message;
import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.enums.AcademicGrade;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Visibility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Test class for all the functions of LearnSet
 *
 * @author Martin Kuehlborn, Daniel Michel
 */
public class LearnSetTest
{

    /**
     * This is a helper function for many tests.
     *
     *
     * @return a LearnSet to run further tests on.
     */
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

        Account learnSetOwner = new Account();
        LearnSet learnSet = new LearnSet(learnSetTitle, learnSetDescription, faculty, cardList, learnSetOwner, Visibility.PUBLIC);

        return learnSet;
    }

    @Test
    public void testingEvaluations()
    {
        //given: a LearnSet and 3 evaluations.
        LearnSet testLearnSet = getTestLearnSet();
        int evaluation1 = 4;
        int evaluation2 = 2;
        int evaluation3 = 3;

        //when: adding first evaluation
        testLearnSet.addEvaluation(evaluation1);
        //then: Evaluation of LearnSet equals the first evaluation
        Assertions.assertEquals(evaluation1, testLearnSet.getEvaluations());

        //when: adding second evaluation
        testLearnSet.addEvaluation(evaluation2);
        //then: Evaluation of LearnSet equals the average of the first two evaluations
        Assertions.assertEquals((double)(evaluation1 + evaluation2) / 2, testLearnSet.getEvaluations());

        //when: adding third evaluation
        testLearnSet.addEvaluation(evaluation3);
        //then: Evaluation of LearnSet equals the average of the three evaluations
        Assertions.assertEquals((double)(evaluation1 + evaluation2 + evaluation3) / 3, testLearnSet.getEvaluations());

        //when: deleting the first evaluation
        testLearnSet.deleteEvaluation(evaluation1);
        //then: Evaluation of LearnSet equals the average of the second and third evaluation
        Assertions.assertEquals((double)(evaluation2 + evaluation3) / 2, testLearnSet.getEvaluations());

        //when: deleting the second evaluation
        testLearnSet.deleteEvaluation(evaluation2);
        //then: Evaluation of LearnSet equals toe third evaluation
        Assertions.assertEquals((double)evaluation3, testLearnSet.getEvaluations());

        //when: deleting the last evaluation
        testLearnSet.deleteEvaluation(evaluation3);
        //then: Evaluation of LearnSet goes back to zero.
        Assertions.assertEquals(0.0, testLearnSet.getEvaluations());

    }

    @Test
    public void testingAdminList()
    {
        //given: a testLearnSet and three accounts
        LearnSet learnSet = getTestLearnSet();
        Account professor = new Account();
        Account student = new Account();
        Account myOwnerStudent = learnSet.getOwner();

        //when: adding those Accounts as admins to the LearnSet
        learnSet.addAdmin(professor);
        learnSet.addAdmin(student);
        learnSet.addAdmin(myOwnerStudent);

        //then: those accounts are in exactly this order in the admin array of the learnset
        Assertions.assertEquals(professor, learnSet.getAdminList().get(0));
        Assertions.assertEquals(student, learnSet.getAdminList().get(1));
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdminList().get(2));

        //when: trying to add one of those accounts again as admin
        learnSet.addAdmin(professor);
        //then: it is not added
        Assertions.assertEquals(3, learnSet.getAdminList().size());

        //when: removing the first admin account (professor) by index
        learnSet.removeAdmin(0);
        //then: the other two admins and only those remain in the adminList
        Assertions.assertEquals(student, learnSet.getAdminList().get(0));
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdminList().get(1));

        //when: removing student as admin
        learnSet.removeAdmin(student);
        //then: only myOwnerStudent remains in the adminList
        Assertions.assertEquals(1, learnSet.getAdminList().size());
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdminList().get(0));

        //when: removing the last account as admin
        learnSet.removeAdmin(myOwnerStudent);
        //then: the AdminList is Empty
        Assertions.assertEquals(true, learnSet.getAdminList().isEmpty());

    }

    @Test
    public void testingCommentList()
    {
        //given: a learnSet, 2 accounts and 3 messages
        LearnSet learnSet = getTestLearnSet();
        Account student = learnSet.getOwner();
        Account professor = new Account();
        Message message1 = new Message("Message 1", student);
        Message message2 = new Message("Message 2", professor);
        Message message3 = new Message("Message 3", student);

        //when: adding Messages to LearnSet
        learnSet.addMessage(message1);
        learnSet.addMessage(message2);
        learnSet.addMessage(message3);

        //then: the messages can be accessed
        Assertions.assertEquals("Message 1", learnSet.getCommentList().get(0).getMessage());



    }

    @Test
    public void testAuthorizationCheck(){
        //given: 3 Learnsets: one public, one protected, one private
        LearnSet publicLearnSet = getTestLearnSet();
        publicLearnSet.setVisibility(Visibility.PUBLIC);

        LearnSet protectedLearnSet = getTestLearnSet();
        protectedLearnSet.setVisibility(Visibility.PROTECTED);

        LearnSet privateLearnSet = getTestLearnSet();
        privateLearnSet.setVisibility(Visibility.PRIVATE);

        //also given: some Accounts

        Account ownerOfPublicLearnSet = publicLearnSet.getOwner();
        Account ownerOfProtectedLearnSet = protectedLearnSet.getOwner();
        Account ownerOfPrivateLearnSet = privateLearnSet.getOwner();
        Account friendOfAll = new Account();
        ownerOfPublicLearnSet.addFriend(friendOfAll);
        ownerOfProtectedLearnSet.addFriend(friendOfAll);
        ownerOfPrivateLearnSet.addFriend(friendOfAll);
        Account allAlone = new Account();

        //then: allAlone is only authorised to subscribe to the public LearnSet
        Assertions.assertTrue(publicLearnSet.isAuthorizedToAddLearnSet(allAlone));
        Assertions.assertFalse(protectedLearnSet.isAuthorizedToAddLearnSet(allAlone));
        Assertions.assertFalse(privateLearnSet.isAuthorizedToAddLearnSet(allAlone));

        //then: friendOfAll is authorised to subscribe to the public and the protected LearnSet
        Assertions.assertTrue(publicLearnSet.isAuthorizedToAddLearnSet(friendOfAll));
        Assertions.assertTrue(protectedLearnSet.isAuthorizedToAddLearnSet(friendOfAll));
        Assertions.assertFalse(privateLearnSet.isAuthorizedToAddLearnSet(friendOfAll));

        //then: the owner of the LearnSets are authorized to add their own Learnsets
        Assertions.assertTrue(publicLearnSet.isAuthorizedToAddLearnSet(ownerOfPublicLearnSet));
        Assertions.assertTrue(protectedLearnSet.isAuthorizedToAddLearnSet(ownerOfProtectedLearnSet));
        Assertions.assertTrue(privateLearnSet.isAuthorizedToAddLearnSet(ownerOfPrivateLearnSet));

    }
}

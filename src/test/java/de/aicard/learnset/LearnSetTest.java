package de.aicard.learnset;

import de.aicard.Social.Message;
import de.aicard.account.Account;
import de.aicard.account.Professor;
import de.aicard.account.Student;
import de.aicard.card.Card;
import de.aicard.card.TextFile;
import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Test class for all the functions of LearnSet
 *
 * @author: Martin Kühlborn, Daniel Michel
 */
public class LearnSetTest
{

    /**
     * This is a helper function for many tests.
     *
     *
     * @return: a LearnSet to run further tests on.
     */
    public static LearnSet getTestLearnSet()
    {

        String learnSetTitle = "Title";
        String learnSetDescription = "Description of my Learnset";
        Faculty faculty = Faculty.APPLIED_COMPUTER_SCIENCE;
        CardList cardList = new CardList();
        ArrayList<TextFile> front = new ArrayList<>();
        ArrayList<TextFile> back = new ArrayList<>();
        ArrayList<Card> card = new ArrayList<Card>();
        for(int i = 0; i<20;i++)
        {
            front.add(new TextFile("Front of card " + i));
            back.add(new TextFile("Back of card " + i));
            card.add(i, new Card(front.get(i), back.get(i)));
            cardList.addToList(card.get(i));
        }

        Account learnSetOwner = new Student("mail", "password", "name", "a student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        LearnSet learnSet = new LearnSet(learnSetTitle, learnSetDescription, faculty, cardList, learnSetOwner);

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
        Assertions.assertEquals(evaluation1, testLearnSet.getEvaluation());

        //when: adding second evaluation
        testLearnSet.addEvaluation(evaluation2);
        //then: Evaluation of LearnSet equals the average of the first two evaluations
        Assertions.assertEquals((double)(evaluation1 + evaluation2) / 2, testLearnSet.getEvaluation());

        //when: adding third evaluation
        testLearnSet.addEvaluation(evaluation3);
        //then: Evaluation of LearnSet equals the average of the three evaluations
        Assertions.assertEquals((double)(evaluation1 + evaluation2 + evaluation3) / 3, testLearnSet.getEvaluation());
        
        //when: deleting the first evaluation
        testLearnSet.deleteEvaluation(evaluation1);
        //then: Evaluation of LearnSet equals the average of the second and third evaluation
        Assertions.assertEquals((double)(evaluation2 + evaluation3) / 2, testLearnSet.getEvaluation());

        //when: deleting the second evaluation
        testLearnSet.deleteEvaluation(evaluation2);
        //then: Evaluation of LearnSet equals toe third evaluation
        Assertions.assertEquals((double)evaluation3, testLearnSet.getEvaluation());

        //when: deleting the last evaluation
        testLearnSet.deleteEvaluation(evaluation3);
        //then: Evaluation of LearnSet goes back to zero.
        Assertions.assertEquals(0.0, testLearnSet.getEvaluation());
        
    }
    
    @Test
    public void testingAdminList()
    {
        //given: a testLearnSet and three accounts
        LearnSet learnSet = getTestLearnSet();
        Account professor = new Professor("mail", "password", "Prof A", "Just a professor", AcademicGrade.JUNIOR_PROFESSOR);
        Account student = new Student("mail", "password", "hanz", "A productive student", 25, Faculty.ARCHITECTURE);
        Account myOwnerStudent = learnSet.getOwner();

        //when: adding those Accounts as admins to the LearnSet
        learnSet.addAdmin(professor);
        learnSet.addAdmin(student);
        learnSet.addAdmin(myOwnerStudent);

        //then: those accounts are in exactly this order in the admin array of the learnset
        Assertions.assertEquals(professor, learnSet.getAdmins().get(0));
        Assertions.assertEquals(student, learnSet.getAdmins().get(1));
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdmins().get(2));

        //when: trying to add one of those accounts again as admin
        learnSet.addAdmin(professor);
        //then: it is not added
        Assertions.assertEquals(3, learnSet.getAdmins().size());

        //when: removing the first admin account (professor) by index
        learnSet.removeAdmin(0);
        //then: the other two admins and only those remain in the adminList
        Assertions.assertEquals(student, learnSet.getAdmins().get(0));
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdmins().get(1));

        //when: removing student as admin
        learnSet.removeAdmin(student);
        //then: only myOwnerStudent remains in the adminList
        Assertions.assertEquals(1, learnSet.getAdmins().size());
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdmins().get(0));

        //when: removing the last account as admin
        learnSet.removeAdmin(myOwnerStudent);
        //then: the AdminList is Empty
        Assertions.assertEquals(true, learnSet.getAdmins().isEmpty());

    }
    
    @Test
    public void testingCommentList()
    {
        //given: a learnSet, 2 accounts and 3 messages
        LearnSet learnSet = getTestLearnSet();
        Student student = (Student) learnSet.getOwner();
        Professor professor = new Professor("mail", "password", "Prof A", "Just a professor", AcademicGrade.JUNIOR_PROFESSOR);
        Message message1 = new Message("Message 1", student);
        Message message2 = new Message("Message 2", professor);
        Message message3 = new Message("Message 3", student);

        //when: adding Messages to LearnSet
        learnSet.addMessage(message1);
        learnSet.addMessage(message2);
        learnSet.addMessage(message3);

        //then: the messages can be accessed
        //TODO write this test when we decided about future of MessageList
        //Assertions.assertEquals("Message 1", learnSet.getCommentList().);


    
    }


}

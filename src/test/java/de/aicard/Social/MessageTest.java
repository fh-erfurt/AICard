package de.aicard.Social;

import de.aicard.account.Account;
import de.aicard.account.Professor;
import de.aicard.account.Student;
import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Test class for the functions of Message
 *
 * @author Semlali Amine
 */

public class MessageTest {

    @Test

    public void testRaiseAndRemoveLikes() {
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Message msg1 = new Message("this is a message",std);
        msg1.setLikes(2);

        Message msg2 = new Message("this is a message",std);
        msg2.raiseLikes();
        msg2.raiseLikes();
        msg2.raiseLikes();
        msg2.removeLike();

        Assertions.assertEquals(msg1.getLikes(), msg2.getLikes());
    }
    @Test

    public void testNewLikerAndLostLiker() {
        Professor prof = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Message msg1 = new Message("this is a message",std);
        ArrayList<Account> accountList = new ArrayList<Account>();
        accountList.add(std);
        msg1.newLiker(prof);
        msg1.newLiker(std);
        msg1.lostLiker(prof);
        Assertions.assertEquals(msg1.getLikedBy(), accountList);
    }

}

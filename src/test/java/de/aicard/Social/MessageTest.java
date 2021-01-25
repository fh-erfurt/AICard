package de.aicard.Social;

import de.aicard.account.Student;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import de.aicard.Social.Message;


public class MessageTest {

    @Test

    public void testRaiseLikes() {
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);
        Message msg1 = new Message("this is a message",std);
        msg1.setLikes(3);

        Message msg2 = new Message("this is a message",std);
        msg2.raiseLikes();
        msg2.raiseLikes();
        msg2.raiseLikes();

        Assertions.assertEquals(msg1.getLikes(), msg2.getLikes());
    }

}

package de.aicard.Social;

import de.aicard.account.Student;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import de.aicard.Social.Message;


public class MessageTest {

    @Test

    public void TestRaiseLikes() {
        Student Std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);
        Message msg1 = new Message("this is a message",Std);
        msg1.set_likes(3);

        Message msg2 = new Message("this is a message",Std);
        msg2.raise_likes();
        msg2.raise_likes();
        msg2.raise_likes();

        Assertions.assertEquals(msg1.get_likes(), msg2.get_likes());
    }

}

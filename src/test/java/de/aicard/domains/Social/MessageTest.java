package de.aicard.domains.Social;

import de.aicard.domains.account.Student;
import de.aicard.domains.enums.Faculty;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for the functions of Message
 *
 * @author Semlali Amine
 */

public class MessageTest
{
    @Test
    public void testLikeManipulation()
    {
        //setup
        Student std1 = new Student("Std@fh-erfurt.de", "adminStd", "Std1", "Student1", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Student std2 = new Student("Std@fh-erfurt.de", "adminStd", "Std2", "Student2", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Message message1 = new Message("Hello", std1);

        //test clickLike
        message1.clickLike(std1);   //likes Message
        Assertions.assertEquals(message1.getLikes(), 1);
        message1.clickLike(std2);   //likes Message
        Assertions.assertEquals(message1.getLikes(), 2);
        message1.clickLike(std1);   //undo like Message
        Assertions.assertEquals(message1.getLikes(), 1);
        message1.clickLike(std2);   // undo like Message
        Assertions.assertEquals(message1.getLikes(), 0);
    }
}

package de.aicard.Social;

import de.aicard.account.Account;
import de.aicard.account.Professor;
import de.aicard.account.Student;
import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import de.aicard.Social.Chat;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the functions of Chat
 *
 * @author Semlali Amine
 */


class ChatTest {



    @Test
    void getChatCreator()
    {
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);
        Chat chat = new Chat(prof,std,"this is a message");
        Assertions.assertEquals(chat.getChatCreator(), std);
    }

    @Test
    void clearHistory()
    {
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);
        Chat chat = new Chat(prof,std,"this is a message1");
        chat.sendMessage("this is a message2", prof);
        chat.clearHistory();
        ArrayList<Message> chatHistoryFake = new ArrayList<Message>();
        Assertions.assertEquals(chat.getChatHistory(), chatHistoryFake);
    }

    @Test
    void sendAndRemoveMessage()
    {
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);

        Chat chat = new Chat(prof,std,"this is a message1");
        chat.sendMessage("this is a message2", prof);
        chat.removeMessageByIndex(1);
        ArrayList<Message> chatHistoryFake = new ArrayList<Message>();
        Message msg1 = new Message("this is a message1",std);
        chatHistoryFake.add(msg1);
        Assertions.assertEquals(chat.getChatHistory().size(), chatHistoryFake.size());
    }


}
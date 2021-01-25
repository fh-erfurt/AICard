package de.aicard.Social;
import de.aicard.account.Student;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;

public class MessageListTest {

    public void testAddRemoveMessage()
    {
        MessageList messageList = new MessageList();
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Message msg1 = new Message("this is message1",std);
        Message msg2 = new Message("this is message2",std);
        Message msg3 = new Message("this is message3",std);

        messageList.addMessage(msg1);
        messageList.addMessage(msg2);
        messageList.addMessage(msg3);
        messageList.removeMessage(msg2);

        MessageList messageList2 = new MessageList();
        messageList2.addMessage(msg1);
        messageList2.addMessage(msg3);

        Assertions.assertEquals(messageList,messageList2);

    }


    public void testGetNumberMessages()
    {
        MessageList messageList = new MessageList();
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Message msg1 = new Message("this is message1",std);
        Message msg2 = new Message("this is message2",std);
        Message msg3 = new Message("this is message3",std);

        messageList.addMessage(msg1);
        messageList.addMessage(msg2);
        messageList.addMessage(msg3);
        messageList.removeMessage(msg2);

        MessageList messageList2 = new MessageList();
        messageList2.addMessage(msg1);
        messageList2.addMessage(msg3);

        Assertions.assertEquals(messageList.getNumberMessages(),messageList2.getNumberMessages());
    }
}

package de.aicard.Social;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
public class MessageListTest {

    public void TestAddRemoveMessage()
    {
        MessageList messageList = new MessageList();
        Message msg1 = new Message("this is message1");
        Message msg2 = new Message("this is message2");
        Message msg3 = new Message("this is message3");

        messageList.addMessage(msg1);
        messageList.addMessage(msg2);
        messageList.addMessage(msg3);
        messageList.removeMessage(msg2);

        MessageList messageList2 = new MessageList();
        messageList2.addMessage(msg1);
        messageList2.addMessage(msg3);

        Assertions.assertEquals(messageList,messageList2);

    }


    public void TestGetNumberMessages()
    {
        MessageList messageList = new MessageList();
        Message msg1 = new Message("this is message1");
        Message msg2 = new Message("this is message2");
        Message msg3 = new Message("this is message3");

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

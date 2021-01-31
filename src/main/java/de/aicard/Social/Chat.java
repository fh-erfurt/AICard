package de.aicard.Social;
import de.aicard.account.Account;

import java.util.ArrayList;

/**
Class Chat has 2 Attributes chatHistory where the messages from all participants are stored
 and participants where the participants are stored
 Participants can be added to the Chat but only after adding the creator

 @Author Semlali Amine
 */

//ToDo MessageList und AccountList durch ArrayList ersetzen
public class Chat {

private ArrayList<Message> chatHistory;
private ArrayList<Account> participants ;

    //Constructor

    public Chat()
    { chatHistory = new ArrayList<Message>();
    participants = new ArrayList<Account>();
    }

    /**
     * Constructor of a Chat.
     * @param _newCreator the person that creates the chat by sending a message to a participant, the creator is the fist person on the participants list
     * @param _newParticipant the person that receives the first message and is the second on the participants list
     * @param _newMessage the message to send
     *
     * @author Semlali Amine
     */

    public Chat(Account _newParticipant,Account _newCreator, String _newMessage)
    {
        chatHistory = new ArrayList<Message>();
        this.chatHistory.add(new Message(_newMessage,_newCreator));
        participants = new ArrayList<Account>();
        this.participants.add(_newCreator);
        this.participants.add(_newParticipant);
        for(Account participant:participants)
        {
            if (!(participant == _newCreator))// invites every participant without creator to chat
            {
                participant.addChat(this);
            }
        }

    }

    //Setter & getter

    public ArrayList<Message> getChatHistory()
    {
            return this.chatHistory;
    }

    public void setChatHistory(ArrayList<Message> _newChathistory) {
        this.chatHistory = _newChathistory;
    }

    public ArrayList<Account> getParticipants()
    {
        return this.participants;
    }
    public void setParticipants(ArrayList<Account> _newParticipant) {
        this.participants = _newParticipant;
    }

    //Functions
/**
    -participants can be added to the chat with the function addParticipant
    -the function getChatCreator is used to get the chat creator which is the first in the participants list
    -the Chat history can be cleared with the function clearHistory
    -messages can be sent , for that we need the account and the message to create the object Message and then add it to the chat history
    -messages can be removed from the chat history, with the help of an index argument or the actual Message object argument

 * @author Semlali Amine
 */

    public void addParticipant(Account _newParticipant)
    {
        this.participants.add(_newParticipant);

    }

    public Account getChatCreator()
    {
        return this.participants.get(0);
    }

    // chat

    public void clearHistory()
    {
        this.chatHistory = new ArrayList<Message>();
    }


    public void sendMessage(String _newMessage, Account _newSender)
    {
     Message messageToSend = new Message(_newMessage, _newSender);
     this.chatHistory.add(messageToSend);
    }

    public void removeMessage (Message _oldMessage)
    {

        this.chatHistory.remove(_oldMessage);
    }

    public void removeMessageByIndex (int _oldMessage)
    {

        this.chatHistory.remove(_oldMessage);
    }
    //TODO Chat in Participant Account schreiben
}

package de.aicard.Social;
import de.aicard.account.Account;

import java.util.ArrayList;

/**
Class Chat has 2 Attributes chatHistory where the messages from all particiants are stored
 and participants where the participants are stored
 Participants can be added to the Chat but only after adding the creator
 the Chat history can be cleared
 messages can be sent , for that we need the account and a message
 messages can be removed from the chat history
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
    public Chat(Account _newParticipant,Account _newCreator, String _newMessage)
    {
        chatHistory = new ArrayList<Message>();
        this.chatHistory.add(new Message(_newMessage,_newCreator));
        participants = new ArrayList<Account>();
        this.participants.add(_newCreator);
        this.participants.add(_newParticipant);

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
    public void addParticipant(Account _newParticipant){
        this.participants.add(_newParticipant);
    }

    public Account getChatCreator(){
        return this.participants.get(0);
    }

    public void clearHistory(){
        this.chatHistory = new ArrayList<Message>();
    }

    // chats

    public void sendMessage(String _newMessage, Account _newSender) {
     Message messageToSend = new Message(_newMessage, _newSender);
     this.chatHistory.add(messageToSend);
    }

    public void removeMessage (Message _oldMessage){

        this.chatHistory.remove(_oldMessage);
    }

    public void removeMessageByIndex (int _oldMessage){

        this.chatHistory.remove(_oldMessage);
    }
    //TODO Chat in Participant Account schreiben
}

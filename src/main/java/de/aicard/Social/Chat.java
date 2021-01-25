package de.aicard.Social;
import de.aicard.account.Account;
import de.aicard.account.AccountList;
/**
Class Chat has 2 Attributes chatHistory where the messages from all particiants are stored
 and participants where the participants are stored
 Participants can be added to the Chat but only after adding the creator
 the Chat history can be cleared
 messages can be sent , for that we need the account and a message
 messages can be removed from the chat history
 */
public class Chat {

private MessageList chatHistory;
private AccountList participants ;

    //Constructor

    public Chat()
    { chatHistory = new MessageList();
    participants = new AccountList();
    }
    public Chat(Account newParticipant,Account newCreator, String newMessage)
    {
        chatHistory = new MessageList(newMessage, newCreator);
        participants = new AccountList();
        this.participants.addPerson(newCreator);
        this.participants.addPerson(newParticipant);

    }
    //Setter & getter

    public MessageList getChatHistory() throws NullPointerException
    {
        if(this.chatHistory == null)
        {
            throw new NullPointerException("chathistory does not exist.");
        }
            return this.chatHistory;
    }

    public void setChatHistory(MessageList newChathistory) {
        this.chatHistory = newChathistory;
    }

    public AccountList getParticipants()
    {
        return this.participants;
    }
    public void setParticipants(AccountList newParticipant) {
        this.participants = newParticipant;
    }

    //Functions
    public void addParticipant(Account newParticipant){
        this.participants.addPerson(newParticipant);
    }

    public Account getChatCreator(){
        return this.participants.getPerson(0);
    }

    public void clearHistory(){
        this.chatHistory = new MessageList();
    }

    // chats

    public void sendMessage(String newMessage, Account newSender) {
     Message messageToSend = new Message(newMessage, newSender);
     this.chatHistory.addMessage(messageToSend);
    }

    public void removeMessage (Message oldMessage){

        this.chatHistory.removeMessage(oldMessage);
    }


}

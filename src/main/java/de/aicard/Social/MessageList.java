package de.aicard.Social;
import de.aicard.Social.Message;
import de.aicard.account.Account;
import java.util.ArrayList;

/** this class is used to group messages in a list */

public class MessageList {

    private ArrayList<Message> messageList;

    //Constructor

    public MessageList(){ messageList = new ArrayList<Message>(); }
    public MessageList(String newMessage, Account newSender)
    {
        messageList = new ArrayList<Message>();
        this.addMessage(new Message(newMessage,newSender));
    }

    //Setter & getter

    public ArrayList<Message> getMessageList() throws NullPointerException
    {
        if(this.messageList == null)
        {
            throw new NullPointerException("No messages were set.");
        }

        return this.messageList;
    }
    public void setMessagelist(ArrayList<Message> newMessageList) {
        this.messageList = newMessageList;
    }

    // functions

    public void addMessage(Message newMessage)
    {
        this.messageList.add(newMessage);
    }

    public void removeMessage(Message oldMessage)
    {
        this.messageList.remove(oldMessage);
    }

    public int getNumberMessages(){
        return this.messageList.size();
    }
    public boolean containMessage (Message theMessage)
    {
        return (this.messageList.contains(theMessage));
    }
}
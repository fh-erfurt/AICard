package de.aicard.Social;
import de.aicard.Social.Message;
import de.aicard.account.Account;

import java.util.ArrayList;

public class MessageList {

    /*
    Todo Eine Liste, die nur aus einer Liste besteht, macht den Code nur unnötig kompliziert. Also: Klasse löschen und
    gleich mit ArrayList<Message> arbeiten, wo wir es brauchen, oder das Ding braucht mehr Funktionen.

    Beispiel:

    Zugriff auf Kommentar in commentList in LearnSet ohne diese Klasse:

    myLearnSet.getCommentList().get(i);

    und mit dieser Klasse:

    myLearnSet.getCommentList().get_messageList().get(i);

    */

    private ArrayList<Message> messagelist;

    //Constructor

    public MessageList(){ messagelist = new ArrayList<Message>(); }
    public MessageList(String _message, Account _account)
    {
        messagelist = new ArrayList<Message>();
        this.addMessage(new Message(_message,_account));
    }

    //Setter & getter

    public ArrayList<Message> get_messagelist() {
        return messagelist;
    }
    public void set_messagelist(ArrayList<Message> _messageList) {
        this.messagelist = _messageList;
    }

    // functions

    public void addMessage(Message _message)
    {
        messagelist.add(_message);
    }

    public void removeMessage(Message _message)
    {
        messagelist.remove(_message);
    }

    public int getNumberMessages(){
        return messagelist.size();
    }
}
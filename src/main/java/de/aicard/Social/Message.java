package de.aicard.Social;

import de.aicard.account.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The class message is used to store informations about a message  */
public class Message {

    private String message;
    private Account sender;
    private LocalDateTime time;
    private int likes;
    private ArrayList<Account> likedBy  ;
    //constructor
    public Message(String _newMessage, Account _newSender){

        message = _newMessage;
        sender = _newSender;
        time = LocalDateTime.now();
        likes = 0;
        likedBy = null;

    }
    //Setter & getter

    public String getMessage()
    {
        return this.message;
    }
    public void setMessage(String _newMessage) {
        this.message = _newMessage;
    }
    public int getLikes()
    {
        return this.likes;
    }
    public void setLikes (int _number){this.likes = _number;}
    public ArrayList<Account> getLikedBy() throws  NullPointerException
    {
        if(this.likedBy == null)
        {
            throw new NullPointerException("message was liked by nobody.");
        }
        return this.likedBy;
    }
    public void setLikedby (ArrayList<Account> _newLikedBy) {this.likedBy = _newLikedBy;}
    public LocalDateTime getTime() {
        return this.time;
    }

    //functions

    public void raiseLikes(){
        this.likes += 1;
    }
    public void newLiker (Account _newLiker){
        this.likedBy.add(_newLiker);
    }
    public void removeLike(){
        this.likes -= 1;
    }
    public void lostLiker (Account _minusLiker){
        this.likedBy.remove(_minusLiker);
    }

}

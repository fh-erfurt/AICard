package de.aicard.Social;

import de.aicard.account.Account;
import de.aicard.account.AccountList;
import de.aicard.enums.Visibility;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The class message is used to store informations about a message  */
public class Message {

    private String message;
    private Account sender;
    private LocalDateTime time;
    private int likes;
    private AccountList likedBy  ;
    //constructor
    public Message(String newMessage, Account newSender){

        message = newMessage;
        sender = newSender;
        time = LocalDateTime.now();
        likes = 0;
        likedBy = null;

    }
    //Setter & getter

    public String getMessage()
    {
        return this.message;
    }
    public void setMessage(String newMessage) {
        this.message = newMessage;
    }
    public int getLikes()
    {
        return this.likes;
    }
    public void setLikes (int number){this.likes = number;}
    public AccountList getLikedBy() throws  NullPointerException
    {
        if(this.likedBy == null)
        {
            throw new NullPointerException("message was liked by nobody.");
        }
        return this.likedBy;
    }
    public void setLikedby (AccountList newLikedBy) {this.likedBy = newLikedBy;}
    public LocalDateTime getTime() {
        return this.time;
    }

    //functions

    public void raiseLikes(){
        this.likes += 1;
    }
    public void newLiker (Account newLiker){
        this.likedBy.addPerson(newLiker);
    }
    public void removeLike(){
        this.likes -= 1;
    }
    public void lostLiker (Account minusLiker){
        this.likedBy.removePerson(minusLiker);
    }

}

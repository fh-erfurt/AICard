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
    private int likes;  //TODO size(likedBy) -> likes redundant? umsetzung unten
    private ArrayList<Account> likedBy  ;
    //constructor
    public Message(String _newMessage, Account _newSender){

        message = _newMessage;
        sender = _newSender;
        time = LocalDateTime.now();
        likes = 0;
        likedBy = new ArrayList<Account>();

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
    public ArrayList<Account> getLikedBy()
    {
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

    public void clickLike(Account _account) //todo clickLike und getLikes ersetzten raiseLikes, newLiker, removeLike und lostLiker
                                            //todo spart uns int like und zugriff in account
    {
        if(likedBy.contains(_account))
        {
            likedBy.remove(_account);
        }
        else
        {
            likedBy.add(_account);
        }
    }

    //public int getLikes()
    {
        return likedBy.size();
    }
}

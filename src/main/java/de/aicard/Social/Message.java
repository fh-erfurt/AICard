package de.aicard.Social;

import de.aicard.account.Account;
import de.aicard.account.AccountList;
import de.aicard.enums.Visibility;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Message {

    private String message;
    private Account sender;
    private LocalDateTime time;
    private int likes;
    private AccountList likedby ;
    //constructor
    public Message(String _message, Account _sender){

        message = _message;
        sender = _sender;
        time = LocalDateTime.now();
        likes = 0;
        likedby = null;

    }
    //Setter & getter

    public String get_message() {
        return message;
    }
    public void set_message(String _message) {
        this.message = _message;
    }
    public int get_likes() {
        return likes;
    }
    public void set_likes (int _number){this.likes = _number;}
    public AccountList get_likedby(){ return likedby;}
    public void set_likedby (AccountList _likedby) {this.likedby = _likedby;}
    public LocalDateTime get_time() {
        return time;
    }

    //functions

    public void raise_likes(){
        this.likes += 1;
    }
    public void newliker (Account _account){
        likedby.addPerson(_account);
    }
    public void remove_Like(){
        this.likes -= 1;
    }
    public void lostliker (Account _account){
        likedby.removePerson(_account);
    }

}

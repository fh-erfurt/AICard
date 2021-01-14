package de.aicard.Social;

import de.aicard.account.Account;
import de.aicard.account.AccountList;
import de.aicard.enums.Visibility;

import java.time.LocalDateTime;


public class Message {

    private String message;
    private Account sender;
    private LocalDateTime time;
    private int likes;

    //constructor
    public Message(String _message, Account _sender){

        message = _message;
        sender = _sender;
        time = LocalDateTime.now();
        likes = 0;
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
    public LocalDateTime get_time() {
        return time;
    }

    //functions

    public void raise_likes(){
        //TODO Kopplung an Account? Alle können nur jeweils 1 mal liken.
        this.likes += 1;
    }

    public void removeLike(){
        //TODO Kopplung an Accont? Alle können nur eigene likes entfernen.
        this.likes -= 1;
    }



}

package de.aicard.Social;

import de.aicard.account.Account;
import de.aicard.account.AccountList;
import de.aicard.enums.Visibility;

import java.time.LocalDateTime;


public class Message {

    private String message;
    private Account sender;
    private String sendersname;
    private LocalDateTime time;
    private int likes;

    //constructor
    public Message(){

        message = null;
        sendersname = Account.getName();
        time = LocalDateTime.now();
        likes = 0;
    }
    public Message(String _message){

        message = _message;
        sendersname = Account.getName();
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

    public String get_sender() {
        return sendersname;
    }
    public LocalDateTime get_time() {
        return time;
    }

    public int get_likes() {
        return likes;
    }
    public void raise_likes(){this.likes += 1;}



}

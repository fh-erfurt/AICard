package de.aicard.account;
import de.aicard.enums.Visibility;

import java.time.LocalDateTime;


public class Message {

    private String message;
    private Account sender;
    private int likes;
    private LocalDateTime time;


    public Message(){
        message = null;
        sender = new Account();
        likes = 0;
    }

}

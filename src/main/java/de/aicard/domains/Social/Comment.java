package de.aicard.domains.Social;

import de.aicard.domains.account.Account;
import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.Recommended;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * The class message is used to store informations about a message which are :
 * the message its self - the sender - the time it was sent - the number of likes and the people who liked this message as a list
 *
 *
 * @author Amine Semlali
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity
{
    
    private String message;
    @OneToOne
    private Account sender;
    private LocalDateTime time;
    private Recommended recommended;
    
    //constructor
    
    /**
     * Constructor of a Message.
     * @param newSender the person that sends the message
     * @param newMessage the message to send
     *
     * likes are initialized to 0  and time is the time the message was sent
     *
     * @author Amine Semlali
     */
    public Comment(String newMessage, Account newSender, Recommended newRecomended)
    {
        this.message = newMessage;
        this.sender = newSender;
        this.time = LocalDateTime.now();
        this.recommended = newRecomended;
    }

}


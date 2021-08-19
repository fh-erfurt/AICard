package de.aicard.domains.Social;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Recommended;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * The class message is used to store informations about a message which are :
 * the message its self - the sender - the time it was sent - the number of likes and the people who liked this message as a list
 *
 * @author Amine Semlali
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

    private String message;
    @OneToOne
    private Account sender;
    private LocalDateTime time;
    private Recommended recommended;

    //constructor

    /**
     * Constructor of a Message.
     *
     * @param newSender        the person that sends the message
     * @param newCommentString the message to send
     * @author Amine Semlali
     */
    public Comment(String newCommentString, Account newSender, Recommended newRecomended) {
        this.message = newCommentString;
        this.sender = newSender;
        this.time = LocalDateTime.now();
        this.recommended = newRecomended;
    }

}


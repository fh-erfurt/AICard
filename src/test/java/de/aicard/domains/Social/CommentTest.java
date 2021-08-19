package de.aicard.domains.Social;

import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Recommended;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for the functions of Message
 *
 * @author Semlali Amine
 */

public class CommentTest
{
    
    @Test
    public void CommentTest()
    {
        Account Account1 = new Account("Account@fh-erfurt.de", "adminAccount", "Account1", "Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
        Comment Comment = new Comment("Message1", Account1, Recommended.YES);
        
        Assertions.assertEquals(Comment.getSender().getName(), Account1.getName());
    }
}

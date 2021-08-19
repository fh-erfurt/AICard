package de.aicard.domains.learnset;

import de.aicard.domains.Social.Comment;
import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Recommended;
import de.aicard.domains.enums.Visibility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Test class for all the functions of LearnSet
 *
 * @author Martin Kuehlborn, Daniel Michel
 */
public class LearnSetTest
{
    
    /**
     * This is a helper function for many tests.
     *
     * @return a LearnSet to run further tests on.
     */
    public static LearnSet getTestLearnSet()
    {
        String learnSetTitle = "Title";
        String learnSetDescription = "Description of my Learnset";
        Faculty faculty = Faculty.APPLIED_COMPUTER_SCIENCE;
        CardList cardList = new CardList();
        ArrayList<CardContent> front = new ArrayList<>();
        ArrayList<CardContent> back = new ArrayList<>();
        ArrayList<Card> card = new ArrayList<Card>();
        for (int i = 0; i < 20; i++)
        {
            front.add(new CardContent("title" + i, "data" + i, DataType.TextFile));
            back.add(new CardContent("title" + i, "data" + i, DataType.TextFile));
            card.add(i, new Card(front.get(i), back.get(i)));
            cardList.addToList(card.get(i));
        }
        
        Account learnSetOwner = new Account("Account@fh-erfurt.de", "adminAccount", "Account", "Descrip", Faculty.APPLIED_COMPUTER_SCIENCE);
        LearnSet learnSet = new LearnSet(learnSetTitle, learnSetDescription, faculty, cardList, learnSetOwner, Visibility.PUBLIC);
        
        return learnSet;
    }
    
    
    @Test
    public void testingAdminList()
    {
        //given: a testLearnSet and three accounts
        LearnSet learnSet = getTestLearnSet();
        Account professor = new Account();
        Account student = new Account();
        Account myOwnerStudent = learnSet.getOwner();
        
        //when: adding those Accounts as admins to the LearnSet
        learnSet.addAdmin(professor);
        learnSet.addAdmin(student);
        learnSet.addAdmin(myOwnerStudent);
        
        //then: those accounts are in exactly this order in the admin array of the learnset
        Assertions.assertEquals(professor, learnSet.getAdminList().get(0));
        Assertions.assertEquals(student, learnSet.getAdminList().get(1));
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdminList().get(2));
        
        //when: trying to add one of those accounts again as admin
        learnSet.addAdmin(professor);
        //then: it is not added
        Assertions.assertEquals(3, learnSet.getAdminList().size());
        
        //when: removing the first admin account (professor) by index
        learnSet.removeAdmin(0);
        //then: the other two admins and only those remain in the adminList
        Assertions.assertEquals(student, learnSet.getAdminList().get(0));
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdminList().get(1));
        
        //when: removing student as admin
        learnSet.removeAdmin(student);
        //then: only myOwnerStudent remains in the adminList
        Assertions.assertEquals(1, learnSet.getAdminList().size());
        Assertions.assertEquals(myOwnerStudent, learnSet.getAdminList().get(0));
        
        //when: removing the last account as admin
        learnSet.removeAdmin(myOwnerStudent);
        //then: the AdminList is Empty
        Assertions.assertEquals(true, learnSet.getAdminList().isEmpty());
        
    }
    
    @Test
    public void testingCommentList()
    {
        //given: a learnSet, 2 accounts and 3 messages
        LearnSet learnSet = getTestLearnSet();
        Account student = learnSet.getOwner();
        Account professor = new Account();
        Comment comment1 = new Comment("Message 1", student, Recommended.YES);
        Comment comment2 = new Comment("Message 2", professor, Recommended.NO);
        Comment comment3 = new Comment("Message 3", student, Recommended.YES);
        
        //when: adding Messages to LearnSet
        learnSet.addComment(comment1);
        learnSet.addComment(comment2);
        learnSet.addComment(comment3);
        
        //then: the messages can be accessed
        Assertions.assertEquals("Message 1", learnSet.getCommentList().get(0).getMessage());
        
        
    }
    
    @Test
    public void testAuthorizationCheck()
    {
        //given: 3 Learnsets: one public, one protected, one private
        LearnSet publicLearnSet = getTestLearnSet();
        publicLearnSet.setVisibility(Visibility.PUBLIC);
        
        LearnSet protectedLearnSet = getTestLearnSet();
        protectedLearnSet.setVisibility(Visibility.PROTECTED);
        
        LearnSet privateLearnSet = getTestLearnSet();
        privateLearnSet.setVisibility(Visibility.PRIVATE);
        
        //also given: some Accounts
        
        Account ownerOfPublicLearnSet = publicLearnSet.getOwner();
        Account ownerOfProtectedLearnSet = protectedLearnSet.getOwner();
        Account ownerOfPrivateLearnSet = privateLearnSet.getOwner();
        Account friendOfAll = new Account("Friend@fh-erfurt.de", "friendAccount", "Friend", "Descrip", Faculty.APPLIED_COMPUTER_SCIENCE);
        ownerOfPublicLearnSet.addFriend(friendOfAll);
        ownerOfProtectedLearnSet.addFriend(friendOfAll);
        ownerOfPrivateLearnSet.addFriend(friendOfAll);
        Account allAlone = new Account("noFriend@fh-erfurt.de", "noFriendAccount", "noFriend", "Descrip", Faculty.APPLIED_COMPUTER_SCIENCE);
        
        //then: allAlone is only authorised to subscribe to the public LearnSet
        Assertions.assertTrue(publicLearnSet.isAuthorizedToAccessLearnSet(allAlone));
        Assertions.assertTrue(protectedLearnSet.isAuthorizedToAccessLearnSet(allAlone));
        Assertions.assertFalse(privateLearnSet.isAuthorizedToAccessLearnSet(allAlone));
        
        //then: friendOfAll is authorised to subscribe to the public and the protected LearnSet
        Assertions.assertTrue(publicLearnSet.isAuthorizedToAccessLearnSet(friendOfAll));
        Assertions.assertTrue(protectedLearnSet.isAuthorizedToAccessLearnSet(friendOfAll));
        Assertions.assertFalse(privateLearnSet.isAuthorizedToAccessLearnSet(friendOfAll));
        
        //then: the owner of the LearnSets are authorized to add their own Learnsets
        Assertions.assertTrue(publicLearnSet.isAuthorizedToAccessLearnSet(ownerOfPublicLearnSet));
        Assertions.assertTrue(protectedLearnSet.isAuthorizedToAccessLearnSet(ownerOfProtectedLearnSet));
        Assertions.assertTrue(privateLearnSet.isAuthorizedToAccessLearnSet(ownerOfPrivateLearnSet));
        
    }
}

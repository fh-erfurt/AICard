package de.aicard.domains.account;

import de.aicard.domains.enums.AcademicGrade;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.LearnSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for the functions of Account
 *
 * @author Antonio Blechschmidt, Semlali Amine
 */
public class AccountTest
{
    @Test
    void testOwnLearnSetManipulation()
    {
        //setup
        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR, Faculty.APPLIED_COMPUTER_SCIENCE);

        LearnSet learnSet1 = new LearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);


        prof1.ownLearnSets.add(learnSet1);

        //test deleteFromOwnedLearningSetsByIndex
        prof1.deleteOwnLearnSetsByIndex(0);
        Assertions.assertTrue(prof1.ownLearnSets.isEmpty());

        //setup for deleteFromOwnedLearningSetsLastElement()
        prof1.ownLearnSets.add(learnSet1);
        prof1.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);

        //test deleteFromOwnedLearningSetsLastElement()
        prof1.deleteOwnLearnSetByLastElement();
        Assertions.assertEquals(prof1.getOwnLearnSetByIndex(prof1.ownLearnSets.size()-1), learnSet1);

        //setup for deleteAllFromOwnedLearningSets()
        prof1.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);

        //test deleteAllFromOwnedLearningSets()
        prof1.deleteAllOwnLearnSets();
        Assertions.assertTrue(prof1.ownLearnSets.isEmpty());
    }
    
    @Test
    void testFavoriteLearnSetManipulation()
    {
        //setup
        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        prof2.createNewOwnLearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);
        prof2.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);
        
        //test addNewFavoriteSets() and getFavoriteSetByPosition()
        prof1.addNewFavoriteLearnSet(prof2.getOwnLearnSetByIndex(0));
        Assertions.assertEquals(prof2.getOwnLearnSetByIndex(0), prof1.getFavoriteLearnSetByIndex(0).getLearnSet());
        
        //test deleteFromOwnLearningSetsByIndex
        prof1.deleteFavoriteLearnSetByIndex(0);
        Assertions.assertTrue(prof1.favoriteLearnSets.isEmpty());
        
        //setup for deleteFromOwnLearningSetsLastElement()
        prof1.addNewFavoriteLearnSet(prof2.getOwnLearnSetByIndex(0));
        prof1.addNewFavoriteLearnSet(prof2.getOwnLearnSetByIndex(1));
        
        //test deleteFromOwnLearningSetsLastElement()
        prof1.deleteFavoriteLearnSetByLastElement();
        Assertions.assertEquals(prof1.getFavoriteLearnSetByIndex(prof1.favoriteLearnSets.size()-1).getLearnSet(), prof2.getOwnLearnSetByIndex(0));
        
        //setup for deleteAllFromOwnLearningSets()
        prof1.addNewFavoriteLearnSet(prof2.getOwnLearnSetByIndex(1));
        
        //test deleteAllFromOwnedLearningSets()
        prof1.deleteAllFavoriteLearnSets();
        Assertions.assertTrue(prof1.favoriteLearnSets.isEmpty());
    }
    
    @Test
    void testFriendManipulation()
    {
        //setup
        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof3 = new Professor("Prof@fh-erfurt.de","adminProf","Prof3","Professor3", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        
        //test addFriend() and getFriendByIndex()
        prof1.addFriend(prof2);
        Assertions.assertEquals(prof1.getFriendByIndex(0), prof2);
        
        //test removeFriend()
        prof1.removeFriend(prof2);
        Assertions.assertTrue(prof1.friends.isEmpty());
        
        //setup for removeFriendByIndex()
        prof1.addFriend(prof2);
        prof1.addFriend(prof3);
        
        //test removeFriendByIndex()
        prof1.removeFriend(0);
        Assertions.assertEquals(prof1.getFriendByIndex(0), prof3);
        
    }
    
    //might be added later
    /*
     @Test
     void testGroupManipulation()
     {
     //setup
     Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.A);
     Professor Prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.A);
     Prof1.createGroup();
     Prof2.createGroup();
     
     //test createGroup() and leaveGroup()
     Prof1.leaveGroup(Prof1.getGroups());
     Assertions.assertEquals(Prof1.getGroups(), null);
     
     //test joinGroup() negetiv
     Prof1.joinGroup(Group _group);
     Assertions.assertEquals(Prof1.getGroups(), null);
     //test joinGroup() positiv
     Prof1.joinGroup(Group _group);
     }
     **/

    @Test
    void testChatManipulation()
    {
        //setup
        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);

        //test addNewChat()
        prof1.addNewChat(prof2);
        Assertions.assertEquals(prof1.chats.get(0), prof2.chats.get(0));

        //test addNewChat()
        prof1.deleteChat(0);
        Assertions.assertTrue(prof1.chats.isEmpty());
    }

    @Test
    void testLoginandPasswordChange()
    {
        //setup
        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);

        //test login()
        Assertions.assertEquals(prof1.login("Prof@fh-erfurt.de", "adminProf"), "login was successful");
        Assertions.assertEquals(prof1.login("Prof@fh-erfurt.de", "adminProf123"), "login failed"); //wrong password
        Assertions.assertEquals(prof1.login("Prof@fh-erfurt.com", "adminProf"), "login failed");//wrong email

        //test resetPassword
        prof1.resetPassword("Prof@fh-erfurt.de", "adminProf123");
        Assertions.assertEquals(prof1.getPassword(), "adminProf123");
    }

    @Test
    void testMessageLikeManipulation()
    {
        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
        prof1.addNewChat(prof2);
        prof2.getChats().get(0).sendMessage("Hello",prof1);

        //test clickLike
        prof1.clicksLikeOfMessage(0,1); //likes Message of Prof2
        Assertions.assertEquals(prof1.getChats().get(0).getChatHistory().get(1).getLikes(),1);
        prof2.clicksLikeOfMessage(0,1); // likes own Message
        Assertions.assertEquals(prof1.getChats().get(0).getChatHistory().get(1).getLikes(),2);
    }
}
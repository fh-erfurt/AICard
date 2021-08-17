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
    // TODO : update test to current account state
//    @Test
//    void testOwnLearnSetManipulation()
//    {
//        //setup
//        Account Account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
//
//        LearnSet learnSet1 = new LearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);
//
//
//        Account1.ownLearnSets.add(learnSet1);
//
//        //test deleteFromOwnedLearningSetsByIndex
//        Account1.deleteOwnLearnSetsByIndex(0);
//        Assertions.assertTrue(Account1.ownLearnSets.isEmpty());
//
//        //setup for deleteFromOwnedLearningSetsLastElement()
//        Account1.ownLearnSets.add(learnSet1);
//        Account1.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);
//
//        //test deleteFromOwnedLearningSetsLastElement()
//        Account1.deleteOwnLearnSetByLastElement();
//        Assertions.assertEquals(Account1.getOwnLearnSetByIndex(Account1.ownLearnSets.size()-1), learnSet1);
//
//        //setup for deleteAllFromOwnedLearningSets()
//        Account1.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);
//
//        //test deleteAllFromOwnedLearningSets()
//        Account1.deleteAllOwnLearnSets();
//        Assertions.assertTrue(Account1.ownLearnSets.isEmpty());
//    }
//
//    @Test
//    void testFavoriteLearnSetManipulation()
//    {
//        //setup
//        Account account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
//        Account account2 = new Account("Account2@fh-erfurt.de","adminAccount2","Account2","Descrip2", Faculty.APPLIED_COMPUTER_SCIENCE);
//        account2.createNewOwnLearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);
//        account2.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE, Visibility.PUBLIC);
//
//        //test addNewFavoriteSets() and getFavoriteSetByPosition()
//        account1.addNewFavoriteLearnSet(account2.getOwnLearnSetByIndex(0));
//        Assertions.assertEquals(account2.getOwnLearnSetByIndex(0), account1.getFavoriteLearnSetByIndex(0).getLearnSet());
//
//        //test deleteFromOwnLearningSetsByIndex
//        account1.deleteFavoriteLearnSetByIndex(0);
//        Assertions.assertTrue(account1.learnsetAbos.isEmpty());
//
//        //setup for deleteFromOwnLearningSetsLastElement()
//        account1.addNewFavoriteLearnSet(account2.getOwnLearnSetByIndex(0));
//        account1.addNewFavoriteLearnSet(account2.getOwnLearnSetByIndex(1));
//
//        //test deleteFromOwnLearningSetsLastElement()
//        account1.deleteFavoriteLearnSetByLastElement();
//        Assertions.assertEquals(account1.getFavoriteLearnSetByIndex(account1.learnsetAbos.size()-1).getLearnSet(), account2.getOwnLearnSetByIndex(0));
//
//        //setup for deleteAllFromOwnLearningSets()
//        account1.addNewFavoriteLearnSet(account2.getOwnLearnSetByIndex(1));
//
//        //test deleteAllFromOwnedLearningSets()
//        account1.deleteAllFavoriteLearnSets();
//        Assertions.assertTrue(account1.learnsetAbos.isEmpty());
//    }
//
//    @Test
//    void testFriendManipulation()
//    {
//        //setup
//        Account account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
//        Account account2 = new Account("Account2@fh-erfurt.de","adminAccount2","Account2","Descrip2", Faculty.APPLIED_COMPUTER_SCIENCE);
//        Account account3 = new Account("Account3@fh-erfurt.de","adminAccount3","Account3","Descrip3", Faculty.APPLIED_COMPUTER_SCIENCE);
//
//        //test addFriend() and getFriendByIndex()
//        account1.addFriend(account2);
//        Assertions.assertEquals(account1.getFriendByIndex(0), account2);
//
//        //test removeFriend()
//        account1.removeFriend(account2);
//        Assertions.assertTrue(account1.friends.isEmpty());
//
//        //setup for removeFriendByIndex()
//        account1.addFriend(account2);
//        account1.addFriend(account3);
//
//        //test removeFriendByIndex()
//        account1.removeFriend(0);
//        Assertions.assertEquals(account1.getFriendByIndex(0), account3);
//
//    }
//
//    //might be added later
//    /*
//     @Test
//     void testGroupManipulation()
//     {
//     //setup
//     Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.A);
//     Professor Prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.A);
//     Prof1.createGroup();
//     Prof2.createGroup();
//
//     //test createGroup() and leaveGroup()
//     Prof1.leaveGroup(Prof1.getGroups());
//     Assertions.assertEquals(Prof1.getGroups(), null);
//
//     //test joinGroup() negetiv
//     Prof1.joinGroup(Group _group);
//     Assertions.assertEquals(Prof1.getGroups(), null);
//     //test joinGroup() positiv
//     Prof1.joinGroup(Group _group);
//     }
//     **/
//
////    @Test
////    void testChatManipulation()
////    {
////        //setup
////        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
////        Professor prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
////
////        //test addNewChat()
////        prof1.addNewChat(prof2);
////        Assertions.assertEquals(prof1.chats.get(0), prof2.chats.get(0));
////
////        //test addNewChat()
////        prof1.deleteChat(0);
////        Assertions.assertTrue(prof1.chats.isEmpty());
////    }
//////    @Test
//////    void testMessageLikeManipulation()
//////    {
//////        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
//////        Professor prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR,Faculty.APPLIED_COMPUTER_SCIENCE);
//////        prof1.addNewChat(prof2);
//////        prof2.getChats().get(0).sendMessage("Hello",prof1);
//////
//////        //test clickLike
//////        prof1.clicksLikeOfMessage(0,1); //likes Message of Prof2
//////        Assertions.assertEquals(prof1.getChats().get(0).getChatHistory().get(1).getLikes(),1);
//////        prof2.clicksLikeOfMessage(0,1); // likes own Message
//////        Assertions.assertEquals(prof1.getChats().get(0).getChatHistory().get(1).getLikes(),2);
//////    }
//
//    @Test
//    void testLoginandPasswordChange()
//    {
//        //setup
//        Account account1 = new Account("Account@fh-erfurt.de","adminAccount","Account1","Descrip1", Faculty.APPLIED_COMPUTER_SCIENCE);
//
//        //test login()
//        Assertions.assertEquals(account1.login("Account@fh-erfurt.de", "adminAccount"), "login was successful");
//        Assertions.assertEquals(account1.login("Account@fh-erfurt.de", "adminAccount123"), "login failed"); //wrong password
//        Assertions.assertEquals(account1.login("Prof@fh-erfurt.com", "adminProf"), "login failed");//wrong email
//
//        //test resetPassword
//        account1.resetPassword("Account@fh-erfurt.de", "adminProf123");
//        Assertions.assertEquals(account1.getPassword(), "adminProf123");
//    }


}
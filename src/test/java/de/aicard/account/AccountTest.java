package de.aicard.account;

import de.aicard.Social.Message;
import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearnSetAbo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest
{
    @Test
    void testCreatedLearnSetManipulation()
    {
        //setup
        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR);
        try
        {
            LearnSetAbo LearnSetAbo1 = new LearnSetAbo(new LearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE));
            LearnSetAbo LearnSetAbo2 = new LearnSetAbo(new LearnSet("Mehr IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE));
            
            
            
            //test createNewOwnedLearnSet and getOwnedLearnSetAboByPosition
            Prof1.createNewOwnLearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);
            Assertions.assertEquals(Prof1.getOwnLearnSetByIndex(0), LearnSetAbo1);
            
            //test deleteFromOwnedLearningSetsByIndex
            Prof1.deleteOwnLearnSetsByIndex(0);
            Assertions.assertEquals(Prof1.getOwnLearnSetByIndex(0), LearnSetAbo2);
            
            //setup for deleteFromOwnedLearningSetsLastElement()
            Prof1.createNewOwnLearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);
            Prof1.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);
            
            //test deleteFromOwnedLearningSetsLastElement()
            Prof1.deleteOwnLearnSetByLastElement();
            Assertions.assertEquals(Prof1.getOwnLearnSetByIndex(-1), LearnSetAbo1);
            
            //setup for deleteAllFromOwnedLearningSets()
            Prof1.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);
            
            //test deleteAllFromOwnedLearningSets()
            Prof1.deleteAllOwnLearnSets();
            Assertions.assertEquals(Prof1.getOwnLearnSetByIndex(0), LearnSetAbo2);
            
        }
        catch (Exception e){
            //OH NO! we have to do something!
        }
        
    }
    
    @Test
    void testFavoriteLearnSetManipulation()
    {
        //setup
        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR);
        Professor Prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR);
        Prof2.createNewOwnLearnSet("IT", "This is an IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);
        Prof2.createNewOwnLearnSet("IT", "This is the second IT Learnset", Faculty.APPLIED_COMPUTER_SCIENCE);
        
        //test addNewFavoriteSets() and getFavoriteSetByPosition()
        Prof1.addNewFavoriteLearnSet(Prof2.getOwnLearnSetByIndex(0));
        Assertions.assertEquals(Prof1.getFavoriteLearnSetByIndex(0), Prof2.getOwnLearnSetByIndex(0));
        
        //test deleteFromOwnedLearningSetsByIndex
        Prof1.deleteFavoriteLearnSetByIndex(0);
        Assertions.assertNull(Prof1.getOwnLearnSetByIndex(0));
        
        //setup for deleteFromOwnedLearningSetsLastElement()
        Prof1.addNewFavoriteLearnSet(Prof2.getOwnLearnSetByIndex(0));
        Prof1.addNewFavoriteLearnSet(Prof2.getOwnLearnSetByIndex(1));
        
        //test deleteFromOwnedLearningSetsLastElement()
        Prof1.deleteFavoriteLearnSetByLastElement();
        Assertions.assertEquals(Prof1.getFavoriteLearnSetByIndex(-1), Prof2.getOwnLearnSetByIndex(0));
        
        //setup for deleteAllFromOwnedLearningSets()
        Prof1.addNewFavoriteLearnSet(Prof2.getOwnLearnSetByIndex(1));
        
        //test deleteAllFromOwnedLearningSets()
        Prof1.deleteAllFavoriteLearnSets();
        Assertions.assertNull(Prof1.getOwnLearnSetByIndex(0));
    }
    
    @Test
    void testFriendManipulation()
    {
        //setup
        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.UNIVERSITY_PROFESSOR);
        Professor Prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.UNIVERSITY_PROFESSOR);
        Professor Prof3 = new Professor("Prof@fh-erfurt.de","adminProf","Prof3","Professor3", AcademicGrade.UNIVERSITY_PROFESSOR);
        
        //test addFriend() and getFriendByIndex()
        Prof1.addFriend(Prof2);
        Assertions.assertEquals(Prof1.getFriendByIndex(1), Prof2);
        
        //test removeFriend()
        Prof1.removeFriend(Prof2);
        Assertions.assertNull(Prof1.getFriendByIndex(1));
        
        //setup for removeFriendByIndex()
        Prof1.addFriend(Prof2);
        Prof1.addFriend(Prof3);
        
        //test removeFriendByIndex()
        Prof1.removeFriend(1);
        Assertions.assertEquals(Prof1.getFriendByIndex(1), Prof2);
        
    }
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
        //TODO Tests schreiben
    }
    
    boolean testLikeMessage()
    {
        boolean x = false;
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Message msg1 = new Message("this is message1",std); // liked message
        msg1.newLiker(std);
        Message msg2 = new Message("this is message2",std);// not liked message
        if (!std.likeMessage(msg1) && std.likeMessage(msg2)) {  x= true;}
        return x;
    }
    boolean testdislikeMessage()
    {
        boolean x = false;
        Student std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Message msg1 = new Message("this is message1",std);
        msg1.newLiker(std);// likes message
        Message msg2 = new Message("this is message2",std);// undo likes message
        if (std.dislikeMessage(msg1) && !std.dislikeMessage(msg2)) {  x= true;}
        return x;
    }
}
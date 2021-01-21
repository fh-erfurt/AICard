package de.aicard.account;

import de.aicard.Social.Message;
import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest
{
    @Test
    void testCreatedLearnSetManipulation()
    {
        //setup
        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.A);
        LearnSetAbo LearnSetAbo1 = LearnSetAbo(new LearnSet("IT", "This is an IT Learnset", Faculty.AppliedComputerScience));
        LearnSetAbo LearnSetAbo2 = LearnSetAbo(new LearnSet());

        //test createNewOwnedLearnSet and getOwnedLearnSetAboByPosition
        Prof1.createNewOwnedLearnSet("IT", "This is an IT Learnset", Faculty.AppliedComputerScience);
        Assertions.assertEquals(Prof1.getOwnedLearnSetAboByPosition(0), LearnSetAbo1);

        //test deleteFromOwnedLearningSetsByIndex
        Prof1.deleteFromOwnedLearningSetsByIndex(0);
        Assertions.assertEquals(Prof1.getOwnedLearnSetAboByPosition(0), LearnSetAbo2);

        //setup for deleteFromOwnedLearningSetsLastElement()
        Prof1.createNewOwnedLearnSet("IT", "This is an IT Learnset", Faculty.AppliedComputerScience);
        Prof1.createNewOwnedLearnSet("IT", "This is the second IT Learnset", Faculty.AppliedComputerScience);

        //test deleteFromOwnedLearningSetsLastElement()
        Prof1.deleteFromOwnedLearningSetsLastElement();
        Assertions.assertEquals(Prof1.getOwnedLearnSetAboByPosition(-1), LearnSetAbo1);

        //setup for deleteAllFromOwnedLearningSets()
        Prof1.createNewOwnedLearnSet("IT", "This is the second IT Learnset", Faculty.AppliedComputerScience);

        //test deleteAllFromOwnedLearningSets()
        Prof1.deleteAllFromOwnedLearningSets();
        Assertions.assertEquals(Prof1.getOwnedLearnSetAboByPosition(0), LearnSetAbo0);



    }

    @Test
    void testFavoriteLearnSetManipulation()
    {
        //setup
        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.A);
        Professor Prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.A);
        Prof2.createNewOwnedLearnSet("IT", "This is an IT Learnset", Faculty.AppliedComputerScience);
        Prof2.createNewOwnedLearnSet("IT", "This is the second IT Learnset", Faculty.AppliedComputerScience);

        //test addNewFavoriteSets() and getFavoriteSetByPosition()
        Prof1.addNewFavoriteSets(Prof2.getOwnedLearnSetAboByPosition(0));//TODO wie werden alle public Sets angezeigt
        Assertions.assertEquals(Prof1.getFavoriteSetByPosition(0), Prof2.getOwnedLearnSetAboByPosition(0));

        //test deleteFromOwnedLearningSetsByIndex
        Prof1.deleteFromFavoriteSetsByIndex(0);
        Assertions.assertEquals(Prof1.getOwnedLearnSetAboByPosition(0), null);

        //setup for deleteFromOwnedLearningSetsLastElement()
        Prof1.addNewFavoriteSets(Prof2.getOwnedLearnSetAboByPosition(0));
        Prof1.addNewFavoriteSets(Prof2.getOwnedLearnSetAboByPosition(1));

        //test deleteFromOwnedLearningSetsLastElement()
        Prof1.deleteFromFavoriteSetsLastElement();
        Assertions.assertEquals(Prof1.getFavoriteSetByPosition(-1), Prof2.getOwnedLearnSetAboByPosition(0));

        //setup for deleteAllFromOwnedLearningSets()
        Prof1.addNewFavoriteSets(Prof2.getOwnedLearnSetAboByPosition(1));

        //test deleteAllFromOwnedLearningSets()
        Prof1.deleteAllFromFavoriteSets();
        Assertions.assertEquals(Prof1.getOwnedLearnSetAboByPosition(0), null);
    }

    @Test
    void testFriendManipulation()
    {
        //setup
        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof1","Professor1", AcademicGrade.A);
        Professor Prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof2","Professor2", AcademicGrade.A);
        Professor Prof3 = new Professor("Prof@fh-erfurt.de","adminProf","Prof3","Professor3", AcademicGrade.A);

        //test addFriend()
        Prof1.addFriend(Prof2);
        Assertions.assertEquals(Prof1.getFriends(), Prof2);

        //test removeFriend()
        removeFriend(Prof2);
        Assertions.assertEquals(Prof1.getFriends(), null);

        //setup for removeFriendByIndex()
        Prof1.addFriend(Prof2);
        Prof1.addFriend(Prof3);

        //test removeFriendByIndex()
        removeFriendByIndex(1);
        Assertions.assertEquals(Prof1.getFriends(), Prof2);

    }

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
        //TODO weiß nicht wie ich in Group den Status ändere von Prof2
        Prof1.joinGroup(Group _group);
    }

    @Test
    void testChatManipulation()
    {
        //TODO verstehe nicht die Chatzugriffe in Account
    }

    boolean testLikeMessage()
    {
        boolean x = false;
        Student Std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);
        Message msg1 = new Message("this is message1",Std); // liked message
        msg1.newliker(Std);
        Message msg2 = new Message("this is message2",Std);// not liked message
        if ((Std.likeMessage(msg1) == false) && (Std.likeMessage(msg2) == true)) {  x= true;}
        return x;
    }
    boolean testdislikeMessage()
    {
        boolean x = false;
        Student Std = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);
        Message msg1 = new Message("this is message1",Std); // liked message
        msg1.newliker(Std);
        Message msg2 = new Message("this is message2",Std);// not liked message
        if ((Std.dislikeMessage(msg1) == true) && (Std.dislikeMessage(msg2) == false)) {  x= true;}
        return x;
    }
}

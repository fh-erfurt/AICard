package de.aicard.Social;

import de.aicard.account.AccountList;
import de.aicard.account.Professor;
import de.aicard.account.Student;
import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import de.aicard.enums.Visibility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 the class GroupTest was supposed to run tests for the Group class it will be implemented as soon as all the functionalities from Group
 are implemented
 * */
public class GroupTest {

    @Test
    public void testAddToGroup()
    {
        AccountList accountList = new AccountList();
        AccountList accountList2 = new AccountList();

        Student std1 = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.APPLIED_COMPUTER_SCIENCE);
        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);

        accountList2.addPerson(std1);
        accountList2.addPerson(prof1);

        accountList.addPerson(std1);
        Group group = new Group("group1", Visibility.PUBLIC,std1);
        group.addToGroup(prof1);

        Assertions.assertEquals(accountList2, group.getMembers());

    }

}

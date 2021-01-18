package de.aicard.Social;

import de.aicard.account.AccountList;
import de.aicard.account.Professor;
import de.aicard.account.Student;
import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import de.aicard.enums.Visibility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void TestAddToGroup()
    {
        AccountList accountlist = new AccountList();
        AccountList accountlist2 = new AccountList();

        Student Std1 = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);
        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);

        accountlist2.addPerson(Std1);
        accountlist2.addPerson(Prof1);

        accountlist.addPerson(Std1);
        Group group = new Group("group1", Visibility.PUBLIC,Std1);
        group.addToGroup(Prof1);

        Assertions.assertEquals(accountlist2, group.get_members());

    }

}

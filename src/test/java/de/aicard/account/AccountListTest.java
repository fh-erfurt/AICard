package de.aicard.account;

import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountListTest {
    @Test
    public void testAddRemoveFromAccountList()
    {
        AccountList AccList1 = new AccountList();

        Professor Prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UniversityProfessor);
        Student Std1 = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);

        AccList1.addPerson(Prof1);
        AccList1.addPerson(Std1);

        AccountList AccList2 = new AccountList();

        Professor Prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UniversityProfessor);
        Professor Prof3 = new Professor("Prof2@fh-erfurt.de","adminProf2","Prof2","Professor2", AcademicGrade.AcademicCouncil);
        Student Std2 = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);
        Student Std3 = new Student("Std2@fh-erfurt.de","adminStd2","Std2","Student2", 3, Faculty.Architecture);

        AccList2.addPerson(Prof2);
        AccList2.addPerson(Std2);
        AccList2.addPerson(Prof3);
        AccList2.addPerson(Std3);
        AccList2.removePerson(Prof3);
        AccList2.removePerson(Std3);

        Assertions.assertEquals(AccList1, AccList2);

    }


}

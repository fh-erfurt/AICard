package de.aicard.account;

import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountListTest {
    @Test
    public void testAddRemoveFromAccountList()
    {
        AccountList accList1 = new AccountList();

        Professor prof1 = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);
        Student std1 = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);

        accList1.addPerson(prof1);
        accList1.addPerson(std1);

        AccountList accList2 = new AccountList();

        Professor prof2 = new Professor("Prof@fh-erfurt.de","adminProf","Prof","Professor", AcademicGrade.UNIVERSITY_PROFESSOR);
        Professor prof3 = new Professor("Prof2@fh-erfurt.de","adminProf2","Prof2","Professor2", AcademicGrade.ACADEMIC_COUNCIL);
        Student std2 = new Student("Std@fh-erfurt.de","adminStd","Std","Student", 3, Faculty.AppliedComputerScience);
        Student std3 = new Student("Std2@fh-erfurt.de","adminStd2","Std2","Student2", 3, Faculty.Architecture);

        accList2.addPerson(prof2);
        accList2.addPerson(std2);
        accList2.addPerson(prof3);
        accList2.addPerson(std3);
        accList2.removePerson(prof3);
        accList2.removePerson(std3);

        Assertions.assertEquals(accList1, accList2);

    }


}

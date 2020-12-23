package de.aicard.account;

import de.aicard.enums.AcademicGrade;
import de.aicard.enums.Faculty;

import java.util.ArrayList;

public class Student extends Account{
    private int semester;
    private Faculty faculty;

    public Student(String _email, String _password, String _name, String _description,int _semester,Faculty _faculty)
    {
        this.email = _email;
        this.password = _password;
        this.name = _name;
        this.description = _description;
        this.ownedLearningSets = new ArrayList<LearnSetAbo>();
        this.favoriteSets = new ArrayList<LearnSetAbo>();
        this.friends = new AccountList();
        this.chats = new ArrayList<Chat>();
        this.groups = new ArrayList<Groups>();
        this.semester  = _semester;
        this.faculty = _faculty;

    }
}

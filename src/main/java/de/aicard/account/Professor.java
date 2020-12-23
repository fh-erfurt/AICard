package de.aicard.account;

import de.aicard.Social.Chat;
import de.aicard.Social.Group;
import de.aicard.enums.AcademicGrade;

import java.util.ArrayList;

public class Professor extends Account{
    private AcademicGrade academicGrade;

    public Professor(String _email, String _password, String _name, String _description, AcademicGrade _academic)
    {
        this.email = _email;
        this.password = _password;
        this.name = _name;
        this.description = _description;
        this.ownedLearningSets = new ArrayList<LearnSetAbo>();
        this.favoriteSets = new ArrayList<LearnSetAbo>();
        this.friends = new AccountList();
        this.chats = new ArrayList<Chat>();
        this.groups = new ArrayList<Group>();
        this.academicGrade = _academic;

    }
}
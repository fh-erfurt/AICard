package de.aicard.domains.account;

import de.aicard.domains.Social.Chat;
import de.aicard.domains.enums.AcademicGrade;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;


/**
 * extends the account class
 * additional variable academicGrade
 * @author Clemens Berger
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue( value = "professor")
public class Professor extends Account
{
    
    private AcademicGrade academicGrade;

    public Professor(String _email, String _password, String _name, String _description, AcademicGrade _academic)
    {
        this.email = _email;
        this.password = _password;
        this.name = _name;
        this.description = _description;
        this.ownLearnSets = new ArrayList<LearnSet>();
        this.favoriteLearnSets = new ArrayList<LearnSetAbo>();
        this.friends = new ArrayList<Account>();
        this.chats = new ArrayList<Chat>();
        this.academicGrade = _academic;
    }

}

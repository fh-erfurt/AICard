package de.aicard.domains.account;

import de.aicard.domains.Social.Chat;
import de.aicard.domains.enums.Faculty;
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
 * additional variables semester and faculty
 * @author Clemens Berger
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@DiscriminatorValue( value = "student")
public class Student extends Account
{
    private int semester;
    

    public Student(String _email, String _password, String _name, String _description,int _semester,Faculty _faculty)
    {
        this.email = _email;
        this.password = _password;
        this.name = _name;
        this.description = _description;
        this.ownLearnSets = new ArrayList<LearnSet>();
        this.favoriteLearnSets = new ArrayList<LearnSetAbo>();
        this.friends = new ArrayList<Account>();
        this.chats = new ArrayList<Chat>();
        this.semester  = _semester;
        this.faculty = _faculty;
    }
    
}

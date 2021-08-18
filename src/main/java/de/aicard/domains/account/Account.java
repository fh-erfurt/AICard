package de.aicard.domains.account;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * is the stencil for the subclasses Professor and Student
 * contains basic attributes of and account, who get more specific in the subclasses
 * Provides the user with the functionalities to personalize their account
 *
 * @author Antonio Blechschmidt
 */
@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity
{
    //Logger
    private static final Logger logger = Logger.getLogger(Account.class.getName());
    //Attribute
    
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String description;
    private Faculty faculty;

    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<LearnSetAbo> learnsetAbos;

    @Setter(AccessLevel.NONE)
    @ManyToMany( cascade = CascadeType.ALL)
    private List<Account> friends;
    
    /**
     * constructor for tests
     * @param _newEmail
     * @param _newPassword
     * @param _newName
     * @param _newDescription
     * @param _newFaculty
     */
    public Account(String _newEmail, String _newPassword, String _newName, String _newDescription , Faculty _newFaculty)
    {
        this.email = _newEmail;
        this.password = _newPassword;
        this.name = _newName;
        this.description  =  _newDescription;
        this.faculty = _newFaculty;
        this.learnsetAbos = new ArrayList<>();
        this.friends = new ArrayList<>() ;
    }

    
    /**Create a new LearnSetAbo with a new LearnSet
     * puts the new LearnSetAbo in ownLearnSets
     *
     * @param _title        |
     * @param _description  | provided Imformation for the Constructor of LearnSet
     * @param _faculty      |
     * @param _visibility   |
     */
    public void createNewOwnLearnSet(String _title, String _description, Faculty _faculty, Visibility _visibility)
    {
        try
        {
            LearnSet newLearnSet = new LearnSet(_title, _description, _faculty,new CardList(),this,_visibility);
            newLearnSet.setOwner(this);
            newLearnSet.addAdmin(this);
            this.addLearnSetAbo(newLearnSet);
        }
        catch (Exception e)
        {
            logger.warning(e.getMessage());
        }
    }
    
 
    
    /**
     * adds learnSetAbo into learnSetAbo list with a given learnset
     *
     * @param learnSet
     */
    public void addLearnSetAbo(LearnSet learnSet)
    {
        if(learnSet.isAuthorizedToAccessLearnSet(this))
        {
            try
            {
                this.learnsetAbos.add(new LearnSetAbo(learnSet));
            }
            catch (Exception e)
            {
                logger.warning(e.getMessage());
            }
        }
    }
    
    /**
     * removes learnsetabo from learnsetabo list
     *
     * @param _learnSetAbo
     */
    public void removeLearnSetAbo(LearnSetAbo _learnSetAbo)
    {
        this.learnsetAbos.remove(_learnSetAbo);
    }
    
    /**
     * method removes learnsetabos from learnsetabo list by learnset and returns it
     *
     * @param learnSet
     * @return
     */
    public LearnSetAbo removeLearnSetAboByLearnSet(LearnSet learnSet){
        for(int i = this.learnsetAbos.size()-1;i>=0;i--){
            if(this.learnsetAbos.get(i).getLearnSet().equals(learnSet)){
                LearnSetAbo abo = this.learnsetAbos.get(i);
                this.removeLearnSetAbo(abo);
                return abo;
            }
        }
        return null;
    }
    
    //Friends
    
    /**
     * adds account as friend into friend list
     *
     * @param _friend
     */
    public void addFriend(Account _friend)
    {
        this.friends.add(_friend);
    }
    
    /**
     * removes friend from friend list
     *
     * @param _friend
     */
    public void removeFriend(Account _friend)
    {
        this.friends.remove(_friend);
    }

}
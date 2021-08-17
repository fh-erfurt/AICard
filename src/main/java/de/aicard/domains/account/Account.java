package de.aicard.domains.account;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.domains.learnset.LearnSet;
import lombok.*;

import javax.persistence.*;
import java.util.*;
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
    protected String email;
    protected String password;
    protected String name;
    protected String description;
    protected Faculty faculty;
    
//    @Setter(AccessLevel.NONE)
//    @ManyToMany(cascade = CascadeType.ALL)
//    protected List<LearnSet> ownLearnSets;
    // um festzustellen ob das Learnset von dem account ist wird durch die abos iteriert und die ownerid verglichen
    // learnSet -> isAccountID in AdminList
    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<LearnSetAbo> learnsetAbos;

    @Setter(AccessLevel.NONE)
    @ManyToMany( cascade = CascadeType.ALL)
    protected List<Account> friends;


    public Account(String _newEmail, String _newPassword, String _newName, String _newDescription , Faculty _newFaculty)
    {
        this.email = _newEmail;
        this.password = _newPassword;
        this.name = _newName;
        this.description  =  _newDescription;
        this.faculty = _newFaculty;
        this.learnsetAbos = new ArrayList<LearnSetAbo>();
        this.friends = new ArrayList<Account>() ;
    }




    /**
     * Setter for ArrayLists are not Required
     */
    
    //Advanced Getter, Setter and Delete for ArrayLists
    
    //ownLearnAboSet
    
//    public LearnSet getOwnLearnSetByIndex(int _index)
//    {
//        return ownLearnSets.get(_index);
//    }
    
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
    


    public LearnSetAbo removeLearnSetAboByLearnSet(LearnSet learnSet){
        for(int i = this.learnsetAbos.size()-1;i>=0;i--){
            if(this.learnsetAbos.get(i).getLearnSet().equals(learnSet)){
                LearnSetAbo abo = this.learnsetAbos.get(i);
                this.learnsetAbos.remove(abo);
                return abo;
            }
        }
        return null;
    }
    //learnSetAbos
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
    
    public void removeLearnSetAbo(LearnSetAbo _learnSetAbo)
    {
        this.learnsetAbos.remove(_learnSetAbo);
    }

    
    //Friends

    public Account getFriendByIndex(int _index)
    {
        return this.friends.get(_index);
    }

    public void addFriend(Account _friend)
    {
        this.friends.add(_friend);
    }
    
    public void removeFriend(Account _friend)
    {
        this.friends.remove(_friend);
    }
    
    public void removeFriend(int _friend)
    {
        this.friends.remove(_friend);
    }


    /** after checking if there is no existent chat with a person, a new chat is created with that person and is added to the chat list
     *  @author  Semlali Amine
     */
    
    //Methods
    
    /**
     * the function clicksLikeOfMessage is used on a specific message to like or dislike it
     *
     * clickLike checks if a person is in the list of those who liked the message
     * if this account liked the message, his name is removed from the list (dislike)
     * if this account didn't like the message,his name is added to the list (like)
     *
     * @author Amine Semlali
     */
    

    /**Checks User for valid login data
     *
     * @param _email        |
     * @param _password     | if both are right user will be logged in
     */
    public String login(String _email, String _password)
    {
        String email = getEmail();
        String password = getPassword();

        if (email.equals(_email) && password.equals(_password))
        {
            return "login was successful";
        }
        else
        {
            return "login failed";
        }
    }
    /**Checks User for valid login data
     *
     * @param _email            | if right password will be changed to new password
     * @param _newPassword      | new password
     */
    public void resetPassword(String _email, String _newPassword)
    {
        String email = getEmail();

        if (email.equals(_email))
        {
            setPassword(_newPassword);
        }
    }
    
}
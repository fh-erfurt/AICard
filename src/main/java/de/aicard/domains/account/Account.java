package de.aicard.domains.account;

import de.aicard.domains.Social.Chat;
import de.aicard.domains.BaseEntity;
import de.aicard.domains.card.Card;
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
    
    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<LearnSet> ownLearnSets;
    // um festzustellen ob das Learnset von dem account ist wird durch die abos iteriert und die ownerid verglichen
    // learnSet -> isAccountID in AdminList
    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<LearnSetAbo> learnsetAbos;

    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<Account> friends;

    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    protected List<Chat> chats;



    public Account(String _newEmail, String _newPassword, String _newName, String _newDescription , Faculty _newFaculty)
    {
        this.email = _newEmail;
        this.password = _newPassword;
        this.name = _newName;
        this.description  =  _newDescription;
        this.faculty = _newFaculty;
        this.learnsetAbos = new ArrayList<LearnSetAbo>();
        this.ownLearnSets = new ArrayList<LearnSet>();
        this.friends = new ArrayList<Account>() ;
        this.chats = new ArrayList<Chat>();
    }




    /**
     * Setter for ArrayLists are not Required
     */
    
    //Advanced Getter, Setter and Delete for ArrayLists
    
    //ownLearnAboSet
    
    public LearnSet getOwnLearnSetByIndex(int _index)
    {
        return ownLearnSets.get(_index);
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
            this.ownLearnSets.add(newLearnSet);
            this.addNewFavoriteLearnSet(newLearnSet);
        }
        catch (Exception e)
        {
            logger.warning(e.getMessage());
        }
    }
    
    public void deleteOwnLearnSetsByIndex(int _index)
    {
        this.ownLearnSets.remove(_index);
    }
    
    public void deleteOwnLearnSetByLastElement()
    {
        this.ownLearnSets.remove(this.ownLearnSets.size() - 1);
    }
    
    public void deleteAllOwnLearnSets()
    {
        this.ownLearnSets.clear();
    }

    public void deleteLearnSetAboByLearnSet(LearnSet learnSet){
        this.learnsetAbos.removeIf(learnSetAbo -> learnSetAbo.getLearnSet().equals(learnSet));
    }
    
    //favoriteLearnSets
    
    public LearnSetAbo getFavoriteLearnSetByIndex(int _index)
    {
        return this.learnsetAbos.get(_index);
    }
    
    public void addNewFavoriteLearnSet(LearnSet _favoriteSet)
    {
        if(_favoriteSet.isAuthorizedToAccessLearnSet(this))
        {
            try
            {
                this.learnsetAbos.add(new LearnSetAbo(_favoriteSet));
            }
            catch (Exception e)
            {
                logger.warning(e.getMessage());
            }
        }
    }
    
    public void deleteFavoriteLearnSetByLearnSetAbo(LearnSetAbo _learnSetAbo)
    {
        this.getLearnsetAbos().remove(_learnSetAbo);
    }
    
    public void deleteFavoriteLearnSetByIndex(int _index)
    {
        this.learnsetAbos.remove(_index);
    }
    
    public void deleteFavoriteLearnSetByLastElement()
    {
        this.learnsetAbos.remove(this.learnsetAbos.size() - 1);
    }
    
    public void deleteAllFavoriteLearnSets()
    {
        this.learnsetAbos.clear();
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

    //Chat

    /** isAlreadyInChatWith is a function that checks if there is an existent chat with a person
     *  @author  Semlali Amine
     */
    private boolean isAlreadyInChatWith(Account _account)
    {
        for (Chat chat:this.chats)
        {
            if(chat.getParticipants().contains(_account) && chat.getParticipants().size() == 2)
            {
                return true;
            }
        }
        return false;
    }
    /** after checking if there is no existent chat with a person, a new chat is created with that person and is added to the chat list
     *  @author  Semlali Amine
     */
    public void addNewChat(Account _account)
    {
        if (!isAlreadyInChatWith(_account))
        {
            chats.add(new Chat(_account, this, "Hallo "+ _account.getName() +", von "+ this.getName()));
        }
    }

    public void addChat(Chat _chat)
    {
        this.chats.add(_chat);
    }

    /** deleteChat is a function that deletes a chat from the chats list
     *  @author  Semlali Amine
     */
    public void deleteChat(int _chat)
    {
        this.chats.remove(_chat);
    }
    
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
    public void clicksLikeOfMessage(int _chatIndex ,int _messageIndex)
    {
        this.getChats().get(_chatIndex).getChatHistory().get(_messageIndex).clickLike(this);
    }

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
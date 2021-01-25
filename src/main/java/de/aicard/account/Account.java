package de.aicard.account;

import de.aicard.Social.Chat;
import de.aicard.Social.Message;
import de.aicard.Social.MessageList;
import de.aicard.enums.Faculty;
import de.aicard.learnset.LearnSet;

import java.util.List;

/**
 * is the stencil for the subclasses Professor and Student
 * contains basic attributes of and account, who get more specific in the subclasses
 * Provides the user with the functionalities to personalize their account
 *
 * @Author Antonio Blechschmidt
 */

public abstract class Account
{
    
    
    //Attribute
    protected String email;
    protected String password;
    protected String name;
    protected String description;
    protected List<LearnSetAbo> ownLearnSets;
    protected List<LearnSetAbo> favoriteLearnSets;
    protected AccountList friends;
    protected List<Chat> chats;
    
    
    //no Constructor cause abstract, Constructor in subclasses Professor and Student
    
    
    //Setter + Getter
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String _email)
    {
        this.email = _email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String _password)
    {
        this.password = _password;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String _name)
    {
        this.name = _name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String _description)
    {
        this.description = _description;
    }
    
    public List<LearnSetAbo> getOwnLearnSets()
    {
        return this.ownLearnSets;
    }
    
    public AccountList getFriends()
    {
        return this.friends;
    }
    
    public List<Chat> getChats()
    {
        return this.chats;
    }
    
    
    /**
     * Setter for ArrayLists are not Required
     */
    
    //Advanced Getter, Setter and Delete for ArrayLists
    
    //ownLearnAboSet
    
    public LearnSetAbo getOwnLearnSetByPosition(int _position)
    {
        return ownLearnSets.get(_position);
    }
    
    /**Create a new LearnSetAbo with a new LearnSet
     * puts the new LearnSetAbo in ownLearnSets
     *
     * @param _title        |
     * @param _description  | provided Imformation for the Constructor of LearnSet
     * @param _faculty      |
     */
    public void createNewOwnLearnSet(String _title, String _description, Faculty _faculty)
    {
        try
        {
            ownLearnSets.add(new LearnSetAbo(new LearnSet(_title, _description, _faculty)));
        }
        catch (Exception e){
            // Do something here ;)
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
    
    //favoriteLearnSets
    
    public LearnSetAbo getFavoriteSetByPosition(int _position)
    {
        return this.favoriteLearnSets.get(_position);
    }
    
    public void addNewFavoriteSet(LearnSetAbo _favoriteSet)
    {
        this.favoriteLearnSets.add(_favoriteSet);
    }
    
    public void deleteFavoriteSetByIndex(int _index)
    {
        this.favoriteLearnSets.remove(_index);
    }
    
    public void deleteFavoriteSetByLastElement()
    {
        this.favoriteLearnSets.remove(this.favoriteLearnSets.size() - 1);
    }
    
    public void deleteAllFavoriteSets()
    {
        this.favoriteLearnSets.clear();
    }
    
    //friends
    
    public void addFriend(Account _friend){
        this.friends.addPerson(_friend);
    }
    
    public void removeFriend(Account _friend){
        this.friends.removePerson(_friend);
    }
    
    public void removeFriend(int _friend){ this.friends.removePerson(_friend);
    }
    
    //chats
    //TODO Chat class?
    public void editMessage(Message _message, String _editedMessage)
    {
        _message.setMessage(_editedMessage);
    }
    //TODO Chat class?
    public void deleteMessage (Message _message, MessageList _messagelist)
    {
        _messagelist.removeMessage(_message);
    }
    
    public void deleteChat(Chat _chat)
    {
        this.chats.remove(_chat);
    }
    /*
    public void leaveGroup(int _index){
        this.groups.remove(_index);
    }

     */
    
    //Methods
    
    //TODO Chat class?
    public boolean likeMessage(Message _message) {
        if (!(_message.getLikedBy()).contain(this))
        { _message.raiseLikes(); _message.newLiker(this); return true; }
        else{return false;}
    }
    
    //TODO Chat class?
    public boolean dislikeMessage(Message _message){
        if ((_message.getLikedBy()).contain(this))
        { _message.removeLike(); _message.lostLiker(this); return true;}
        else{return false;}
    }
    //TODO signIn = Constructor in Subclassen?
    //TODO (Mit RegEx oder similar abgleich auf validität)) --> setEmail?
    
    public void login(String _email, String _password)
    {
        String email = getEmail();
        String password = getPassword();
        
        if (email == _email && password == _password)
        {
            System.out.print("login was successful");
            return;
        }
        else
        {
            System.out.print("login failed");
        }
    }
    
    public void resetPassword(String _email, String _password)
    {
        String email = getEmail();
        
        if (email == _email)
        {
            setPassword(_password);
        }
    }
}
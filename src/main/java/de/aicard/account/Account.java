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
    protected List<LearnSetAbo> ownedLearningSets;
    protected List<LearnSetAbo> favoriteSets;
    protected AccountList friends;
    protected List<Chat> chats;
    
    
    //Constructor CreateAccount

    
    //Setter + Getter
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public List<LearnSetAbo> getOwnedLearningSets()
    {
        return this.ownedLearningSets;
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

    /*
     * public void setOwnedLearningSets(ArrayList<LearnSet> _NewLearningSets)
     * {
     *   this.ownedLearningSets = _NewLearningSets;
     * }
     *
     *
     * public void setFavoriteSets (ArrayList<LearnSet> (_NewFavoriteSets)
     * {
     *   this.favoriteSets = _NewFavoriteSets;
     * }
     *
     * */
    
    //Advanced Getter, Setter and Delete for ArrayLists
    public LearnSetAbo getOwnedLearnSetAboByPosition(int _position)
    {
        return ownedLearningSets.get(_position);
    }
    
    public void createNewOwnedLearnSet(String _title, String _description, Faculty _faculty)
    {
        try
        {
            ownedLearningSets.add(new LearnSetAbo(new LearnSet(_title, _description, _faculty)));
        }
        catch (Exception e){
        //Do something here ;)
        }
    }

    
    public void deleteFromOwnedLearningSetsByIndex(int _index)
    {
        this.ownedLearningSets.remove(_index);
    }
    
    public void deleteFromOwnedLearningSetsLastElement()
    {
        this.ownedLearningSets.remove(this.ownedLearningSets.size() - 1);
    }
    
    public void deleteAllFromOwnedLearningSets()
    {
        this.ownedLearningSets.clear();
    }

    //favorites
    
    public LearnSetAbo getFavoriteSetByPosition(int _position)
    {
        return this.favoriteSets.get(_position);
    }
    
    public void addNewFavoriteSets(LearnSetAbo _favoriteSet)
    {
        this.favoriteSets.add(_favoriteSet);
    }
    
    public void deleteFromFavoriteSetsByIndex(int _index)
    {
        this.favoriteSets.remove(_index);
    }
    
    public void deleteFromFavoriteSetsLastElement()
    {
        this.favoriteSets.remove(this.favoriteSets.size() - 1);
    }
    
    public void deleteAllFromFavoriteSets()
    {
        this.favoriteSets.clear();
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

    public boolean likeMessage(Message _message) {
        if (!(_message.get_likedby()).contain(this))
        { _message.raise_likes(); _message.newliker(this); return true; }
        else{return false;}
    }
    public boolean dislikeMessage(Message _message){
        if ((_message.get_likedby()).contain(this))
        { _message.remove_Like(); _message.lostliker(this); return true;}
        else{return false;}
    }

    public void editMessage(Message _message, String _editedMessage)
    {
        _message.set_message(_editedMessage);
    }

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
    // TODO: Login + Signin Methode(Mit RegEx oder similar abgleich auf validität)), Reset Password,

}

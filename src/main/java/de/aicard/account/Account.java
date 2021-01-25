package de.aicard.account;

import de.aicard.Social.Chat;
import de.aicard.Social.Message;
import de.aicard.Social.Group;
import de.aicard.Social.MessageList;
import de.aicard.enums.Visibility;
import de.aicard.enums.Faculty;
import de.aicard.learnset.LearnSet;

import java.util.List;


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
    protected List<Group> groups;
    
    
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

    public List<Group> getGroups()
    {
        return this.groups;
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

    public void addFriend(Account newFriend){
        this.friends.addPerson(newFriend);
    }

    public void removeFriend(Account oldFriend){
        this.friends.removePerson(oldFriend);
    }

    public void removeFriend(int oldFriend){ this.friends.removePerson(oldFriend);
    }


    //groups

    public void createGroup(String groupName , Visibility groupPublicity){
        this.groups.add( new Group(groupName,groupPublicity,this)); //constructor von group;
    }

    public void joinGroup(Group theGroup){
        if(theGroup.getPublicity() == Visibility.PUBLIC) {
            this.groups.add(theGroup);
        }
    }


    public void leaveGroup(Group oldGroup){
        this.groups.remove(oldGroup);
    }

    public boolean likeMessage(Message theMessage) {
        if (!(theMessage.getLikedBy()).contain(this))
        { theMessage.raiseLikes(); theMessage.newLiker(this); return true; }
        else{return false;}
    }
    public boolean dislikeMessage(Message theMessage){
        if ((theMessage.getLikedBy()).contain(this))
        { theMessage.removeLike(); theMessage.lostLiker(this); return true;}
        else{return false;}
    }

    public void editMessage(Message theMessage, String newMessage)
    {
        theMessage.setMessage(newMessage);
    }

    public void deleteMessage (Message theMessage, MessageList messageList)
    {
        messageList.removeMessage(theMessage);
    }

    public void deleteChat(Chat theChat)
{
    this.chats.remove(theChat);
}

    
    //Methods
    // TODO: Login + Signin Methode(Mit RegEx oder similar abgleich auf validität)), Reset Password,

}

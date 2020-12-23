package de.aicard.account;

import de.aicard.enums.Visibility;
import de.aicard.learnset.LearnSet;
import de.aicard.enums.Faculty;
import de.aicard.account.AccountList;
import java.util.List;
import java.util.ArrayList;
import de.aicard.account.Groups;

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
    protected List<Groups> groups;
    
    
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
    } //XD

    public List<Chat> getChats()
    {
        return this.chats;
    }

    public List<Groups> getGroups()
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
        ownedLearningSets.add(new LearnSetAbo(new LearnSet(_title,_description,_faculty)));
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

    /*public void removeFriendByIndex(int _index){
        this.friends.remove(_index);
    }
*/

    //groups

    public void createGroup(){
        this.groups.add( new Groups()); //constructor von group;
    }


    public void joinGroup(Groups _group){
        if(_group.get_publicity() == PUBLIC) {
            this.groups.add(_group);
        }
    }

    public void leaveGroup(Groups _group){
        this.groups.remove(_group);
    }



    /*
    public void leaveGroup(int _index){
        this.groups.remove(_index);
    }

     */
    
    //Methods


}

package de.aicard.account;

import de.aicard.Social.Chat;
import de.aicard.Social.Message;
import de.aicard.enums.Faculty;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearnSetAbo;

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
    protected List<Account> friends; //Todo durch ArrayList ersetzen
    protected List<Chat> chats;
    
    
    //no Constructor cause abstract, Constructor in subclasses Professor and Student
    
    
    //Setter + Getter
    public String getEmail()
    {
        return email;
    }

    //todo regex
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
    
    public List<Account> getFriends()
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
    
    public LearnSetAbo getOwnLearnSetByIndex(int _index)
    {
        return ownLearnSets.get(_index);
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
    
    public LearnSetAbo getFavoriteLearnSetByIndex(int _index)
    {
        return this.favoriteLearnSets.get(_index);
    }
    
    public void addNewFavoriteLearnSet(LearnSetAbo _favoriteSet)
    {
        //Todo hier auf Visibility von LearnSet prüfen. In LearnSetAbo haben wir nicht die Info,
        //welcher Account das abonniert. Dafür müsste das Abo aber direkt hier erstellt werden.
        //Also LearnSet als Parameter und dann new LearnSetAbo(_favoriteSet).
        //können wir auch gerne morgen Abend zusammen schreiben oder ich setze mich mit deinem
        //ok dran. ~Daniel
        this.favoriteLearnSets.add(_favoriteSet);
    }
    
    public void deleteFavoriteLearnSetByIndex(int _index)
    {
        this.favoriteLearnSets.remove(_index);
    }
    
    public void deleteFavoriteLearnSetByLastElement()
    {
        this.favoriteLearnSets.remove(this.favoriteLearnSets.size() - 1);
    }
    
    public void deleteAllFavoriteLearnSets()
    {
        this.favoriteLearnSets.clear();
    }
    
    //friends

    public Account getFriendByIndex(int _index)
    {
        return this.friends.get(_index);
    }

    public void addFriend(Account _friend){
        this.friends.add(_friend);
    }
    
    public void removeFriend(Account _friend){
        this.friends.remove(_friend);
    }
    
    public void removeFriend(int _friend){ this.friends.remove(_friend);
    }

    //Chat

/** deleteChat is a function that deletes a chat(which is the argument of the function ) from the chats list
 *  @Author  Semlali Amine
 *  */

    public void deleteChat(Chat _chat)
    {
        this.chats.remove(_chat);
    }
    
    //Methods

    /** likeMessage is a function that adds a like to a message(which is the argument of the function).
     * if the person who wants to like has not liked this message yet and
     * to check if the person liked the message, there is an arraylist called likedBy that contains the persons who already liked.
     * the function returns true if the person was able to like the message otherwise it returns false
     * @Author Semlali Amine
     *   */

    public boolean likeMessage(Message _message)
    {
        if (!(_message.getLikedBy()).contains(this))
        { _message.raiseLikes(); _message.newLiker(this); return true; }
        else{return false;}
    }

    /** dislikeMessage is a function that removes a like from a message(which is the argument of the function).
     *  if the person who wants to dislike has already liked this message and
     *  to check if the person liked the message, there is an arraylist called likedBy that contains the persons who already liked.
     * the function returns true if the person was able to dislike the message otherwise it returns false
     * @Author Semlali Amine
     *   */

    public boolean dislikeMessage(Message _message)
    {
        if ((_message.getLikedBy()).contains(this))
        { _message.removeLike(); _message.lostLiker(this); return true;}
        else{return false;}
    }
    /** clickToLike is a function that runs the likeMessage
     * the function returns two statements depending on whether the message was liked or not
     * @Author Semlali Amine
     *   */

    //TODO clickLike
    public String clickToLike (Message _message)
    {

        if(likeMessage(_message))
        {
            return "you liked this message";
        }
        else
        {
            return "you already liked this message";
        }
    }
    /** clickToDislike is a function that runs the dislikeMessage
     * the function returns two statements depending on whether the message was disliked or not
     * @Author Semlali Amine
     *   */

    public String clickToDislike (Message _message)
    {

        if(dislikeMessage(_message))
        {
            return "you disliked this message";
        }
        else
        {
            return "you can't dislike this message";
        }
    }



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
    
    public void resetPassword(String _email, String _password)
    {
        String email = getEmail();
        
        if (email.equals(_email))
        {
            setPassword(_password);
        }
    }
}
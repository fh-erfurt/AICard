package de.aicard.account;

import de.aicard.Social.Chat;
import de.aicard.Social.Message;
import de.aicard.enums.Faculty;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearnSetAbo;

import java.util.ArrayList;
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
    protected ArrayList<LearnSetAbo> ownLearnSets;
    protected ArrayList<LearnSetAbo> favoriteLearnSets;
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

    private boolean isAlreadyInChatWith(Account _account)
    {
        for (Chat chat:this.chats)
        {
            if(chat.getParticipants().contains(_account) && chat.getParticipants().size() == 2)//todo in Chatklasse
            {
                return true;
            }
        }
        return false;
    }

    public void addNewChat(Account _account)
    {
        if (!isAlreadyInChatWith(_account))
        {
            chats.add(new Chat(_account, this, "")); //TODO Message notwendig?
        }
    }

    public void addChat(Chat _chat){
        this.chats.add(_chat);
    }

/** deleteChat is a function that deletes a chat from the chats list
 *  @Author  Semlali Amine
 *  */

    public void deleteChat(int _chat)
    {
        this.chats.remove(_chat);
    }
    
    //Methods

    public void clicksLikeOfMessage(int _chatIndex ,int _messageIndex) // todo ok?
    {
        this.getChats().get(_chatIndex).getChatHistory().get(_messageIndex).clickLike(this);
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
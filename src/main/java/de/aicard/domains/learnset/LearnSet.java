package de.aicard.domains.learnset;

import de.aicard.domains.Social.Comment;
import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Recommended;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Provides CardList with additional information:
 * Title, Description, Faculty(to which Accounts from which Faculties it will be shown)
 * CommentList, Evaluations(only 1 per Account->LearnSetAbo is possible)
 * Owner(Account who created the LearnSet), Admins(Accounts who can edit the LearnSet)
 * Visibility(to show the LearnSet to all Account, to Faculty and Friends or only to Owner)
 *
 * @author Martin Kuehlborn
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
public class LearnSet extends BaseEntity
{
    // CLASS VARIABLES
    private static final Logger logger = Logger.getLogger(LearnSet.class.getName());
 
    // MEMBER VARIABLES
    private String title;
    
    /**
     * Short Text Description about the LearnSet content
     */
    private String description;
    
    /**
     * Which Faculty is the LearnSet acquainted with
     */
    private Faculty faculty;
    
    @OneToOne(cascade = CascadeType.ALL)
    private CardList cardList;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;
    
    /**
     * The Account who created and owns the LearnSet
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Account owner;
    
    /**
     * Visibility for Accounts who are not the owner
     */
    private Visibility visibility;
    
    /**
     * List of people who can edit the learnset
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> adminList;
    
    /**
     * Average evaluation of a LearnSet
     */


    // CONSTRUCTORS

    public LearnSet(CardList _newCardList)
    {
        this(null, null, null, _newCardList, null, Visibility.PRIVATE);
    }
    
    public LearnSet(String _newTitle, Faculty _newFaculty, CardList _newCardList, Account _newOwner)
    {
        this(_newTitle, null, _newFaculty, _newCardList, _newOwner, Visibility.PRIVATE);
    }
    
    public LearnSet(String _newTitle, String _newDescription, Faculty _newFaculty)
    {
        this(_newTitle, _newDescription, _newFaculty, new CardList(), null, Visibility.PRIVATE);
    }
    
    public LearnSet(String _newTitle, String _newDescription, Faculty _newFaculty, CardList _newCardList, Account _newOwner, Visibility _visibility)
    {
        title = _newTitle;
        description = _newDescription;
        faculty = _newFaculty;
        cardList = _newCardList;
        commentList = new ArrayList<>();
        owner = _newOwner;
        visibility = _visibility;
        adminList = new ArrayList<>();

    }
    // METHODS
    public void createCardList()
    {
        this.cardList = new CardList();
    }
    
    
    /*
    * Admin
    *
    * */
    public void addAdmin(Account _newAdmin)
    {
        if(!adminList.contains(_newAdmin))
        {
            this.adminList.add(_newAdmin);
        }
        else
        {
            logger.warning("New Admin is already part of adminList");
        }
    }
    
    /**
     * Removes Admin from adminList by Index
     * is overloaded
     *
     * @param _indexToRemove
     */
    public void removeAdmin(int _indexToRemove)
    {
        if(_indexToRemove <= adminList.size() && 0 <= _indexToRemove)
        {
            this.adminList.remove(_indexToRemove);
        }
        else
        {
            logger.warning("indexToRemvoe is out of bounce");
        }
    }
    
    /**
     * Removes Admin from adminList by Account
     * is overloaded
     *
     * @param _accountToRemove
     */
    public void removeAdmin(Account _accountToRemove)
    {
        if(adminList.contains(_accountToRemove))
        {
            this.adminList.remove(_accountToRemove);
        }
        else
        {
            logger.warning("accountToRemove is not part of adminList");
        }
    }
    
    /**
    * Comments are used as comments
    *
    * */
    public void addComment(Comment _newComment)
    {
        this.commentList.add(_newComment);
    }
    
    public int calculateEvaluation()
    {
        if(this.commentList.size() == 0){
            return -1;
        }
        int numberOfYes = 0;
        for(Comment comment : this.commentList){
            if(comment.getRecommended().equals(Recommended.YES)){
                numberOfYes++;
            }
        }
    
        return (int) 100.0 / this.commentList.size() * numberOfYes;
    }

    /**
     * checks if given account can see the learnSet
     * @param _account
     * @return
     */
    public boolean isAuthorizedToAccessLearnSet(Account account)
    {
        switch (this.visibility)
        {
            case PUBLIC:
                return true;

            case PRIVATE:
                if (this.getOwner()== account)
                {
                    return true;
                }
                break;

            case PROTECTED:
                // TODO: wenn Freunde implementiert sind, sollte hier auch auf die Adminliste geprüft werden
                if (this.getOwner().getFriends().contains( account) || this.getOwner()== account
                    || this.getFaculty().equals(account.getFaculty()))
                {
                    return true;
                }
                break;

            }
        return false;
    }
    
    public boolean isAdmin(Account account){
        return this.getAdminList().contains(account);
    }
    
    public boolean isOwner(Account account){
        return this.getOwner().equals(account);
    }
    
}

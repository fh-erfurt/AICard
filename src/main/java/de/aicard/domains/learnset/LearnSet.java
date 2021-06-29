package de.aicard.domains.learnset;

import de.aicard.domains.Social.Message;
import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.Faculty;
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
    private List<Message> commentList;
    
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
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Account> adminList;
    
    /**
     * Average evaluation of a LearnSet
     */
    private double evaluations;
    private int numberOfEvaluations;

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
        evaluations = 0;
        numberOfEvaluations = 0;
    }
    
    // GETTER + SETTER
//    public String getTitle() throws NullPointerException
//    {
//        if(title == null)
//        {
//            throw new NullPointerException("LearnSet Title was not set.");
//        }
//        return this.title;
//    }
//
//    public String getDescription() throws NullPointerException
//    {
//        if(description == null)
//        {
//            throw new NullPointerException("LearnSet Description was not set.");
//        }
//        return this.description;
//    }
//
//    public Faculty getFaculty() throws NullPointerException
//    {
//        if (faculty == null)
//        {
//            throw new NullPointerException("LearnSet Faculty was not set.");
//        }
//        return this.faculty;
//    }
//
//    public CardList getCardList() throws NullPointerException
//    {
//        if(cardList == null)
//        {
//            throw new NullPointerException("LearnSet CardList was not set.");
//        }
//        return this.cardList;
//    }
//
//    public List<Message> getCommentList() throws NullPointerException
//    {
//        if(commentList == null)
//        {
//            throw new NullPointerException("LearnSet CommentList was not set.");
//        }
//        return this.commentList;
//    }
//
//    public Account getOwner() throws NullPointerException
//    {
//        if(owner == null)
//        {
//            throw new NullPointerException("LearnSet Owner was not set.");
//        }
//        return this.owner;
//    }
//
//    public Visibility getVisibility() throws NullPointerException
//    {
//        if(visibility == null)
//        {
//            throw new NullPointerException("LearnSet Visibility was not set.");
//        }
//        return this.visibility;
//    }
//
//    public List<Account> getAdmins() throws NullPointerException
//    {
//        if(adminList == null)
//        {
//            throw new NullPointerException("LearnSet AdminList was not set.");
//        }
//        return this.adminList;
//    }
//
    // METHODS
    
    public void createCardList()
    {
        this.cardList = new CardList();
    }
    
    
    /*
     * Evaluation
     * */
    
    /**Add a new Evaluation and calculates the new average
     *
     * @param _newEvaluation int value that in used to calculate new average Evaluation
     */
    public void addEvaluation(int _newEvaluation)
    {
        if(getNumberOfEvaluations() == 0)
        {
            setEvaluations(_newEvaluation);
            increaseNumberOfEvaluations();
        }
        else
        {
            double updatedEvaluation = getEvaluations() * getNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation + _newEvaluation;
            increaseNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation / getNumberOfEvaluations();
            setEvaluations(updatedEvaluation);
        }
    }

    /**
     * Deletes an existing evaluation and calculates the new average.
     *
     * @param _evaluationToDelete
     */
    public void deleteEvaluation(int _evaluationToDelete)
    {
        if(this.numberOfEvaluations > 0)
        {
            try
            {
                double updatedEvaluation = this.evaluations * this.numberOfEvaluations;
                updatedEvaluation = updatedEvaluation - _evaluationToDelete;
                decreaseNumberOfEvaluations();

                if(this.numberOfEvaluations != 0)
                {
                    updatedEvaluation = updatedEvaluation / this.numberOfEvaluations;
                }

                this.evaluations = updatedEvaluation;
            }
            catch (Exception e)
            {
                logger.warning(e.getMessage());
            }
        }
    }
    
    public void increaseNumberOfEvaluations()
    {
        setNumberOfEvaluations(this.numberOfEvaluations + 1);
    }
    
    public void decreaseNumberOfEvaluations() throws Exception
    {
        if(this.numberOfEvaluations <=  0)
        {
            // this should never be reached
            throw new Exception("Can't decrease Number of Evaluations because lower or equal 0.");
        }
    
        this.numberOfEvaluations = this.numberOfEvaluations - 1;
    
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
    
    /*
    * Messages
    *
    * */
    public void addMessage(Message _newMessage)
    {
        this.commentList.add(_newMessage);
    }
    
    public void removeMessageByMessage(Message _messageToRemove)
    {
        if(commentList.contains(_messageToRemove))
        {
            this.commentList.remove(_messageToRemove);
        }
        else
        {
            logger.warning("messageToRemove is not part of commentList");
        }
    }

    public boolean isAuthorizedToAddLearnSet(Account _account)
    {
        switch (this.visibility)
        {
            case PUBLIC:
                return true;

            case PRIVATE:
                if (this.getOwner()==_account)
                {
                    return true;
                }
                break;

            case PROTECTED:
                if (this.getOwner().getFriends().contains(_account) || this.getOwner()==_account)
                {
                    return true;
                }
                break;

            }
        return false;
    }
    
}

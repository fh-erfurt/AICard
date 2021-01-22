package de.aicard.learnset;


import de.aicard.Social.Message;
import de.aicard.enums.Faculty;
import de.aicard.enums.Visibility;
import de.aicard.account.Account;
import de.aicard.Social.MessageList;
import java.util.ArrayList;
import java.util.logging.Logger;


/**
 * Provides CardList with additional information:
 * Title, Description, Faculty(to which Accounts from which Faculties it will be shown)
 * CommentList, Evaluations(only 1 per Account->LearnSetAbo is possible)
 * Owner(Account who created the LearnSet), Admins(Accounts who can edit the LearnSet)
 * Visibility(to show the LearnSet to all Account, to Faculty and Friends or only to Owner)
 *
 * @author: Martin Kühlborn
 */
public class LearnSet
{
    // CLASS VARIABLES
    private static final Logger logger = Logger.getLogger(LearnSet.class.getName());
 
    // MEMBER VARIABLES
    private String title;
    private String description;
    private Faculty faculty;
    private CardList cardList;
    private MessageList commentList;
    private Account owner;
    private Visibility visibility;
    private ArrayList<Account> adminList;
    private double evaluations;
    private int numberOfEvaluations;

    //ToDo Visibility prüfen, bevor LearnSetAbo erstellt wird
        // why? visibility interresiert doch nur beim anzeigen und nicht beim anlegen
    //ToDo Funktion, dass maximal 200 Karten angelegt werden können
        // Done at CardList->addToList()
    //ToDo Wo wird LearnSet Status geändert?
        // LearnSet hat keinen Status, sondern LearnSetAbo?

    // CONSTRUCTORS
    public LearnSet()
    {
        this(null, null, null, new CardList(), null);
    }
    
    public LearnSet(CardList _newCardList)
    {
        this(null, null, null, _newCardList, null);
    }
    
    public LearnSet(String _newTitle, Faculty _newFaculty, CardList _newCardList, Account _newOwner)
    {
        this(_newTitle, null, _newFaculty, _newCardList, _newOwner);
    }
    
    public LearnSet(String _newTitle, String _newDescription, Faculty _newFaculty)
    {
        this(_newTitle, _newDescription, _newFaculty, new CardList(), null);
    }
    
    public LearnSet(String _newTitle, String _newDescription, Faculty _newFaculty, CardList _newCardList, Account _newOwner)
    {
        title = _newTitle;
        description = _newDescription;
        faculty = _newFaculty;
        cardList = _newCardList;
        commentList = new MessageList();
        owner = _newOwner;
        visibility = Visibility.PRIVATE;
        adminList = new ArrayList<Account>();
        evaluations = 0;
        numberOfEvaluations = 0;
    }
    
    // GETTER + SETTER
    public String getTitle() throws NullPointerException
    {
        if(title == null)
        {
            throw new NullPointerException("LearnSet Title was not set.");
        }
        return this.title;
    }
    
    public void setTitle(String _newTitle)
    {
        this.title = _newTitle;
    }
    
    public String getDescription() throws NullPointerException
    {
        if(description == null)
        {
            throw new NullPointerException("LearnSet Description was not set.");
        }
        return this.description;
    }
    
    public void setDescription(String _newDescription)
    {
        this.description = _newDescription;
    }
    
    public Faculty getFaculty() throws NullPointerException
    {
        if(faculty == null)
        {
            throw new NullPointerException("LearnSet Faculty was not set.");
        }
        return this.faculty;
    }
    
    public void setFaculty(Faculty _newFaculty)
    {
        this.faculty = _newFaculty;
    }
    
    public CardList getCardList() throws NullPointerException
    {
        if(cardList == null)
        {
            throw new NullPointerException("LearnSet CardList was not set.");
        }
        return this.cardList;
    }
    
    public void setCardList(CardList _newCardList)
    {
        this.cardList = _newCardList;
    }
    
    public MessageList getCommentList() throws NullPointerException
    {
        if(commentList == null)
        {
            throw new NullPointerException("LearnSet CommentList was not set.");
        }
        return this.commentList;
    }
    
    public void setCommentList(MessageList _newCommentList)
    {
        this.commentList = _newCommentList;
    }
    
    public Account getOwner() throws NullPointerException
    {
        if(owner == null)
        {
            throw new NullPointerException("LearnSet Owner was not set.");
        }
        return this.owner;
    }
    
    public void setOwner(Account _newOwner)
    {
        this.owner = _newOwner;
    }
    
    public Visibility getVisibility() throws NullPointerException
    {
        if(visibility == null)
        {
            throw new NullPointerException("LearnSet Visibility was not set.");
        }
        return this.visibility;
    }
    
    public void setVisibility(Visibility _newVisibility)
    {
        this.visibility = _newVisibility;
    }
    
    public ArrayList<Account> getAdmins() throws NullPointerException
    {
        if(adminList == null)
        {
            throw new NullPointerException("LearnSet AdminList was not set.");
        }
        return this.adminList;
    }
    
    public void setAdmins(ArrayList<Account> _newAdmins)
    {
        this.adminList = _newAdmins;
    }
    
    public double getEvaluation()
    {
        return this.evaluations;
    }
    
    public void setEvaluation(double _newEvaluation)
    {
        this.evaluations = _newEvaluation;
    }
    
    public int getNumberOfEvaluations()
    {
        return this.numberOfEvaluations;
    }
    
    public void setNumberOfEvaluations(int _newNumberOfEvaluations)
    {
        this.numberOfEvaluations = _newNumberOfEvaluations;
    }
    
 
    
    // METHODS
    
    public void createCardList()
    {
        this.cardList = new CardList();
    }
    
    
    /*
     * Evaluation
     * */
    
    /**Add a new Evaluation and calculates the new average
     * @param _newEvaluation int value that in used to calculate new average Evaluation
     */
    public void addEvaluation(int _newEvaluation)
    {
        if(getNumberOfEvaluations() == 0)
        {
            setEvaluation(_newEvaluation);
            increaseNumberOfEvaluations();
        }
        else
        {
            double updatedEvaluation = getEvaluation() * getNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation + _newEvaluation;
            increaseNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation / getNumberOfEvaluations();
            setEvaluation(updatedEvaluation);
        }
    }
    
    public void deleteEvaluation(int _EvaluationToDelete)
    {
        if(getNumberOfEvaluations() > 0)
        {
            try
            {
                double updatedEvaluation = getEvaluation() * getNumberOfEvaluations();
                updatedEvaluation = updatedEvaluation - _EvaluationToDelete;
                decreaseNumberOfEvaluations();
                updatedEvaluation = updatedEvaluation / getNumberOfEvaluations();
                setEvaluation(updatedEvaluation);
            }
            catch (Exception e)
            {
                logger.warning(e.getMessage());
            }
        }
    }
    
    public void increaseNumberOfEvaluations()
    {
        setNumberOfEvaluations(getNumberOfEvaluations() + 1);
    }
    
    public void decreaseNumberOfEvaluations() throws Exception
    {
        if(getNumberOfEvaluations() <=  0)
        {
            throw new Exception("Can't decrease Number of Evaluations because lower or equal 0.");
        }
    
        setNumberOfEvaluations(getNumberOfEvaluations() - 1);
    
    }
    
    
    /*
    * Admin
    *
    * */
    public void addAdmin(Account _newAdmin)
    {
        this.adminList.add(_newAdmin);
    }
    
    /**
     * Removes Admin from adminList by Index
     * is overloaded
     *
     * @param _IndexToRemove
     */
    public void removeAdmin(int _IndexToRemove)
    {
        this.adminList.remove(_IndexToRemove);
    }
    
    /**
     * Removes Admin from adminList by Account
     * is overloaded
     *
     * @param _AccountToRemove
     */
    public void removeAdmin(Account _AccountToRemove)
    {
        this.adminList.remove(_AccountToRemove);
    }
    
    /*
    * Messages
    *
    * */
    public void addMessage(Message _newMessage)
    {
        this.commentList.addMessage(_newMessage);
    }
    
    public void removeMessageByMessage(Message _MessageToRemove)
    {
        this.commentList.removeMessage(_MessageToRemove);
    }
    
}

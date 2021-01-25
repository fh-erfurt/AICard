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
    //ToDo brauchen wir wirklich so viele Construktoren?
    //  -> NEIN!!1! aber ich habe die Construktoren so erstellt, wie die Test der anderen Klassen ware
    //  => Sinnvoll sind nur Konstruktoren, bei denen mindestens Faculty und Owner übergeben wird (vielleicht auch title)
    public LearnSet()
    {
        this(null, null, null, new CardList(), null);
    }
    
    public LearnSet(CardList newCardList)
    {
        this(null, null, null, newCardList, null);
    }
    
    public LearnSet(String newTitle, Faculty newFaculty, CardList newCardList, Account newOwner)
    {
        this(newTitle, null, newFaculty, newCardList, newOwner);
    }
    
    public LearnSet(String newTitle, String newDescription, Faculty newFaculty)
    {
        this(newTitle, newDescription, newFaculty, new CardList(), null);
    }
    
    public LearnSet(String newTitle, String newDescription, Faculty newFaculty, CardList newCardList, Account newOwner)
    {
        title = newTitle;
        description = newDescription;
        faculty = newFaculty;
        cardList = newCardList;
        commentList = new MessageList();
        owner = newOwner;
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
    
    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }
    
    public String getDescription() throws NullPointerException
    {
        if(description == null)
        {
            throw new NullPointerException("LearnSet Description was not set.");
        }
        return this.description;
    }
    
    public void setDescription(String newDescription)
    {
        this.description = newDescription;
    }
    
    public Faculty getFaculty() throws NullPointerException
    {
        if(faculty == null)
        {
            throw new NullPointerException("LearnSet Faculty was not set.");
        }
        return this.faculty;
    }
    
    public void setFaculty(Faculty newFaculty)
    {
        this.faculty = newFaculty;
    }
    
    public CardList getCardList() throws NullPointerException
    {
        if(cardList == null)
        {
            throw new NullPointerException("LearnSet CardList was not set.");
        }
        return this.cardList;
    }
    
    public void setCardList(CardList newCardList)
    {
        this.cardList = newCardList;
    }
    
    public MessageList getCommentList() throws NullPointerException
    {
        if(commentList == null)
        {
            throw new NullPointerException("LearnSet CommentList was not set.");
        }
        return this.commentList;
    }
    
    public void setCommentList(MessageList newCommentList)
    {
        this.commentList = newCommentList;
    }
    
    public Account getOwner() throws NullPointerException
    {
        if(owner == null)
        {
            throw new NullPointerException("LearnSet Owner was not set.");
        }
        return this.owner;
    }
    
    public void setOwner(Account newOwner)
    {
        this.owner = newOwner;
    }
    
    public Visibility getVisibility() throws NullPointerException
    {
        if(visibility == null)
        {
            throw new NullPointerException("LearnSet Visibility was not set.");
        }
        return this.visibility;
    }
    
    public void setVisibility(Visibility newVisibility)
    {
        this.visibility = newVisibility;
    }
    
    public ArrayList<Account> getAdmins() throws NullPointerException
    {
        if(adminList == null)
        {
            throw new NullPointerException("LearnSet AdminList was not set.");
        }
        return this.adminList;
    }
    
    public void setAdmins(ArrayList<Account> newAdmins)
    {
        this.adminList = newAdmins;
    }
    
    public double getEvaluation()
    {
        return this.evaluations;
    }
    
    public void setEvaluation(double newEvaluation)
    {
        this.evaluations = newEvaluation;
    }
    
    public int getNumberOfEvaluations()
    {
        return this.numberOfEvaluations;
    }
    
    public void setNumberOfEvaluations(int newNumberOfEvaluations)
    {
        this.numberOfEvaluations = newNumberOfEvaluations;
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
     *
     * @param newEvaluation int value that in used to calculate new average Evaluation
     */
    public void addEvaluation(int newEvaluation)
    {
        if(getNumberOfEvaluations() == 0)
        {
            setEvaluation(newEvaluation);
            increaseNumberOfEvaluations();
        }
        else
        {
            double updatedEvaluation = getEvaluation() * getNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation + newEvaluation;
            increaseNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation / getNumberOfEvaluations();
            setEvaluation(updatedEvaluation);
        }
    }

    /**
     * Deletes an existing evaluation and calculates the new average.
     *
     * @param evaluationToDelete
     */
    public void deleteEvaluation(int evaluationToDelete)
    {
        if(this.numberOfEvaluations > 0)
        {
            try
            {
                double updatedEvaluation = this.evaluations * this.numberOfEvaluations;
                updatedEvaluation = updatedEvaluation - evaluationToDelete;
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
    public void addAdmin(Account newAdmin)
    {
        if(!adminList.contains(newAdmin))
        {
            this.adminList.add(newAdmin);
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
     * @param indexToRemove
     */
    public void removeAdmin(int indexToRemove)
    {
        if(indexToRemove <= adminList.size() && 0 <= indexToRemove)
        {
            this.adminList.remove(indexToRemove);
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
     * @param accountToRemove
     */
    public void removeAdmin(Account accountToRemove)
    {
        if(adminList.contains(accountToRemove))
        {
            this.adminList.remove(accountToRemove);
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
    public void addMessage(Message newMessage)
    {
        this.commentList.addMessage(newMessage);
    }
    
    public void removeMessageByMessage(Message messageToRemove)
    {
        if(commentList.getMessageList().contains(messageToRemove))
        {
            this.commentList.removeMessage(messageToRemove);
        }
        else
        {
            logger.warning("messageToRemove is not part of commentList");
        }
    }
    
}

package de.aicard.learnset;


import de.aicard.Social.Message;
import de.aicard.enums.Faculty;
import de.aicard.enums.Visibility;
import de.aicard.account.Account;
import de.aicard.learnset.CardList;
import de.aicard.Social.MessageList;
import java.util.ArrayList;

/*
* LearnSet in LearnSet Package for adjustment to UML-Class Diagramm and Package Structure
* with new member variables
* */

public class LearnSet
{
    private String m_Title;
    private String m_Description;
    private Faculty m_Faculty;
    private CardList m_CardList;
    private MessageList m_CommentList;
    private Account m_Owner;
    private Visibility m_Visibility;
    private ArrayList<Account> m_Admins;
    private double m_Evaluation;
    private int m_NumberOfEvaluations;
    
    // Constructor
    public LearnSet()
    {
        this(null, null, null, null, null);
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
        this(_newTitle, _newDescription, _newFaculty, null, null);
    }
    
    public LearnSet(String _newTitle, String _newDescription, Faculty _newFaculty, CardList _newCardList, Account _newOwner)
    {
        m_Title = _newTitle;
        m_Description = _newDescription;
        m_Faculty = _newFaculty;
        m_CardList = _newCardList;
        m_CommentList = new MessageList();
        m_Owner = _newOwner;
        m_Visibility = Visibility.PRIVATE;
        m_Admins = new ArrayList<Account>();
        m_Evaluation = 0;
        m_NumberOfEvaluations = 0;
    }
    
    // Getter + Setter
    public String getTitle() throws NullPointerException
    {
        if(m_Title == null)
        {
            throw new NullPointerException("LearnSet Title was not set.");
        }
        return this.m_Title;
    }
    
    public void setTitle(String _newTitle)
    {
        this.m_Title = _newTitle;
    }
    
    public String getDescription() throws NullPointerException
    {
        if(m_Description == null)
        {
            throw new NullPointerException("LearnSet Description was not set.");
        }
        return this.m_Description;
    }
    
    public void setDescription(String _newDescription)
    {
        this.m_Description = _newDescription;
    }
    
    public Faculty getFaculty() throws NullPointerException
    {
        if(m_Faculty == null)
        {
            throw new NullPointerException("LearnSet Faculty was not set.");
        }
        return this.m_Faculty;
    }
    
    public void setFaculty(Faculty _newFaculty)
    {
        this.m_Faculty = _newFaculty;
    }
    
    public CardList getCardList() throws NullPointerException
    {
        if(m_CardList == null)
        {
            throw new NullPointerException("LearnSet CardList was not set.");
        }
        return this.m_CardList;
    }
    
    public void setCardList(CardList _newCardList)
    {
        this.m_CardList = _newCardList;
    }
    
    public MessageList getCommentList() throws NullPointerException
    {
        if(m_CommentList == null)
        {
            throw new NullPointerException("LearnSet CommentList was not set.");
        }
        return this.m_CommentList;
    }
    
    public void setCommentList(MessageList _newCommentList)
    {
        this.m_CommentList = _newCommentList;
    }
    
    public Account getOwner() throws NullPointerException
    {
        if(m_Owner == null)
        {
            throw new NullPointerException("LearnSet Owner was not set.");
        }
        return this.m_Owner;
    }
    
    public void setOwner(Account _newOwner)
    {
        this.m_Owner = _newOwner;
    }
    
    public Visibility getVisibility() throws NullPointerException
    {
        if(m_Visibility == null)
        {
            throw new NullPointerException("LearnSet Visibility was not set.");
        }
        return this.m_Visibility;
    }
    
    public void setVisibility(Visibility _newVisibility)
    {
        this.m_Visibility = _newVisibility;
    }
    
    public ArrayList<Account> getAdmins() throws NullPointerException
    {
        if(m_Admins == null)
        {
            throw new NullPointerException("LearnSet AdminList was not set.");
        }
        return this.m_Admins;
    }
    
    public void setAdmins(ArrayList<Account> _newAdmins)
    {
        this.m_Admins = _newAdmins;
    }
    
    public double getEvaluation()
    {
        return this.m_Evaluation;
    }
    
    public void setEvaluation(double _newEvaluation)
    {
        this.m_Evaluation = _newEvaluation;
    }
    
    public int getNumberOfEvaluations()
    {
        return this.m_NumberOfEvaluations;
    }
    
    public void setNumberOfEvaluations(int _newNumberOfEvaluations)
    {
        this.m_NumberOfEvaluations = _newNumberOfEvaluations;
    }
    
 
    
    /**
     * Methods
     *
     *
     * */
    
    public void createCardList()
    {
        this.m_CardList = new CardList();
        // TODO: Do Something here
    }
    
    /*
    * Evaluation
    * */
    public void addEvaluation(double /* oder int? */ _newEvaluation)
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
    
    public void deleteEvaluation(double _EvaluationToDelete)
    {
        if(getNumberOfEvaluations() > 0)
        {
            double updatedEvaluation = getEvaluation() * getNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation - _EvaluationToDelete;
            //TODO: Catch Exception or Logger error
            decreaseNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation / getNumberOfEvaluations();
            setEvaluation(updatedEvaluation);
        }
    }
    
    public void increaseNumberOfEvaluations()
    {
        setNumberOfEvaluations(getNumberOfEvaluations() + 1);
    }
    
    public void decreaseNumberOfEvaluations()
    {
        if(getNumberOfEvaluations() > 0)
        {
            setNumberOfEvaluations(getNumberOfEvaluations() - 1);
        }
        //TODO
        // else: Error -> throw exception or do something with Logger
    }
    
    
    /*
    * Admin
    *
    * */
    public void addAdmin(Account _newAdmin)
    {
        this.m_Admins.add(_newAdmin);
    }
    
    public void removeAdminByIndex(int _IndexToRemove)
    {
        this.m_Admins.remove(_IndexToRemove);
    }
    
    public void removeAdminByAccount(Account _AccountToRemove)
    {
        this.m_Admins.remove(_AccountToRemove);
    }
    
    /*
    * Messages
    *
    * */
    public void addMessage(Message _newMessage)
    {
        this.m_CommentList.addMessage(_newMessage);
    }
    
    public void removeMessageByMessage(Message _MessageToRemove)
    {
        this.m_CommentList.removeMessage(_MessageToRemove);
    }
    
    
   
}

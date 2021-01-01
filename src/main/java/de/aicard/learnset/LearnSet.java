package de.aicard.learnset;


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
        m_Title = null;
        m_Description = null;
        m_Faculty = null;
        m_CardList = null;
        m_CommentList = null;
        m_Owner = null;
        m_Visibility = Visibility.PRIVATE;
        m_Admins = null; // GET_CALLED_CLASS wie in PHP?
        m_Evaluation = 0;
        m_NumberOfEvaluations = 0;
    }
    
    // Getter + Setter
    public String getTitle()
    {
        return this.m_Title;
    }
    
    public void setTitle(String _newTitle)
    {
        this.m_Title = _newTitle;
    }
    
    public String getDescription()
    {
        return this.m_Description;
    }
    
    public void setDescription(String _newDescription)
    {
        this.m_Description = _newDescription;
    }
    
    public Faculty getFaculty()
    {
        return this.m_Faculty;
    }
    
    public void setFaculty(Faculty _newFaculty)
    {
        this.m_Faculty = _newFaculty;
    }
    
    public CardList getCardList()
    {
        return this.m_CardList;
    }
    
    public void setCardList(CardList _newCardList)
    {
        this.m_CardList = _newCardList;
    }
    
    public MessageList getCommentList()
    {
        return this.m_CommentList;
    }
    
    public void setCommentList(MessageList _newCommentList)
    {
        this.m_CommentList = _newCommentList;
    }
    
    public Account getOwner()
    {
        return this.m_Owner;
    }
    
    public void setOwner(Account _newOwner)
    {
        this.m_Owner = _newOwner;
    }
    
    public Visibility getVisibility()
    {
        return this.m_Visibility;
    }
    
    public void setVisibility(Visibility _newVisibility)
    {
        this.m_Visibility = _newVisibility;
    }
    
    public ArrayList<Account> getAdmins()
    {
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
    
    // Vielleicht private und nur über addEvaluation() Methode bearbeiten
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
    
    
    // Methods
    public void createCardList()
    {
        m_CardList = new CardList();
        // TODO: Do Something here
    }
    
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
        
        
        /*
        oder:
        double updatedEvaluation = (m_Evaluation * m_NumberOfEvaluations) + _newEvaluation;
        m_NumberOfEvaluations++;
        updatedEvaluation = updatedEvaluation / m_NumberOfEvaluations;
        m_Evaluation = updatedEvaluation;
        */
    }
    
    public void deleteEvaluation(double _EvaluationToDelete)
    {
        if(getNumberOfEvaluations() > 0)
        {
            double updatedEvaluation = getEvaluation() * getNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation - _EvaluationToDelete;
            decreaseNumberOfEvaluations();
            updatedEvaluation = updatedEvaluation / getNumberOfEvaluations();
            m_Evaluation = updatedEvaluation;
        }
       
    }
    
    public void addAdmin(Account _newAdmin)
    {
        m_Admins.add(_newAdmin);
    }
    
    public void removeAdminByIndex(int _IndexToRemove)
    {
        m_Admins.remove(_IndexToRemove);
    }
    
    public void removeAdminByAccount(Account _AccountToRemove)
    {
        m_Admins.remove(_AccountToRemove);
    }
    
    // small HelperMethods
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
        // else: Error
    }
}

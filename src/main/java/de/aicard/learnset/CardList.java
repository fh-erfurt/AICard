package de.aicard.learnset;

import de.aicard.card.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CardList
{
    // MEMBER VARIABLES
    private ArrayList<Card> m_CardList;
    private int m_ListIndex;

    
    // CONSTRUCTORS
    public CardList()
    {
        this(null);
    }

    public CardList(ArrayList<Card> _newCardList)
    {
        this.m_CardList = _newCardList;
        this.m_ListIndex = 0;
    }
    
    // GETTER + SETTER
    public ArrayList<Card> getCardList() throws NullPointerException
    {
        if(this.m_CardList == null)
        {
            throw new NullPointerException("CardList was not set.");
        }
        
        return this.m_CardList;
    }
    
    public void setCardList(ArrayList<Card> _newCardList)
    {
        this.m_CardList = _newCardList;
    }
    
    public int getListIndex()
    {
        return m_ListIndex;
    }
    
    public void setListIndex(int _newListIndex)
    {
        this.m_ListIndex = _newListIndex;
    }
    
    
    // METHODS
    public void addToList(Card _newCard)
    {
        this.m_CardList.add(_newCard);
    }
    
    public void removeFromList(Card _Card)
    {
       this.m_CardList.remove(_Card);
    }

    public void removeFromList(int _Index)
    {
        this.m_CardList.remove(_Index);
    }

    public Card getCurrentCard() throws NullPointerException
    {
        if(this.m_CardList.get(m_ListIndex) == null)
        {
            throw new NullPointerException("No Card set on current ListIndex: " + m_ListIndex);
        }
        
        return this.m_CardList.get(m_ListIndex);
    }

    public Card getCardByIndex(int _Index) throws NullPointerException
    {
        if(this.m_CardList.get(_Index) == null)
        {
            throw new NullPointerException("No Card set on ListIndex: " + _Index);
        }
        
        return this.m_CardList.get(_Index);
    }
    
    

    public int getListLength()
    {
        return this.m_CardList.size();
    }

    public void next()
    {
        if (m_ListIndex < m_CardList.size() - 1)
        {
           m_ListIndex++;
        }
    }
    
    public void previous()
    {
        if (m_ListIndex > 0)
        {
            m_ListIndex--;
        }
    }

}

package de.aicard.learnset;

import de.aicard.card.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides CardList with Cards and a Pointer to the current ListIndex
 *
 * @author: Martin Kühlborn
 */
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
    
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param _Card
     */
    public void removeFromList(Card _Card)
    {
       //TODO test this. mucho mucho testos.
        this.m_CardList.remove(_Card);
    }
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param _Index
     */
    public void removeFromList(int _Index)
    {
        this.m_CardList.remove(_Index);
    }
    
    /**
     * Gets a Card via the current value of ListIndex pointer
     * @return Card from list
     * @throws NullPointerException
     */
    public Card getCurrentCard() throws NullPointerException
    {
        if(this.m_CardList.get(m_ListIndex) == null)
        {
            throw new NullPointerException("No Card set on current ListIndex: " + m_ListIndex);
        }
        
        return this.m_CardList.get(m_ListIndex);
    }
    
    /**Method to get a Card from the list by _Index
     *
     * @param _Index expects Index of the required Card in the list
     * @return Card from list
     * @throws NullPointerException
     * @throws Exception if _Index is too big or small to be a ListIndex
     */
    public Card getCardByIndex(int _Index) throws NullPointerException, Exception
    {
        if((_Index < this.m_CardList.size()) && (0 <= _Index))
        {
            if(this.m_CardList.get(_Index) == null)
            {
                throw new NullPointerException("No Card set on ListIndex: " + _Index);
            }
    
            return this.m_CardList.get(_Index);
        }
        
        throw new Exception("Index out of bounce.");
    }
    
    

    public int getListLength()
    {
        return this.m_CardList.size();
    }

    // METHODS
    
    /**
     * Sets ListIndex Pointer +1 if ListIndex not bigger than CardList length
     */
    public void next()
    {
        // LOGGER -> out of bounce
        if (m_ListIndex < m_CardList.size() - 1)
        {
           m_ListIndex++;
        }
    }
    
    /**
     * Sets ListIndex Pointer -1 if ListIndex not smaller than 0
     */
    public void previous()
    {
        // LOGGER -> out of bounce
        if (m_ListIndex > 0)
        {
            m_ListIndex--;
        }
    }

}

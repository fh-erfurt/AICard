package de.aicard.learnset;

import de.aicard.card.Card;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Provides CardList with Cards and a Pointer to the current ListIndex
 *
 * @author: Martin Kühlborn
 */
public class CardList
{
    // CLASS VARIABLES
    private static final Logger logger = Logger.getLogger(CardList.class.getName());
    
    // MEMBER VARIABLES
    private ArrayList<Card> cardList;
    private int listIndex;

    
    // CONSTRUCTORS
    public CardList()
    {
        this(new ArrayList<Card>());
    }

    public CardList(ArrayList<Card> _newCardList)
    {
        this.cardList = _newCardList;
        this.listIndex = 0;
    }
    
    // GETTER + SETTER
    public ArrayList<Card> getCardList() throws NullPointerException
    {
        if(this.cardList == null)
        {
            throw new NullPointerException("CardList was not set.");
        }
        
        return this.cardList;
    }
    
    public void setCardList(ArrayList<Card> _newCardList)
    {
        this.cardList = _newCardList;
    }
    
    public int getListIndex()
    {
        return listIndex;
    }
    
    public void setListIndex(int _newListIndex)
    {
        this.listIndex = _newListIndex;
    }
    
    
    // METHODS
    public void addToList(Card _newCard)
    {
        if(getListLength() < 200)
        {
            this.cardList.add(_newCard);
        }
        else
        {
            logger.warning("Card not added to CardList, only 200 Card are allowed.");
        }
    }
    
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param _Card
     */
    public void removeFromList(Card _Card)
    {
        this.cardList.remove(_Card);
    }
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param _Index
     */
    public void removeFromList(int _Index)
    {
        this.cardList.remove(_Index);
    }
    
    /**
     * Gets a Card via the current value of ListIndex pointer
     * @return Card from list
     * @throws NullPointerException
     */
    public Card getCurrentCard() throws NullPointerException
    {
        if(this.cardList.get(listIndex) == null)
        {
            throw new NullPointerException("No Card set on current ListIndex: " + listIndex);
        }
        
        return this.cardList.get(listIndex);
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
        if((_Index < this.cardList.size()) && (0 <= _Index))
        {
            if(this.cardList.get(_Index) == null)
            {
                throw new NullPointerException("No Card set on ListIndex: " + _Index);
            }
    
            return this.cardList.get(_Index);
        }
        
        throw new Exception("Index out of bounce. Index: " + _Index);
    }
    
    

    public int getListLength()
    {
        return this.cardList.size();
    }

    // METHODS
    
    /**
     * Sets ListIndex Pointer +1 if ListIndex not bigger than CardList length
     */
    public void next()
    {
        if (this.listIndex < this.cardList.size() - 1)
        {
           this.listIndex++;
        }
        else
        {
            logger.warning("ListIndex out of bounce");
        }
        
    }
    
    /**
     * Sets ListIndex Pointer -1 if ListIndex not smaller than 0
     */
    public void previous()
    {
        if (this.listIndex > 0)
        {
            this.listIndex--;
        }
        else
        {
            logger.warning("ListIndex out of bounce.");
        }
    }

}

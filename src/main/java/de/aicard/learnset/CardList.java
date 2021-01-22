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
    /**
     * A list of Cards.
     */
    private ArrayList<Card> cardList;
    /**
     * The current listIndex.
     */
    private int listIndex;

    
    // CONSTRUCTORS

    /**
     * Constructs a CardList containing only an empty ArrayList.
     */
    public CardList()
    {
        this(new ArrayList<Card>());
    }

    /**
     * Constructor which constructs a CardList on the basis of an existing ArrayList of Cards.
     * @param newCardList
     */
    public CardList(ArrayList<Card> newCardList)
    {
        this.cardList = newCardList;
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
    
    public void setCardList(ArrayList<Card> newCardList)
    {
        this.cardList = newCardList;
    }
    
    public int getListIndex()
    {
        return listIndex;
    }
    
    public void setListIndex(int newListIndex)
    {
        this.listIndex = newListIndex;
    }
    
    
    // METHODS
    public void addToList(Card newCard)
    {
        if(getListLength() < 200)
        {
            this.cardList.add(newCard);
        }
        else
        {
            logger.warning("Card not added to CardList, only 200 Card are allowed.");
        }
    }
    
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param Card Card that should be removed
     */
    public void removeFromList(Card Card)
    {
        this.cardList.remove(Card);
    }
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param Index index of the Card that should be removed.
     */
    public void removeFromList(int Index)
    {
        this.cardList.remove(Index);
    }
    
    /**
     * Gets a Card via the current value of ListIndex pointer
     *
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
    
    /**Method to get a Card from the list by index
     *
     * @param index expects index of the required Card in the list
     * @return Card from list
     * @throws NullPointerException
     * @throws Exception if index is too big or small to be a ListIndex
     */
    public Card getCardByIndex(int index) throws NullPointerException, Exception
    {
        if((index < this.cardList.size()) && (0 <= index))
        {
            if(this.cardList.get(index) == null)
            {
                throw new NullPointerException("No Card set on ListIndex: " + index);
            }
    
            return this.cardList.get(index);
        }
        
        throw new Exception("index out of bounce. index: " + index);
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

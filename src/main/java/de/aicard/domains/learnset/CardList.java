package de.aicard.domains.learnset;

import de.aicard.domains.card.Card;
import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Provides CardList with Cards and a Pointer to the current ListIndex
 *
 * @author Martin Kuehlborn, Clemens Berger
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
public class CardList extends BaseEntity
{
    // CLASS VARIABLES
    private static final Logger logger = Logger.getLogger(CardList.class.getName());
    
    // MEMBER VARIABLES
    /**
     * A list of Cards.
     */
    @OneToMany(cascade = CascadeType.ALL)
    public List<Card> listOfCards;
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
     * @param _newCardList
     */
    public CardList(List<Card> _newCardList)
    {
        this.listOfCards = _newCardList;
        this.listIndex = 0;
    }
    
    // GETTER + SETTER

    public List<Card> getListOfCards() throws NullPointerException
    {
        if(this.listOfCards == null)
        {
            throw new NullPointerException("CardList was not set.");
        }
        
        return this.listOfCards;
    }
    
    
    // METHODS
    public void addToList(Card _newCard)
    {
        if(getListLength() < 200)
        {
            this.listOfCards.add(_newCard);
    }
        else
        {
            logger.warning("Card not added to CardList, only 200 Card are allowed.");
        }
    }
    
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param _card Card that should be removed
     */
    public void removeFromList(Card _card)
    {
        this.listOfCards.remove(_card);
    }
    
    /**Removes Card Object from list
     * is overloaded
     *
     * @param _index index of the Card that should be removed.
     */
    public void removeFromList(int _index)
    {
        this.listOfCards.remove(_index);
    }
    
    /**
     * Gets a Card via the current value of ListIndex pointer
     *
     * @return Card from list
     * @throws NullPointerException
     */
    public Card getCurrentCard() throws NullPointerException
    {
        if(this.listOfCards.get(listIndex) == null)
        {
            throw new NullPointerException("No Card set on current ListIndex: " + listIndex);
        }
        
        return this.listOfCards.get(listIndex);
    }
    
    /**Method to get a Card from the list by index
     *
     * @param _index expects index of the required Card in the list
     * @return Card from list
     * @throws NullPointerException
     * @throws Exception if index is too big or small to be a ListIndex
     */
    public Card getCardByIndex(int _index) throws NullPointerException, Exception
    {
        if((_index < this.listOfCards.size()) && (0 <= _index))
        {
            if(this.listOfCards.get(_index) == null)
            {
                throw new NullPointerException("No Card set on ListIndex: " + _index);
            }
    
            return this.listOfCards.get(_index);
        }
        
        throw new Exception("index out of bounce. index: " + _index);
    }
    
    

    public int getListLength()
    {
        return this.listOfCards.size();
    }

    // METHODS
    
    /**
     * Sets ListIndex Pointer +1 if ListIndex not bigger than CardList length
     */
    public void next()
    {
        if (this.listIndex < this.listOfCards.size() - 1)
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
    
    public void setCardPath(String filePath){
        List<Card> listOfCards = this.listOfCards;
        for ( Card card : listOfCards)
        {
            card.setCardPath(filePath);
        }
    }
}

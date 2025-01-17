package de.aicard.domains.learnset;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.card.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Provides CardList with Cards and a Pointer to the current ListIndex
 * this class is deprecated because listindex is not used
 *
 * @author Martin Kuehlborn, Clemens Berger
 */
@Getter
@Setter
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
     * <p>
     * deprecated
     * not supported in Java 2
     */
    private int listIndex;
    
    
    // CONSTRUCTORS
    
    /**
     * Constructs a CardList containing only an empty ArrayList.
     */
    public CardList()
    {
        this(new ArrayList<>());
    }
    
    /**
     * Constructor which constructs a CardList on the basis of an existing ArrayList of Cards.
     *
     * @param newCardList /
     */
    public CardList(List<Card> newCardList)
    {
        this.listOfCards = newCardList;
        this.listIndex = 0;
    }
    
    /**
     * adds a card to the listOfCards
     *
     * @param newCard /
     */
    public void addToList(Card newCard)
    {
        if (! this.listOfCards.contains(newCard))
        {
            this.listOfCards.add(newCard);
        }
    }
    
    
    /**
     * Removes Card Object from list
     * is overloaded
     *
     * @param card Card that should be removed
     */
    public void removeFromList(Card card)
    {
        this.listOfCards.remove(card);
    }
    
    /**
     * Removes Card Object from list
     * is overloaded
     * old method only used in tests
     *
     * @param index index of the Card that should be removed.
     */
    public void removeFromList(int index)
    {
        this.listOfCards.remove(index);
    }
    
    /**
     * Gets a Card via the current value of ListIndex pointer
     * old method only used in tests
     *
     * @return Card from list
     * @throws NullPointerException /
     */
    public Card getCurrentCard() throws NullPointerException
    {
        if (this.listOfCards.get(listIndex) == null)
        {
            throw new NullPointerException("No Card set on current ListIndex: " + listIndex);
        }
        
        return this.listOfCards.get(listIndex);
    }
    
    /**
     * Method to get a Card from the list by index
     *
     * @param index expects index of the required Card in the list
     * @return Card from list
     * @throws NullPointerException /
     * @throws Exception            if index is too big or small to be a ListIndex
     */
    public Card getCardByIndex(int index) throws NullPointerException, Exception
    {
        if ((index < this.listOfCards.size()) && (0 <= index))
        {
            if (this.listOfCards.get(index) == null)
            {
                throw new NullPointerException("No Card set on ListIndex: " + index);
            }
            
            return this.listOfCards.get(index);
        }
        
        throw new Exception("index out of bounce. index: " + index);
    }
    
    
    /**
     * size of listOfCards
     *
     * @return int
     */
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
    
    /**
     * sets the card path of all cards in listOfCards
     *
     * @param filePath /
     */
    public void setCardPath(String filePath)
    {
        List<Card> listOfCards = this.listOfCards;
        for (Card card : listOfCards)
        {
            card.setCardPath(filePath);
        }
    }
}

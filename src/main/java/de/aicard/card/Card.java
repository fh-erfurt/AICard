package de.aicard.card;

/**
 * Provides a 2 sided Card(front and back) for CardList and CardStatus
 * connects trough CardContent (Interface) to the different filetypes
 *
 * @author: Martin Kühlborn
 */
public class Card
{

    // MEMBER VARIABLES
    private CardContent cardFront;
    private CardContent cardBack;
    
    
    // CONSTRUCTORS
    //ToDo same question as in AudioFile: why not force user to directly put content into the card Object?

    public Card()
    {
        new Card(null, null);
    }
    
    public Card(CardContent newCardFront, CardContent newCardBack)
    {
        this.cardFront = newCardFront;
        this.cardBack  = newCardBack ;
    }
    
    
    // GETTER + SETTER
    public CardContent getCardFront() throws NullPointerException
    {
        if(this.cardFront == null)
        {
            throw new NullPointerException("CardFront not was not set.");
        }
        
        return this.cardFront;
    }
    
    public void setCardFront(CardContent newCardFront)
    {
        this.cardFront = newCardFront;
    }
    
    public CardContent getCardBack() throws NullPointerException
    {
        if(this.cardBack == null)
        {
            throw new NullPointerException("CardBack was not set.");
        }
        
        return this.cardBack;
    }
    
    public void setCardBack(CardContent newCardBack)
    {
        this.cardBack = newCardBack;
    }
}

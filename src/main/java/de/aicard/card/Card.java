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
    private CardContent m_CardFront;
    private CardContent m_CardBack;
    
    
    // CONSTRUCTORS
    public Card()
    {
        new Card(null, null);
    }
    
    public Card(CardContent _newCardFront, CardContent _newCardBack)
    {
        this.m_CardFront = _newCardFront;
        this.m_CardBack  = _newCardBack ;
    }
    
    
    // GETTER + SETTER
    public CardContent getCardFront() throws NullPointerException
    {
        if(this.m_CardFront == null)
        {
            throw new NullPointerException("CardFront not was not set.");
        }
        
        return this.m_CardFront;
    }
    
    public void setCardFront(CardContent _newCardFront)
    {
        this.m_CardFront = _newCardFront;
    }
    
    public CardContent getCardBack() throws NullPointerException
    {
        if(this.m_CardBack == null)
        {
            throw new NullPointerException("CardBack was not set.");
        }
        
        return this.m_CardBack;
    }
    
    public void setCardBack(CardContent _newCardBack)
    {
        this.m_CardBack = _newCardBack;
    }
}

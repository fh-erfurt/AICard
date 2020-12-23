package de.aicard.card;


public class Card
{
    private CardContent m_CardFront;
    private CardContent m_CardBack;
    
    public Card()
    {
        new Card(null, null);
    }
    
    public Card(CardContent _newCardFront, CardContent _newCardBack)
    {
        this.m_CardFront = _newCardFront;
        this.m_CardBack  = _newCardBack ;
    }
    
    
    public CardContent getCardFront()
    {
        return this.m_CardFront;
    }
    
    public void setCardFront(CardContent _newCardFront)
    {
        this.m_CardFront = _newCardFront;
    }
    
    public CardContent getCardBack()
    {
        return this.m_CardBack;
    }
    
    public void setCardBack(CardContent _newCardBack)
    {
        this.m_CardBack = _newCardBack;
    }
}

package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Provides a 2 sided Card(front and back) for CardList and CardStatus
 * connects trough CardContent (Interface) to the different filetypes
 *
 * @author Martin Kuehlborn
 */
@Entity
@NoArgsConstructor
public class Card extends BaseEntity
{

    // MEMBER VARIABLES
    @OneToOne
    private CardContent cardFront;
    @OneToOne
    private CardContent cardBack;
    
    
    // CONSTRUCTORS
    
    public Card(CardContent _newCardFront, CardContent _newCardBack)
    {
        this.cardFront = _newCardFront;
        this.cardBack  = _newCardBack ;
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
    
    public void setCardFront(CardContent _newCardFront)
    {
        this.cardFront = _newCardFront;
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

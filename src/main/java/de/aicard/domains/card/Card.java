package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Method;

/**
 * Provides a 2 sided Card(front and back) for CardList and CardStatus
 * @author Martin Kuehlborn
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Card extends BaseEntity
{

    // MEMBER VARIABLES
    @OneToOne(cascade = CascadeType.ALL)
    private CardContent cardFront;
    @OneToOne (cascade = CascadeType.ALL)
    private CardContent cardBack;
    
    
    // CONSTRUCTORS
    
    public Card(CardContent _newCardFront, CardContent _newCardBack)
    {
        
        this.cardFront = _newCardFront;
        this.cardBack  = _newCardBack;
    }

}

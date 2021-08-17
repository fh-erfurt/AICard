package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
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


    public void setCardPath(String filePath){
            if(this.getCardFront().getType() != DataType.TextFile) {
                this.getCardFront().setData(filePath + this.getCardFront().getData());
            }
            if(this.getCardBack().getType() != DataType.TextFile)
            {
                this.getCardBack().setData(filePath + this.getCardBack().getData());
            }
    }
    
    public void deleteCardContent(){
        if (this.getCardFront().getType() != DataType.TextFile)
        {
            File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + this.getCardFront().getData());
            file.delete();
        }
        if (this.getCardBack().getType() != DataType.TextFile)
        {
            File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + this.getCardBack().getData());
            file.delete();
        }
    }

}

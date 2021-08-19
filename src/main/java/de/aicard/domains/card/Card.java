package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.DataType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.File;

/**
 * Provides a 2 sided Card(front and back) for CardList and CardStatus
 *
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
    @OneToOne(cascade = CascadeType.ALL)
    private CardContent cardBack;
    
    // CONSTRUCTORS
    
    /**
     * card constructor
     *
     * @param newCardFront /
     * @param newCardBack  /
     */
    public Card(CardContent newCardFront, CardContent newCardBack)
    {
        this.cardFront = newCardFront;
        this.cardBack = newCardBack;
    }
    
    /**
     * sets cardcontent data from each card side to filePath + original cardcontent data
     * used for fronted call to display files
     *
     * @param filePath path to file directory
     */
    public void setCardPath(String filePath)
    {
        if (this.getCardFront().getType() != DataType.TextFile)
        {
            this.getCardFront().setData(filePath + this.getCardFront().getData());
        }
        if (this.getCardBack().getType() != DataType.TextFile)
        {
            this.getCardBack().setData(filePath + this.getCardBack().getData());
        }
    }
    
    /**
     * deletes cardcontent media files from filesystem
     */
    public void deleteCardContent()
    {
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

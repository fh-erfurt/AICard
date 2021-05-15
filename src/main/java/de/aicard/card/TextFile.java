package de.aicard.card;

import de.aicard.db.domains.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Provides an TextFile as String for side of a card
 *
 * @author Martin Kuehlborn
 */
@Entity
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class TextFile extends BaseEntity implements CardContent
{
    // MEMBER VARIABLES
    private String testData;


    // CONSTRUCTORS
    public TextFile()
    {
        this(null);
    }
    
    public TextFile(String _newTextData)
    {
        this.testData = _newTextData;
    }
    
    // GETTER + SETTER
    public String getTextData() throws NullPointerException
    {
        if(this.testData == null)
        {
            throw new NullPointerException("TextData was not set.");
        }
        
        return this.testData;
    }
    
    public void setTextData(String _newTextData)
    {
        this.testData = _newTextData;
    }
}

package de.aicard.domains.card;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Provides an TextFile as String for side of a card
 *
 * @author Martin Kuehlborn
 */
@Entity
public class TextFile extends CardContent
{
    // MEMBER VARIABLES
    @Column
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

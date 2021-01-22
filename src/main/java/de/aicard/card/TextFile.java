package de.aicard.card;

/**
 * Provides an TextFile as String for side of a card
 *
 * @author: Martin Kühlborn
 */
public class TextFile implements CardContent
{
    // MEMBER VARIABLES
    private String testData;


    // CONSTRUCTORS
    public TextFile()
    {
        this(null);
    }
    
    public TextFile(String newTextData)
    {
        this.testData = newTextData;
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
    
    public void setTextData(String newTextData)
    {
        this.testData = newTextData;
    }
}

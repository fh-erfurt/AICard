package de.aicard.card;

/**
 * Provides an TextFile as String for side of a card
 *
 * @author: Martin Kühlborn
 */
public class TextFile implements CardContent
{
    // MEMBER VARIABLES
    private String m_TextData;


    // CONSTRUCTORS
    public TextFile()
    {
        this(null);
    }
    
    public TextFile(String _newTextData)
    {
        this.m_TextData = _newTextData;
    }
    
    // GETTER + SETTER
    public String getTextData() throws NullPointerException
    {
        if(this.m_TextData == null)
        {
            throw new NullPointerException("TextData was not set.");
        }
        
        return this.m_TextData;
    }
    
    public void setTextData(String _newTextData)
    {
        this.m_TextData = _newTextData;
    }
}

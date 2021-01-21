package de.aicard.card;

/**
 * Provides an PictureFile for side of a card
 * Currently using placeholder Strings
 * Will be real Picture in Java 2
 *
 * @author: Martin Kühlborn
 */
public class PictureFile implements CardContent
{

    // MEMBER VARIABLES
    private String m_PictureData;
    private String m_Title;
    
    
    // CONSTRUCTORS
    public PictureFile()
    {
        this(null);
    }
    
    public PictureFile(String _newPictureData)
    {
        this.m_PictureData = _newPictureData;
    }
    
    // GETTER + SETTER
    public String getPictureData() throws NullPointerException
    {
        if(this.m_PictureData == null)
        {
            throw new NullPointerException("PictureData was not set.");
        }
        
        return this.m_PictureData;
    }
    
    public void setPictureData(String _newPictureData)
    {
        this.m_PictureData = _newPictureData;
    }
    
    public String getTitle() throws NullPointerException
    {
        if(this.m_Title == null)
        {
            throw new NullPointerException("PictureTitle was not set.");
        }
        
        return this.m_Title;
    }
    
    public void setTitle(String _newTitle)
    {
        this.m_Title = _newTitle;
    }
}

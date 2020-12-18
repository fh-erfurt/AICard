package de.aicard.card;

public class PictureFile extends CardContent
{
    private String m_PictureData;
    
    public PictureFile()
    {
        new PictureFile(null);
    }
    
    public PictureFile(String _newPictureData)
    {
        this.m_PictureData = _newPictureData;
    }
    
    public String getPictureData()
    {
        return this.m_PictureData;
    }
    
    public void setPictureData(String _newPictureData)
    {
        this.m_PictureData = _newPictureData;
    }
}

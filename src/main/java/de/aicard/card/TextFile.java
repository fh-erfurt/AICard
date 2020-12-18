package de.aicard.card;

public class TextFile extends CardContent
{
    private String m_TextData;
    
    public TextFile()
    {
        new TextFile(null);
    }
    
    public TextFile(String _newTextData)
    {
        this.m_TextData = _newTextData;
    }
    
    public String getTextData()
    {
        return this.m_TextData;
    }
    
    public void setTextData(String _newTextData)
    {
        this.m_TextData = _newTextData;
    }
}

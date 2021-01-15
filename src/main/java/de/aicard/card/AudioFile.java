package de.aicard.card;

public class AudioFile extends CardContent
{
    // MEMBER VARIABLES
    // this will be a real audio in future...
    private String m_AudioData;
    private boolean m_isPlaying;
    private String m_Title;
    
    // CONSTRUCTORS
    public AudioFile()
    {
        this(null, null);
    }
    
    public AudioFile(String _newAudioData, String _newTitle)
    {
        this.m_isPlaying = false;
        this.m_AudioData = _newAudioData;
        this.m_Title     = _newTitle;
    }
    
    
    // GETTER + SETTER
    public String getAudioData() throws NullPointerException
    {
        if(this.m_AudioData == null)
        {
            throw new NullPointerException("AudioData was not set.");
        }
        
        return this.m_AudioData;
    }
    
    public void setAudioData(String _newAudioData)
    {
        this.m_AudioData = _newAudioData;
    }
    
    public boolean getIsPlaying()
    {
        return this.m_isPlaying;
    }
    
    public void setIsPlaying(boolean _newIsPlaying)
    {
        this.m_isPlaying = _newIsPlaying;
    }
    
    public String getTitle() throws NullPointerException
    {
        if(this.m_Title == null)
        {
            throw new NullPointerException("AudioTitle was not set.");
        }
        
        return this.m_Title;
    }
    
    public void setTitle(String _neTitle)
    {
        this.m_Title = _neTitle;
    }
    
    
    // Methods
    public String pauseAudioData()
    {
        m_isPlaying = false;
        return "AudioData is paused";
    }
    
    public String playAudioData()
    {
        m_isPlaying = true;
        return "AudioData is playing";
    }
    

}

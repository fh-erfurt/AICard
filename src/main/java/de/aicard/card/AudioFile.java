package de.aicard.card;

public class AudioFile extends CardContent
{
    // MEMBER VARIABLES
    // this will be a real audio in future...
    private String m_AudioData;
    private boolean m_isPlaying;
    //TODO write functions for isPlaying
    //TODO Überschrift
    
    
    // CONSTRUCTORS
    public AudioFile()
    {
        this(null);
    }
    
    public AudioFile(String _newAudioData)
    {
        this.m_AudioData = _newAudioData;
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
    
    
    // Methods
    public String pauseAudioData()
    {
        return "AudioData is paused";
    }
    
    public String playAudioData()
    {
        return "AudioData is playing";
    }
    
}

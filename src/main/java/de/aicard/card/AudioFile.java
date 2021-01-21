package de.aicard.card;

/**
 * Provides an AudioFile for side of a card that can be played and paused
 * Currently using placeholder Strings
 * Will be real Audio in Java 2
 *
 * @author: Martin Kühlborn
 */
public class AudioFile implements CardContent
{
    // MEMBER VARIABLES
    private String audioData;
    private boolean isPlaying;
    private String title;
    
    // CONSTRUCTORS
    public AudioFile()
    {
        this(null, null);
    }
    
    public AudioFile(String _newAudioData, String _newTitle)
    {
        this.isPlaying = false;
        this.audioData = _newAudioData;
        this.title     = _newTitle;
    }
    
    
    // GETTER + SETTER
    public String getAudioData() throws NullPointerException
    {
        if(this.audioData == null)
        {
            throw new NullPointerException("AudioData was not set.");
        }
        
        return this.audioData;
    }
    
    public void setAudioData(String _newAudioData)
    {
        this.audioData = _newAudioData;
    }
    
    public boolean getIsPlaying()
    {
        return this.isPlaying;
    }
    
    public void setIsPlaying(boolean _newIsPlaying)
    {
        this.isPlaying = _newIsPlaying;
    }
    
    public String getTitle() throws NullPointerException
    {
        if(this.title == null)
        {
            throw new NullPointerException("AudioTitle was not set.");
        }
        
        return this.title;
    }
    
    public void setTitle(String _newTitle)
    {
        this.title = _newTitle;
    }
    
    
    // Methods
    public String pauseAudioData()
    {
        isPlaying = false;
        return "AudioData is paused";
    }
    
    public String playAudioData()
    {
        isPlaying = true;
        return "AudioData is playing";
    }
    

}

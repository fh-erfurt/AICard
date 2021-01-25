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
    /**
     * Placeholder for an audio file. Will be a real audio in Java 2
     */
    private String audioData;
    /**
     * Boolean, that is true if the audio file is currently playing, false if it is not.
     */
    private boolean isPlaying;
    /**
     * The title of the CardContent. Will be shown on top of the AudioData.
     */
    private String title;
    
    // CONSTRUCTORS

    /**
     * Constructor of an AudioFile. Sets isPlaying to false assigns an audio file and a title to
     * the member variables.
     *
     * @param newAudioData  Audio File to initialise audioData
     * @param newTitle  String to initialize title
     */
    public AudioFile(String newAudioData, String newTitle)
    {
        this.isPlaying = false;
        this.audioData = newAudioData;
        this.title     = newTitle;
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
    
    public void setAudioData(String newAudioData)
    {
        this.audioData = newAudioData;
    }
    
    public boolean getIsPlaying()
    {
        return this.isPlaying;
    }
    
    public void setIsPlaying(boolean newIsPlaying)
    {
        this.isPlaying = newIsPlaying;
    }
    
    public String getTitle() throws NullPointerException
    {
        if(this.title == null)
        {
            throw new NullPointerException("AudioTitle was not set.");
        }
        
        return this.title;
    }
    
    public void setTitle(String newTitle)
    {
        this.title = newTitle;
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

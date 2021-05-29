package de.aicard.domains.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Provides an AudioFile for side of a card that can be played and paused
 * Currently using placeholder Strings
 * Will be real Audio in Java 2
 *
 * @author Martin Kuehlborn
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@DiscriminatorValue( value = "audiofile")
public class AudioFile extends CardContent
{
    // MEMBER VARIABLES
    /**
     * Placeholder for an audio file. Will be a real audio in Java 2
     */
    @Column
    private String audioData;
    /**
     * Boolean, that is true if the audio file is currently playing, false if it is not.
     */
    @Column
    private boolean isPlaying;
    /**
     * The title of the CardContent. Will be shown on top of the AudioData.
     */
    @Column
    private String title;
    
    // CONSTRUCTORS

    /**
     * Constructor of an AudioFile. Sets isPlaying to false assigns an audio file and a title to
     * the member variables.
     *
     * @param _newAudioData  Audio File to initialise audioData
     * @param _newTitle  String to initialize title
     */
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

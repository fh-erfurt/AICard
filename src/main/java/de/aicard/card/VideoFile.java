package de.aicard.card;

/**
 * Provides an VideoFile for side of a card that can be played and paused
 * Currently using placeholder Strings
 * Will be real Video in Java 2
 *
 * @author: Martin Kühlborn
 */
public class VideoFile implements CardContent
{
    // MEMBER VARIABLES
    private String videoData;
    private boolean isPlaying;
    private String title;
    
    // CONSTRUCTORS
    public VideoFile()
    {
        this(null, null);
    }
    
    public VideoFile(String _newVideoData, String _newTitle)
    {
        this.videoData = _newVideoData;
        this.isPlaying = false;
        this.title     = _newTitle;
    }
    
    
    // GETTER + Setter
    public String getVideoData() throws NullPointerException
    {
        if(this.videoData == null)
        {
            throw new NullPointerException("VideoData was not set.");
        }
        
        return this.videoData;
    }
    
    public void setVideoData(String _newVideoData)
    {
        this.videoData = _newVideoData;
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
            throw new NullPointerException("VideoTitle was not set.");
        }
        
        return this.title;
    }
    
    public void setTitle(String _newTitle)
    {
        this.title = _newTitle;
    }
    
    
    // METHODS
    public String pauseVideoData()
    {
        return "VideoData is paused";
    }
    
    public String playVideoData()
    {
        return "VideoData is playing";
    }
    
}

package de.aicard.card;

/**
 * Provides an VideoFile for side of a card that can be played and paused
 * Currently using placeholder Strings
 * Will be real Video in Java 2
 *
 * @author Martin Kuehlborn
 */
public class VideoFile implements CardContent
{
    // MEMBER VARIABLES
    /**
     * Placeholder for a video file. Will be a real video in Java 2
     */
    private String videoData;
    /**
     * Boolean, that is true if the video file is currently playing, false if it is not.
     */
    private boolean isPlaying;
    /**
     * The title of the CardContent. Will be shown on top of the VideoData.
     */
    private String title;
    
    // CONSTRUCTORS

    /**
     * Constructor of a VideoFile. Sets isPlaying to false assigns an audio file and a title to
     * the member variables.
     *
     * @param _newVideoData  Video file to initialise audioData
     * @param _newTitle  String to initialize title
     */
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
        this.isPlaying = false;
        return "VideoData is paused";
    }
    
    public String playVideoData()
    {
        this.isPlaying = true;
        return "VideoData is playing";
    }
    
}

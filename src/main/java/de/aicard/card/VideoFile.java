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
    //ToDo do we need this?
    public VideoFile()
    {
        this(null, null);
    }

    /**
     * Constructor of a VideoFile. Sets isPlaying to false assigns an audio file and a title to
     * the member variables.
     *
     * @param newVideoData  Video file to initialise audioData
     * @param newTitle  String to initialize title
     */
    public VideoFile(String newVideoData, String newTitle)
    {
        this.videoData = newVideoData;
        this.isPlaying = false;
        this.title     = newTitle;
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
    
    public void setVideoData(String newVideoData)
    {
        this.videoData = newVideoData;
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
            throw new NullPointerException("VideoTitle was not set.");
        }
        
        return this.title;
    }
    
    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }
    
    
    // METHODS
    //ToDo Sinn von dem String begründen.
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

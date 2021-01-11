package de.aicard.card;

public class VideoFile extends CardContent
{
    // MEMBER VARIABLES
    private String m_VideoData;
    
    // CONSTRUCTORS
    public VideoFile()
    {
        this(null);
    }
    
    public VideoFile(String _newVideoData)
    {
        this.m_VideoData = _newVideoData;
    }
    
    
    // GETTER + Setter
    public String getVideoData() throws NullPointerException
    {
        if(this.m_VideoData == null)
        {
            throw new NullPointerException("VideoData was not set.");
        }
        
        return this.m_VideoData;
    }
    
    public void setVideoData(String _newVideoData)
    {
        this.m_VideoData = _newVideoData;
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

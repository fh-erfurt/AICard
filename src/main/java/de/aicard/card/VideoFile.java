package de.aicard.card;

public class VideoFile extends CardContent
{
    private String m_VideoData;
    
    public VideoFile()
    {
        new VideoFile(null);
    }
    
    public VideoFile(String _newVideoData)
    {
        this.m_VideoData = _newVideoData;
    }
    
    
    public String getVideoData()
    {
        return this.m_VideoData;
    }
    
    public void setVideoData(String _newVideoData)
    {
        this.m_VideoData = _newVideoData;
    }
    
    public String pauseVideoData()
    {
        return "VideoData is paused";
    }
    
    public String playVideoData()
    {
        return "VideoData is playing";
    }
    
}

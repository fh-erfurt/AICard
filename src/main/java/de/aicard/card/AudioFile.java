package de.aicard.card;

public class AudioFile extends CardContent
{
    private String m_AudioData;
    
    public AudioFile()
    {
        new AudioFile("Test");
    }
    
    public AudioFile(String _newAudioData)
    {
        this.m_AudioData = _newAudioData;
    }
    
    public String getAudioData()
    {
        return this.m_AudioData;
    }
    
    public void setAudioData(String _newAudioData)
    {
        this.m_AudioData = _newAudioData;
    }
    
    public String pauseAudioData()
    {
        return "AudioData is paused";
    }
    
    public String playAudioData()
    {
        return "AudioData is playing";
    }
    
    
    
}

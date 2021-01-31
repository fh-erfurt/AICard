package de.aicard.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class to test the functionalities of the CardContent-Child-Classes
 *
 * Only those funtionalities are tested here, that are not already tested in
 * the class CardTest
 *
 * @author Daniel Michel
 */
public class CardContentTest
{
    @Test
    public void testIsPlaying()
    {
        //given: an AudioFile and a VideoFile

        AudioFile audio = new AudioFile("audioContent", "audioTitle");
        VideoFile video = new VideoFile("videoContent", "videoTitle");

        //when: we do nothing
        //then: both are not playing
        Assertions.assertEquals(false, audio.getIsPlaying());
        Assertions.assertEquals(false, video.getIsPlaying());

        //when: we play both files
        audio.playAudioData();
        video.playVideoData();
        //then: it is playing
        Assertions.assertEquals(true, audio.getIsPlaying());
        Assertions.assertEquals(true, video.getIsPlaying());

        //when: we pause both files
        audio.pauseAudioData();
        video.pauseVideoData();
        //then: both are not playing
        Assertions.assertEquals(false, audio.getIsPlaying());
        Assertions.assertEquals(false, video.getIsPlaying());
        
    }
}

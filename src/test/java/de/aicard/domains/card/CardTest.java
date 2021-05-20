package de.aicard.domains.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class for testing all functionality of the CardClass
 *
 * @author Martin Kuehlborn, (Daniel Michel)
 */
class CardTest
{
    @Test
    public void testingCardFrontAndBackandEdibility()
    {
        //given: two TextFiles with testStrings as data
        String expectedTestData1 = "I'm testData1";
        String expectedTestData2 = "I'm testData2";
    
        TextFile testTextFile1 = new TextFile(expectedTestData1);
        TextFile testTextFile2 = new TextFile(expectedTestData2);

        //when: creating Card with the two TextFiles as front and back
        Card testCard = new Card((CardContent) testTextFile1, (CardContent) testTextFile2);

        //then: the testStrings should be accessible via front and back of the Card
        Assertions.assertEquals(expectedTestData1, ((TextFile) testCard.getCardFront()).getTextData());
        Assertions.assertEquals(expectedTestData2, ((TextFile) testCard.getCardBack()).getTextData());

        //when: editing the content of the front and back of the Card
        expectedTestData1 = "I'm edited1";
        expectedTestData2 = "I'm edited2";
    
        ((TextFile) testCard.getCardFront()).setTextData(expectedTestData1);
        ((TextFile) testCard.getCardBack()).setTextData(expectedTestData2);
        
        //then: the value of CardFront and CardBack are the edited Strings
        Assertions.assertEquals(expectedTestData1, ((TextFile) testCard.getCardFront()).getTextData());
        Assertions.assertEquals(expectedTestData2, ((TextFile) testCard.getCardBack()).getTextData());
    }
    
    @Test
    public void testingCardWithAllFileTypes()
    {
        //given: 4 empty Cards. 4 CardContents (1 Text, 1 Picture, 1 Audio, 1 Video)
        String testCardTextData = "I'm TextData";
        String testCardPictureData = "I'm PictureData";
        String testCardAudioData = "I'm AudioData";
        String testCardAudioTitle = "I'm AudioTitle";
        String testCardVideoData = "I'm VideoData";
        String testCardVideoTitle = "I'm VideoTitle";

        //when: setting the front and back of the Cards to the wanted content of different types

        Card testCardText = new Card((CardContent) new TextFile(testCardTextData), (CardContent) new TextFile(testCardTextData));
        Card testCardPicture = new Card((CardContent) new PictureFile(testCardPictureData), (CardContent) new PictureFile(testCardPictureData));
        Card testCardAudio = new Card((CardContent) new AudioFile(testCardAudioData, testCardAudioTitle), (CardContent) new AudioFile(testCardAudioData, testCardAudioTitle));
        Card testCardVideo = new Card((CardContent) new VideoFile(testCardVideoData, testCardVideoTitle), (CardContent) new VideoFile(testCardVideoData, testCardVideoTitle));
        
        
        //then: we can access this content via the Card (Tests for all 4 CardContent types)
        Assertions.assertEquals(testCardTextData,((TextFile)testCardText.getCardFront()).getTextData());

        Assertions.assertEquals(testCardPictureData, ((PictureFile) testCardPicture.getCardFront()).getPictureData());

        Assertions.assertEquals(testCardAudioData,((AudioFile) testCardAudio.getCardFront()).getAudioData());
        Assertions.assertEquals(testCardAudioTitle, ((AudioFile) testCardAudio.getCardFront()).getTitle());

        Assertions.assertEquals(testCardVideoData,((VideoFile) testCardVideo.getCardFront()).getVideoData());
        Assertions.assertEquals(testCardVideoTitle, ((VideoFile) testCardVideo.getCardFront()).getTitle());
    }
}
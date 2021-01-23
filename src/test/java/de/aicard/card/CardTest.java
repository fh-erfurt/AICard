package de.aicard.card;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest
{
    @Test
    public void testingCardFrontAndBackandEditability()
    {
    
        String expectedTestData1 = "I'm testData1";
        String expectedTestData2 = "I'm testData2";
    
        TextFile testTextFile1 = new TextFile(expectedTestData1);
        TextFile testTextFile2 = new TextFile(expectedTestData2);
    
        Card testCard = new Card((CardContent) testTextFile1, (CardContent) testTextFile2);
        Assertions.assertEquals(expectedTestData1, ((TextFile) testCard.getCardFront()).getTextData());
        Assertions.assertEquals(expectedTestData2, ((TextFile) testCard.getCardBack()).getTextData());
    
        expectedTestData1 = "I'm edited1";
        expectedTestData2 = "I'm edited2";
    
        ((TextFile) testCard.getCardFront()).setTextData(expectedTestData1);
        ((TextFile) testCard.getCardBack()).setTextData(expectedTestData2);
        
        
        Assertions.assertEquals(expectedTestData1, ((TextFile) testCard.getCardFront()).getTextData());
        Assertions.assertEquals(expectedTestData2, ((TextFile) testCard.getCardBack()).getTextData());
    
    }
    
    
    @Test
    public void testingCardWithAllFileTypes()
    {
        Card testCardText = new Card();
        Card testCardPicture = new Card();
        Card testCardAudio = new Card();
        Card testCardVideo = new Card();
        
        String testCardTextData = "I'm TextData";
        String testCardPictureData = "I'm PictureData";
        String testCardAudioData = "I'm AudioData";
        String testCardVideoData = "I'm VideoData";
        
        testCardText.setCardFront((CardContent) new TextFile(testCardTextData));
        Assertions.assertEquals(testCardTextData,((TextFile)testCardText.getCardFront()).getTextData());
        
        testCardPicture.setCardFront((CardContent) new PictureFile(testCardPictureData));
        Assertions.assertEquals(testCardPictureData, ((PictureFile) testCardPicture.getCardFront()).getPictureData());
        
        testCardAudio.setCardFront((CardContent) new AudioFile(testCardAudioData, ""));
        Assertions.assertEquals(testCardAudioData,((AudioFile) testCardAudio.getCardFront()).getAudioData());
        
        testCardVideo.setCardFront((CardContent) new VideoFile(testCardVideoData, ""));
        Assertions.assertEquals(testCardVideoData,((VideoFile) testCardVideo.getCardFront()).getVideoData());
    }
}
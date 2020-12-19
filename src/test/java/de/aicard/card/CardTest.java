package de.aicard.card;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest
{
    @Test
    public void testingCardFrontAndBack()
    {
        
        String ExpectedTestData1 = "I'm TestData1";
        String ExpectedTestData2 = "I'm TestData2";
        
        TextFile TestTextFile1 = new TextFile(ExpectedTestData1);
        TextFile TestTextFile2 = new TextFile(ExpectedTestData2);
        
        Card TestCard = new Card((CardContent) TestTextFile1, (CardContent) TestTextFile2);
        Assertions.assertEquals(ExpectedTestData1,((TextFile)TestCard.getCardFront()).getTextData());
        Assertions.assertEquals(ExpectedTestData2, ((TextFile)TestCard.getCardBack()).getTextData());
        
    }
    
    @Test
    public void testingCardWithAllFileTypes()
    {
//        ArrayList<Card> Cards = new ArrayList<>();
//
//        for(int Index = 0; Index <4 ; Index++)
//        {
//            Cards.add(new Card());
//        }
//
//
        
        Card TestCardText = new Card();
        Card TestCardPicture = new Card();
        Card TestCardAudio = new Card();
        Card TestCardVideo = new Card();
        
        String TestCardTextData = "I'm TextData";
        String TestCardPictureData = "I'm PictureData";
        String TestCardAudioData = "I'm AudioData";
        String TestCardVideoData = "I'm VideoData";
        
        TestCardText.setCardFront((CardContent) new TextFile(TestCardTextData));
        Assertions.assertEquals(TestCardTextData,((TextFile)TestCardText.getCardFront()).getTextData());
        
        TestCardPicture.setCardFront((CardContent) new PictureFile(TestCardPictureData));
        Assertions.assertEquals(TestCardPictureData, ((PictureFile) TestCardPicture.getCardFront()).getPictureData());
        
        TestCardAudio.setCardFront((CardContent) new AudioFile(TestCardAudioData));
        Assertions.assertEquals(TestCardAudioData,((AudioFile) TestCardAudio.getCardFront()).getAudioData());
        
        TestCardVideo.setCardFront((CardContent) new VideoFile(TestCardVideoData));
        Assertions.assertEquals(TestCardVideoData,((VideoFile) TestCardVideo.getCardFront()).getVideoData());
    }
}
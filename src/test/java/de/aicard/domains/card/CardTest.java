package de.aicard.domains.card;

import de.aicard.domains.enums.DataType;
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
    public void testingCardFrontAndBackandEdit()
    {
        
        CardContent CardFront = new CardContent("title1", "data1", DataType.TextFile);
        CardContent CardBack = new CardContent("title2", "data2", DataType.TextFile);
        Card testCard = new Card(CardFront, CardBack);
        
        
        //given: two testStrings as data
        String expectedFrontData = "data1";
        String expectedBackData = "data2";
        DataType expectedDataType = DataType.TextFile;
        
        //then: the testStrings should be accessible via front and back of the Card
        
        Assertions.assertEquals(expectedFrontData, (testCard.getCardFront().getData()));
        Assertions.assertEquals(expectedBackData, (testCard.getCardBack().getData()));
        Assertions.assertEquals(expectedDataType, (testCard.getCardFront().getType()));
        Assertions.assertEquals(expectedDataType, (testCard.getCardBack().getType()));
        
        //when: editing the content of the front and back of the Card
        expectedFrontData = "I'm edited1";
        expectedBackData = "I'm edited2";
        
        (testCard.getCardFront()).setData(expectedFrontData);
        (testCard.getCardBack()).setData(expectedBackData);
        
        //then: the value of CardFront and CardBack are the edited Strings
        Assertions.assertEquals(expectedFrontData, (testCard.getCardFront()).getData());
        Assertions.assertEquals(expectedBackData, (testCard.getCardBack()).getData());
    }
    
    @Test
    public void testingCardWithAllFileTypes()
    {
        
        String CardTextDataFront = "textDataFront";
        String CardPictureTitleBack = "title4";
        DataType CardAudioTypeFront = DataType.AudioFile;
        DataType CardVideoTypeBack = DataType.VideoFile;
        
        //when: setting the front and back of the Cards to the wanted content of different types
        
        Card testCardText = new Card(new CardContent("title1", "textDataFront", DataType.TextFile), new CardContent("title2", "textDataBack", DataType.TextFile));
        Card testCardPicture = new Card(new CardContent("title3", "pictureDataFront", DataType.PictureFile), new CardContent("title4", "pictureDataBack", DataType.PictureFile));
        Card testCardAudio = new Card(new CardContent("title5", "audioDataFront", DataType.AudioFile), new CardContent("title6", "audiDataBack", DataType.AudioFile));
        Card testCardVideo = new Card(new CardContent("title7", "videoDataFront", DataType.VideoFile), new CardContent("title8", "videoDataBack", DataType.VideoFile));
        
        
        //then: we can access this content via the Card (Tests for all 4 CardContent types)
        Assertions.assertEquals(CardTextDataFront, (testCardText.getCardFront()).getData()); // access front data for text file
        Assertions.assertEquals(CardPictureTitleBack, (testCardPicture.getCardBack()).getTitle()); // access back title for picture file
        Assertions.assertEquals(CardAudioTypeFront, (testCardAudio.getCardFront()).getType()); // access front type for audio file
        Assertions.assertEquals(CardVideoTypeBack, (testCardVideo.getCardBack()).getType()); // access back type for video file
    }
    
    
}
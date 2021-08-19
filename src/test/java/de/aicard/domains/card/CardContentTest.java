package de.aicard.domains.card;

import de.aicard.domains.enums.DataType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class to test the functionalities of the CardContent-Child-Classes
 * <p>
 * Only those funtionalities are tested here, that are not already tested in
 * the class CardTest
 *
 * @author Daniel Michel
 */
public class CardContentTest
{
    @Test
    public void testCardContent()
    {
        CardContent CardFront = new CardContent("title1", "data1", DataType.TextFile);
        
        String expectedData = "data1";
        String expectedTitle = "title1";
        DataType expectedDataType = DataType.TextFile;
        
        Assertions.assertEquals(expectedData, (CardFront.getData()));
        Assertions.assertEquals(expectedTitle, (CardFront.getTitle()));
        Assertions.assertEquals(expectedDataType, (CardFront.getType()));
        
    }
}

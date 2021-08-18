package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * CardContent is one Side of a card
 *
 * @author Martin Kuehlborn
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardContent extends BaseEntity {
    @Column
    private String title;

    /**
     * data in cardcontent with type TextFile is used as a string to display
     * <p>
     * data in cardcontent with other types is used as filenames which will be displayed
     */
    @Column(length = 512)
    private String data;

    @Column
    private DataType type;

    /**
     * CardContent constructor
     *
     * @param title /
     * @param data  /
     * @param type  /
     */
    public CardContent(String title, String data, String type) {
        this.title = title;
        this.data = data;
        this.type = getDataTypeFromString(type);
    }

    /**
     * selects correct input type for each possible option
     *
     * @param type /
     * @return DataType of the cardside
     */
    private DataType getDataTypeFromString(String type) {
        DataType dataType;
        switch (type) {
            case "PictureFile":
                dataType = DataType.PictureFile;
                break;
            case "VideoFile":
                dataType = DataType.VideoFile;
                break;
            case "AudioFile":
                dataType = DataType.AudioFile;
                break;
            default:
                dataType = DataType.TextFile;
                break;
        }
        return dataType;
    }
}

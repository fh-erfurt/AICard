package de.aicard.services;

import de.aicard.domains.card.CardContent;
import de.aicard.domains.enums.DataType;
import de.aicard.storages.CardContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardContentService {
    @Autowired
    CardContentRepository cardContentRepository;

    public CardContent getNewCardContent(String title, String data, String typeString){
        DataType type = this.getDataTypeFromString(typeString);
        CardContent cardContent = new CardContent(title, data, type);
        return cardContent;
    }

    public DataType getDataTypeFromString(String type){
        DataType dataType = null;
        switch (type) {
            case "PictureFile":
                dataType = DataType.PictureFile;
                break;
            case "TextFile":
                dataType = DataType.TextFile;
                break;
            case "VideoFile":
                dataType = DataType.VideoFile;
                break;
            case "AudioFile":
                dataType = DataType.AudioFile;
                break;
        }
        return dataType;
    }
}

package de.aicard.services;

import de.aicard.domains.card.Card;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.learnset.CardList;
import de.aicard.storages.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    private final LearnSetService learnSetService;

    @Autowired
    public CardService(CardService cardService, LearnSetService learnSetService) {
        this.learnSetService = learnSetService;
    }

    public List<Card> setCardData(String filePath, CardList cardList){
        List <Card> listOfCards = cardList.getListOfCards();
        for ( Card card : listOfCards)
        {
            if(card.getCardFront().getType() != DataType.TextFile) {
                card.getCardFront().setData(filePath + card.getCardFront().getData());
            }

            if(card.getCardBack().getType() != DataType.TextFile)
            {
                card.getCardBack().setData(filePath + card.getCardBack().getData());
            }
        }
        return listOfCards;
    }

    public void deleteCard(Long id){
        if(cardRepository.existsById(id)){
            Card card = cardRepository.findById(id).get();
            if(card.getCardFront().getType() != DataType.TextFile)
            {
                // TODO : sollte das in ein TryCatch oder so ähnlich?
                File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.getCardFront().getData());
                file.delete();
            }
            if(card.getCardBack().getType() != DataType.TextFile)
            {
                // TODO : sollte das in ein TryCatch oder so ähnlich?
                File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.getCardBack().getData());
                file.delete();
            }
            learnSetService.removeCardFromList(card);
            cardRepository.deleteById(id);
        }
    }

    public void deleteAllCardsFromLearnSet(Long learnSetId){
        for(Card card : learnSetService.getCardList(learnSetId).getListOfCards()){
            this.deleteCard(card.getId());
        }
    }

}

package de.aicard.services;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    CardStatusRepository cardStatusRepository;
    
    @Autowired
    LearningSessionRepository learningSessionRepository;

    @Autowired
    LearnSetAboRepository learnSetAboRepository;

    @Autowired
    CardListRepository cardListRepository;

    @Autowired
    LearnSetRepository learnSetRepository;

    private final CardContentService cardContentService;

    @Autowired
    public CardService(CardContentService cardContentService) {
        this.cardContentService = cardContentService;
    }

    public Card getCardById(Long cardId)
    {
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent())
        {
            return card.get();
        }
        else
        {
            return null;
        }
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

    public Long getLearnSetIdByCardId(Long cardId){
        Optional<LearnSet> learnSet = learnSetRepository.getLearnSetByCardId(cardId);
        System.out.println("LearnSetIsPresent: "+learnSet.isPresent());
        if(learnSet.isPresent()){
            return learnSet.get().getId();
        }
        return -1L;
    }

    public void removeCardFromCardList(Card card){
        Long cardId = card.getId();
        Long learnSetId = this.getLearnSetIdByCardId(cardId);
        if(learnSetId>=-1L && learnSetRepository.existsById(learnSetId)){
            LearnSet learnSet = learnSetRepository.findById(learnSetId).get();
            learnSet.getCardList().removeFromList(card);
            learnSetRepository.save(learnSet);
        }
    }

    private void deleteCardContent(Card card){
        if(card.getCardFront().getType() != DataType.TextFile)
        {
            // TODO : sollte das in ein TryCatch oder so ähnlich? --> JA
            File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.getCardFront().getData());
            file.delete();
        }
        if(card.getCardBack().getType() != DataType.TextFile)
        {
            // TODO : sollte das in ein TryCatch oder so ähnlich?
            File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.getCardBack().getData());
            file.delete();
        }
    }
    
    

    public void deleteCard(Card card){
        Long id = card.getId();
        //löschen aus Cardlist in learnset
        //löschen des CardStatus in allen abos
        //
        
        if(cardRepository.existsById(id)){
            
            Long learnSetId = this.getLearnSetIdByCardId(card.getId());
            
            
            //funzt nich
            
            System.out.println("learnSetId: "+learnSetId);
            List<LearnSetAbo> learnSetAbos = learnSetAboRepository.findAllByLearnSetId(learnSetId);
            this.removeCardFromCardList(card);
            System.out.println("countAbos: "+learnSetAbos.size());
            System.out.println("before Loop");
            int i = 0;
            for (LearnSetAbo abo:learnSetAbos)
            {
                System.out.println("forAbo::"+i);
                CardStatus status = abo.removeCardStatusByCard(card);
                learnSetAboRepository.save(abo);
                if(status != null){
                    System.out.println("status not null");
                    cardStatusRepository.delete(status);
                }
                i++;
            }
            System.out.println("after Loop");
            
            cardRepository.delete(card);
            
            //delete data on card
            this.deleteCardContent(card);
        }
    }

    public Card addNewCard(String cardFrontType, String cardFrontTitel, String cardFrontInput, String cardBackType, String cardBackTitel, String cardBackInput){

        Card card = new Card();
        CardContent cardContentFront = null;
        CardContent cardContentBack = null;

        if(cardFrontInput != null && !cardFrontInput.isEmpty()
        && cardBackInput != null && !cardBackInput.isEmpty()){
            cardContentFront = cardContentService.getNewCardContent(cardFrontTitel, cardFrontInput, cardFrontType);
            cardContentBack = cardContentService.getNewCardContent(cardBackTitel, cardBackInput, cardBackType);
        }
        else{
            throw  new IllegalStateException("eine Texteingabe fehlt!");
        }
        card.setCardFront(cardContentFront);
        card.setCardBack(cardContentBack);

        cardRepository.save(card);

        return card;

    }
    
    public Card addNewCard(String cardFrontType, String cardFrontTitel, MultipartFile cardFrontInput, String cardBackType, String cardBackTitel, String cardBackInput) throws IOException, IllegalStateException {
       String cardFrontInputString = this.handleFileInput(cardFrontInput, cardFrontType);
       return this.addNewCard(cardFrontType, cardFrontTitel, cardFrontInputString, cardBackType, cardBackTitel, cardBackInput);
    }

    public Card addNewCard(String cardFrontType, String cardFrontTitel, String cardFrontInput, String cardBackType, String cardBackTitel, MultipartFile cardBackInput) throws IOException, IllegalStateException{
        String cardBackInputString = this.handleFileInput(cardBackInput, cardBackType);
        return this.addNewCard(cardFrontType, cardFrontTitel, cardFrontInput, cardBackType, cardBackTitel, cardBackInputString);
    }

    public Card addNewCard(String cardFrontType, String cardFrontTitel, MultipartFile cardFrontInput, String cardBackType, String cardBackTitel, MultipartFile cardBackInput) throws IOException, IllegalStateException{
        String cardFrontInputString = this.handleFileInput(cardFrontInput, cardFrontType);
        String cardBackInputString = this.handleFileInput(cardBackInput, cardBackType);
        return this.addNewCard(cardFrontType, cardFrontTitel, cardFrontInputString, cardBackType, cardBackTitel, cardBackInputString);
    }

    public String getCorrectTitle(String cardFileType, String pictureTitle, String textTitle, String videoTitle, String audioTitle){
        String cardTitle = null;
        switch (cardFileType) {
            case "PictureFile":
                cardTitle = pictureTitle;
                break;
            case "TextFile":
                cardTitle = textTitle;
                break;
            case "VideoFile":
                cardTitle = videoTitle;
                break;
            case "AudioFile":
                cardTitle = audioTitle;
                break;
        }
        return cardTitle;
    }

    public MultipartFile getCorrectInput(String cardFileType, MultipartFile videoInput, MultipartFile pictureInput, MultipartFile audioInput){
        MultipartFile fileInput = null;
        switch (cardFileType) {
            case "PictureFile":
                fileInput = pictureInput;
                break;
            case "VideoFile":
                fileInput = videoInput;
                break;
            case "AudioFile":
                fileInput = audioInput;
                break;
        }
        return fileInput;
    }
    
    public String handleFileInput(MultipartFile fileInput,String expectedType) throws IOException, IllegalStateException{
        String filePath = System.getProperty("user.dir") + "\\cardFiles\\";
        String cardFileInputPath = null;
        if (fileInput != null && ! fileInput.isEmpty())
        {
            // file exists
            String fileName = System.currentTimeMillis() + "_" + fileInput.getOriginalFilename();
            String cardFrontFilePath = filePath + fileName;
            cardFrontFilePath = cardFrontFilePath.replace("\\", "\\\\");
            File newFile = new File(cardFrontFilePath);
            fileInput.transferTo(newFile);
            
            
//            String mimetype = new MimetypesFileTypeMap().getContentType(newFile);
//            String fileType = mimetype.split("/")[0];
//            System.out.println("mimetype: " + mimetype);
//            System.out.println("filetype:" + fileType);
            String fileType  = fileInput.getContentType().split("/")[0];
            System.out.println(expectedType);
            if(expectedType.equals("VideoFile") && fileType.equals("video") ||
               expectedType.equals("AudioFile") && fileType.equals("audio") ||
               expectedType.equals("PictureFile") && fileType.equals("image"))
            {
                cardFileInputPath = fileName;
            }
            else
            {
               newFile.delete();
               throw new IllegalStateException("Falscher Datentyp");
            }
        }
        else
        {
            throw new IllegalStateException("Keine Datei hochgeladen");
        }

        return cardFileInputPath;
    }



}

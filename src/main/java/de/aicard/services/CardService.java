package de.aicard.services;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * handles finding, creating, deleting, and saving cards
 *
 * @author Clemes Berger, Daniel Michel, Martin Kuehlborn
 */
@Service
public class CardService
{
    final CardRepository cardRepository;
    
    final CardStatusRepository cardStatusRepository;
    
    final LearningSessionRepository learningSessionRepository;
    
    final LearnSetAboRepository learnSetAboRepository;
    
    final CardListRepository cardListRepository;
    
    final LearnSetRepository learnSetRepository;
    
    
    @Autowired
    public CardService(CardRepository cardRepository, CardStatusRepository cardStatusRepository, LearningSessionRepository learningSessionRepository, LearnSetAboRepository learnSetAboRepository, CardListRepository cardListRepository, LearnSetRepository learnSetRepository)
    {
        this.cardRepository = cardRepository;
        this.cardStatusRepository = cardStatusRepository;
        this.learningSessionRepository = learningSessionRepository;
        this.learnSetAboRepository = learnSetAboRepository;
        this.cardListRepository = cardListRepository;
        this.learnSetRepository = learnSetRepository;
    }
    
    public Optional<Card> getCard(Long cardId)
    {
        return cardRepository.findById(cardId);
    }
    
    /**
     * removes a given card from cardlist in learnset
     *
     * @param card given card
     */
    public void removeCardFromCardList(Card card)
    {
        Optional<LearnSet> learnSet = learnSetRepository.getLearnSetByCardId(card.getId());
        if (learnSet.isPresent())
        {
            learnSet.get().getCardList().removeFromList(card);
            learnSetRepository.save(learnSet.get());
        }
    }
    
    /**
     * deletes card and removes all references to it
     *
     * @param card card to delete
     */
    public void deleteCard(Card card)
    {
        Optional<LearnSet> learnSet = learnSetRepository.getLearnSetByCardId(card.getId());
        if (cardRepository.existsById(card.getId()) && learnSet.isPresent())
        {
            
            List<LearnSetAbo> learnSetAbos = learnSetAboRepository.findAllByLearnSetId(learnSet.get().getId());
            this.removeCardFromCardList(card);
            for (LearnSetAbo abo : learnSetAbos)
            {
                CardStatus status = abo.removeCardStatusByCard(card);
                learnSetAboRepository.save(abo);
                if (status != null)
                {
                    cardStatusRepository.delete(status);
                }
            }
            
            cardRepository.delete(card);
            
            //delete data on card
            card.deleteCardContent();
        }
    }
    
    /**
     * main addNewCard Method that is always called by submethods
     *
     * @param cardFrontType  /
     * @param cardFrontTitel /
     * @param cardFrontInput /
     * @param cardBackType   /
     * @param cardBackTitel  /
     * @param cardBackInput  /
     * @return new Card
     */
    public Card addNewCard(String cardFrontType, String cardFrontTitel, String cardFrontInput, String cardBackType, String cardBackTitel, String cardBackInput)
    {
        
        Card card = new Card();
        CardContent cardContentFront;
        CardContent cardContentBack;
        
        if (cardFrontInput != null && ! cardFrontInput.isEmpty()
                && cardBackInput != null && ! cardBackInput.isEmpty())
        {
            cardContentFront = new CardContent(cardFrontTitel, cardFrontInput, cardFrontType);
            cardContentBack = new CardContent(cardBackTitel, cardBackInput, cardBackType);
        }
        else
        {
            throw new IllegalStateException("eine Texteingabe fehlt!");
        }
        card.setCardFront(cardContentFront);
        card.setCardBack(cardContentBack);
        
        cardRepository.save(card);
        
        return card;
        
    }
    
    /**
     * creates and returns new card with file in card front
     *
     * @param cardFrontType  /
     * @param cardFrontTitel /
     * @param cardFrontInput /
     * @param cardBackType   /
     * @param cardBackTitel  /
     * @param cardBackInput  /
     * @return new card by calling main addNewCard Mehtod
     */
    public Card addNewCard(String cardFrontType, String cardFrontTitel, MultipartFile cardFrontInput, String cardBackType, String cardBackTitel, String cardBackInput) throws IOException, IllegalStateException
    {
        String cardFrontInputString = this.handleFileInput(cardFrontInput, cardFrontType);
        return this.addNewCard(cardFrontType, cardFrontTitel, cardFrontInputString, cardBackType, cardBackTitel, cardBackInput);
    }
    
    /**
     * creates and returns new card with file in card back
     *
     * @param cardFrontType  /
     * @param cardFrontTitel /
     * @param cardFrontInput /
     * @param cardBackType   /
     * @param cardBackTitel  /
     * @param cardBackInput  /
     * @return new card by calling main addNewCard Method
     */
    public Card addNewCard(String cardFrontType, String cardFrontTitel, String cardFrontInput, String cardBackType, String cardBackTitel, MultipartFile cardBackInput) throws IOException, IllegalStateException
    {
        String cardBackInputString = this.handleFileInput(cardBackInput, cardBackType);
        return this.addNewCard(cardFrontType, cardFrontTitel, cardFrontInput, cardBackType, cardBackTitel, cardBackInputString);
    }
    
    /**
     * creates and returns new card with files in card front and back
     *
     * @param cardFrontType  /
     * @param cardFrontTitel /
     * @param cardFrontInput /
     * @param cardBackType   /
     * @param cardBackTitel  /
     * @param cardBackInput  /
     * @return new card by calling main addNewCard Method
     */
    public Card addNewCard(String cardFrontType, String cardFrontTitel, MultipartFile cardFrontInput, String cardBackType, String cardBackTitel, MultipartFile cardBackInput) throws IOException, IllegalStateException
    {
        String cardFrontInputString = this.handleFileInput(cardFrontInput, cardFrontType);
        String cardBackInputString = this.handleFileInput(cardBackInput, cardBackType);
        return this.addNewCard(cardFrontType, cardFrontTitel, cardFrontInputString, cardBackType, cardBackTitel, cardBackInputString);
    }
    
    /**
     * return correct title string by checking enum in cardFileType
     *
     * @param cardFileType enum that is provided by frontend
     * @param pictureTitle /
     * @param textTitle    /
     * @param videoTitle   /
     * @param audioTitle   /
     * @return correct title string
     */
    public String getCorrectTitle(String cardFileType, String pictureTitle, String textTitle, String videoTitle, String audioTitle)
    {
        String cardTitle = null;
        switch (cardFileType)
        {
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
    
    /**
     * gets correct input from optional file inputs in controller
     *
     * @param cardFileType enum provided by fronted
     * @param videoInput   /
     * @param pictureInput /
     * @param audioInput   /
     * @return correct input file
     */
    public MultipartFile getCorrectInput(String cardFileType, MultipartFile videoInput, MultipartFile pictureInput, MultipartFile audioInput)
    {
        MultipartFile fileInput = null;
        switch (cardFileType)
        {
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
    
    /**
     * checks if input type matches input
     * if correct, saves input file to cardFiles folder and return filename
     *
     * @param fileInput    multipart file
     * @param expectedType expected filetype (image, video or audio)
     * @return filename of saved input file
     */
    public String handleFileInput(MultipartFile fileInput, String expectedType) throws IOException, IllegalStateException
    {
        String filePath = System.getProperty("user.dir") + "\\cardFiles\\";
        String cardFileInputPath;
        if (fileInput != null && ! fileInput.isEmpty())
        {
            // file exists
            String fileName = System.currentTimeMillis() + "_" + fileInput.getOriginalFilename();
            String cardFrontFilePath = filePath + fileName;
            cardFrontFilePath = cardFrontFilePath.replace("\\", "\\\\");
            File newFile = new File(cardFrontFilePath);
            fileInput.transferTo(newFile);
            
            String fileType = Objects.requireNonNull(fileInput.getContentType()).split("/")[0];
            if (expectedType.equals("VideoFile") && fileType.equals("video") ||
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

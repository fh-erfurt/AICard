package de.aicard.controller;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.domains.learnset.LearningSession;
import de.aicard.services.AccountService;
import de.aicard.services.CardContentService;
import de.aicard.services.CardService;
import de.aicard.services.LearnSetService;
import de.aicard.storages.LearnSetAboRepository;
import de.aicard.storages.LearnSetRepository;
import de.aicard.storages.LearningSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LearningSessionController
{
    
    @Autowired
    public LearnSetAboRepository learnSetAboRepository;
    
    @Autowired
    public LearningSessionRepository learningSessionRepository;
    
    @Autowired
    public LearnSetRepository learnSetRepository;
    
    private final CardService cardService;
    @Autowired
    public LearningSessionController(CardService cardService)
    {
        this.cardService = cardService;
    }
    
    @GetMapping("/initializeLearningSession/{learnSetAboId}")
    public String getInitializeLearningSession(@PathVariable("learnSetAboId") Long learnSetAboId, Model model)
    {
        model.addAttribute("learnSetAboID", learnSetAboId);
        // TODO: iterate over learnset/abo to get all new cards with cardstatus or delete card
        Optional<LearnSetAbo> learnSetAbo = learnSetAboRepository.findById(learnSetAboId);
        if(learnSetAbo.isPresent())
        {
            
            CardList cardlist = learnSetAbo.get().getLearnSet().getCardList();
            List<CardStatus> cardStatusList = learnSetAbo.get().getCardStatus();
            for (Card card : cardlist.getListOfCards())
            {
                
                // TODO : was passiert mit Karten die aus dem LearnSet gelöscht wurden?
                //  wie entfernen die die Karten hier? andersherum über die Lisen gehen?
                //  erst die CardStatusList und dann die Cardlist?
                
                boolean cardIsInCardStatusList = false;
                for(CardStatus cardStatus : cardStatusList)
                {
                    if (cardStatus.getCard().equals(card)) {
                        cardIsInCardStatusList = true;
                        break;
                    }
                }
                if(!cardIsInCardStatusList)
                {
                    cardStatusList.add(new CardStatus(card));
                }
            }
            
            learnSetAboRepository.save(learnSetAbo.get());
        }
        
        return "/initializeLearningSession";
    }
    
    @PostMapping("/initializeLearningSession/{learnSetAboId}")
    public String postInitializeLearningSession(@PathVariable("learnSetAboId") Long learnSetAboId, @RequestParam(value = "cardCount") int cardCount, Model model)
    {
        
        Optional<LearnSetAbo> learnSetAbo =  learnSetAboRepository.findById(learnSetAboId);
        if(learnSetAbo.isPresent()){
            if(learnSetAbo.get().getLearningSession() != null){
                LearningSession learningSession = learnSetAbo.get().getLearningSession();
                learnSetAbo.get().setLearningSession(null);
                learningSession.setCardStatusList(null);
                learningSessionRepository.delete(learningSession);
                
            }
            LearningSession learningSession = learnSetAbo.get().createLearningSession(cardCount);
            learnSetAboRepository.save(learnSetAbo.get());
            
            return "redirect:/learnCard/" + learnSetAbo.get().getId();
        }
        
        return "redirect:/index";
    }
    
    @GetMapping("/learnCard/{learnSetAboId}")
    public String getLearnCard(@PathVariable("learnSetAboId") Long learnSetAboId, Model model)
    {
        
        //Optional<LearningSession> learningSession = learningSessionRepository.findById(learningSessionId);
        Optional<LearnSetAbo> learnSetAbo = learnSetAboRepository.findById(learnSetAboId);
        if(learnSetAbo.isPresent()){
            LearningSession learningSession = learnSetAbo.get().getLearningSession();
            if(learningSession != null && !learningSession.getCardStatusList().isEmpty()){
                //System.out.println("presentSession: "+learningSession);
                int currentCardIndex = learningSession.getCurrentCard();
//            if(currentCardIndex > learningSession.get().)
                Card card = learningSession.getCardStatusList().get(currentCardIndex).getCard();
                
                // Korrekten FilePath zusammenbauen um ihn im Frontend anzuzeigen
                String filePath = "/learnSetImage/";
                CardList cardList = new CardList();
                cardList.addToList(card);
                Card updatedFilePathCard = cardService.setCardData(filePath, cardList).get(0);
                
                model.addAttribute("card", updatedFilePathCard);
                model.addAttribute("learnSetAboId", learnSetAbo.get().getId());
                return "/learnCard" /*+ learningSessionId*/;
            }
           
        }
        return "redirect:/index";
    }
    

    @ResponseBody
    @PostMapping("/learnCard/{learnSetAboId}")
    public ModelAndView postLearnCard(@PathVariable("learnSetAboId") Long learnSetAboId,@RequestParam("cardKnown") Boolean cardKnown , Model model)
    {
        ModelAndView modelAndView = new ModelAndView();
        Optional<LearnSetAbo> learnSetAbo = learnSetAboRepository.findById(learnSetAboId);
        if(learnSetAbo.isPresent())
        {
            if(learnSetAbo.get().getLearningSession() != null)
            {
                LearningSession learningSession = learnSetAbo.get().getLearningSession();
                if(cardKnown)
                {
                    
                    learningSession.cardKnown();
                }
                else
                {
                    learningSession.cardUnknown();
                }
                learnSetAboRepository.save(learnSetAbo.get());
                if(learningSession.isActive())
                {
                    modelAndView.setViewName("redirect:/learnCard/" + learnSetAboId);
                    return modelAndView;
                    //return "redirect:/learnCard/" + learnSetAboId;
                }
            }
        }
        
        modelAndView.setViewName("redirect:/learnSets");
        return modelAndView;
//        return "/index";
    }
}

package de.aicard.controller;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.domains.learnset.LearningSession;
import de.aicard.services.CardService;
import de.aicard.storages.LearnSetAboRepository;
import de.aicard.storages.LearnSetRepository;
import de.aicard.storages.LearningSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
/**
 * @Author Martin Kühlborn,Clemens Berger
 */
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


    /**
     * shows the initilizeLearningsession.html makes sure to create new cardStatus if not already present fore each card
     *
     * @param learnSetAboId
     * @param model
     * @return
     */
    @GetMapping("/initializeLearningSession/{learnSetAboId}")
    public String getInitializeLearningSession(@PathVariable("learnSetAboId") Long learnSetAboId, Model model)
    {
        model.addAttribute("learnSetAboID", learnSetAboId);
        Optional<LearnSetAbo> learnSetAbo = learnSetAboRepository.findById(learnSetAboId);
        if(learnSetAbo.isPresent())
        {
            
            CardList cardlist = learnSetAbo.get().getLearnSet().getCardList();
            List<CardStatus> cardStatusList = learnSetAbo.get().getCardStatus();
            for (Card card : cardlist.getListOfCards())
            {
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

    /**
     * starts the Learningsession with the given amount of cards and uses a randomisation of Cards based on
     * their knowledge level
     * @param learnSetAboId
     * @param cardCount
     * @param model
     * @return
     */
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

    /**
     * shows the card which is to learn
     * @param learnSetAboId
     * @param model
     * @return
     */
    @GetMapping("/learnCard/{learnSetAboId}")
    public String getLearnCard(@PathVariable("learnSetAboId") Long learnSetAboId, Model model)
    {

        Optional<LearnSetAbo> learnSetAbo = learnSetAboRepository.findById(learnSetAboId);
        if(learnSetAbo.isPresent()){
            LearningSession learningSession = learnSetAbo.get().getLearningSession();
            if(learningSession != null && !learningSession.getCardStatusList().isEmpty()){
                int currentCardIndex = learningSession.getCurrentCard();
                Card card = learningSession.getCardStatusList().get(currentCardIndex).getCard();
                
                // Korrekten FilePath zusammenbauen um ihn im Frontend anzuzeigen
                String filePath = "/getFile/";
                card.setCardPath(filePath);
                model.addAttribute("card", card);
                model.addAttribute("learnSetAboId", learnSetAbo.get().getId());
                return "/learnCard";
            }
           
        }
        return "redirect:/index";
    }

    /**
     * learns the shown card either increses or decreases the knowledgelevel of the card
     * knowledgelevel can't drop below 0 and is maxed out at 5
     * @param learnSetAboId
     * @param cardKnown
     * @param model
     * @return
     */
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
                }
            }
        }
        
        modelAndView.setViewName("redirect:/learnSets");
        return modelAndView;
    }
}

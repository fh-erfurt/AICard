package de.aicard.controller;

import de.aicard.domains.learnset.LearnSet;
import de.aicard.services.LearnSetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
/**
 * @Author Martin Kühlborn,Clemens Berger
 */
@Controller
public class SocialController
{
    
    private final LearnSetService learnSetService;
    
    public SocialController(LearnSetService learnSetService){
        this.learnSetService = learnSetService;
    }

    /**
     * upcomming comment feature for learnSets
     * @param learnSetId
     * @param model
     * @return
     */
    @GetMapping("/comments/{learnSetId}")
    public String getComments(@PathVariable("learnSetId") Long learnSetId, Model model){
        
        
        Optional<LearnSet> learnSet = learnSetService.getLearnSet(learnSetId);
        if(learnSet.isPresent()){
            model.addAttribute("learnSet", learnSet);
            return "comments";
        }
        return "redirect:/index";
    }
}

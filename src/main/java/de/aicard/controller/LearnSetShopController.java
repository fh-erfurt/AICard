package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.services.AccountService;
import de.aicard.services.LearnSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * @Author Martin Kühlborn,Clemens Berger
 */
@Controller
public class LearnSetShopController
{
    private final AccountService accountService;
    private final LearnSetService learnSetService;
    
    @Autowired
    LearnSetShopController(AccountService accountService,LearnSetService learnSetService){
        this.accountService = accountService;
        this.learnSetService = learnSetService;
    }

    /**
     * shows all for the account visible unsubscribed LearnSets
     * only public are visible
     * and protected if the user is in the same faculty
     * @param stringFaculty
     * @param learnSetTitle
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/learnSetShop")
    public String getLearnSetShop(@RequestParam(name = "faculty", required = false, defaultValue = "duNull")String stringFaculty,
                                  @RequestParam(name="learnSetTitle", required = false, defaultValue = "") String learnSetTitle,
                                  Principal principal, Model model)
    {
        List<LearnSet> learnSets = learnSetService.findAll();
        List<LearnSet> frontEndLearnSets = new ArrayList<>();
        Optional<Account> account = accountService.getAccount(principal);
        if(!learnSets.isEmpty() && account.isPresent())
        {
            // check if Account can Access these LearnSets and if the Account follows already
            for (LearnSet learnset : learnSets)
            {
                if (learnset.isAuthorizedToAccessLearnSet(account.get()))
                {
                    boolean inList = false;
                    for (LearnSetAbo learnSetAbo : account.get().getLearnsetAbos())
                    {
                        if (learnSetAbo.getLearnSet().equals(learnset)) {
                            inList = true;
                            break;
                        }
                    }
                    if (!inList)
                    {
                        frontEndLearnSets.add(learnset);
                    }
                }
            }
    
            // evaluate RequestParams
    
            // if we want to filter for faculty -> filter!
            List<LearnSet> helperLearnSets = new ArrayList<>();
            String selectedFacultyName = "";
            try
            {
                Faculty.valueOf(stringFaculty);
                selectedFacultyName = stringFaculty;
    
                for (LearnSet learnSet : frontEndLearnSets)
                {
                    if (learnSet.getFaculty().equals(Faculty.valueOf(stringFaculty)))
                    {
                        helperLearnSets.add(learnSet);
                    }
                }
                frontEndLearnSets = helperLearnSets;
                helperLearnSets = new ArrayList<>();
    
            } catch (IllegalArgumentException e)
            {
                System.out.println(e);
            }

            // if we want to filter learnset title of substring -> filter
            learnSetTitle = learnSetTitle.trim(); // trim front and back blanks before returning to frontend
            if(!learnSetTitle.isEmpty())
            {
                for (LearnSet learnSet : frontEndLearnSets)
                {
                    if (learnSet.getTitle().toLowerCase().contains(learnSetTitle.toLowerCase()))
                    {
                        helperLearnSets.add(learnSet);
                    }
                }
                frontEndLearnSets = helperLearnSets;
                helperLearnSets = new ArrayList<>(); // unnecessary but kept here for possible future expansion
            }
            
            model.addAttribute("learnSetTitle", learnSetTitle);
            model.addAttribute("selectedFacultyName", selectedFacultyName);
            model.addAttribute("learnSetList", frontEndLearnSets);
        }
        return"learnSetShop";
    }

    /**
     * adds a learnsetabo to the account
     *
     * @param principal
     * @param learnSetId
     * @return
     */
    @GetMapping("/followLearnSet/{learnSetId}")
    public String getFollowLearnSet(Principal principal, @PathVariable("learnSetId") Long learnSetId){
        
        
        Optional<LearnSet> learnSet = learnSetService.getLearnSet(learnSetId);
        Optional<Account> account = accountService.getAccount(principal);
        if(learnSet.isPresent() && account.isPresent()){
            account.get().addLearnSetAbo(learnSet.get());
            accountService.saveAccount(account.get());
        }
        return "redirect:/learnSetShop";
    }
    
    
}

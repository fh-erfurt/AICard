package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class LearnSetShopController
{
    @Autowired
    public LearnSetRepository learnSetRepository;
    
    @Autowired
    public AccountRepository accountRepository;
    
    
    @GetMapping("/learnSetShop")
    public String getLearnSetShop(@RequestParam(name = "faculty", required = false, defaultValue = "duNull")String stringFaculty,
                                  @RequestParam(name="learnSetTitle", required = false, defaultValue = "") String learnSetTitle,
                                  Principal principal, Model model)
    {
        List<LearnSet> learnSets = learnSetRepository.findAll();
        List<LearnSet> frontEndLearnSets = new ArrayList<>();
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
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
                        if (learnSetAbo.getLearnSet().equals(learnset) && ! inList)
                        {
                            inList = true;
                        }
                    }
                    if (! inList)
                    {
                        frontEndLearnSets.add(learnset);
                    }
    
                }
            }
    
            // evaluate RequestParams
    
            // if we want to filter for faculty -> filter!
            System.out.println("faculty:" + stringFaculty);
            List<LearnSet> helperLearnSets = new ArrayList<>();
            try
            {
                Faculty.valueOf(stringFaculty);
    
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
    
            // filter for learnset->title containt substring learnSetTitle
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
                helperLearnSets = new ArrayList<>();
            }
            
            model.addAttribute("learnSetList",frontEndLearnSets);
        }
        return"/learnSetShop";
    }
    
    @GetMapping("/followLearnSet/{learnSetId}")
    public String getFollowLearnSet(Principal principal, Model model, @PathVariable("learnSetId") Long learnSetId){
        
        Optional<LearnSet> learnSet = learnSetRepository.findById(learnSetId);
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        if(learnSet.isPresent() && account.isPresent()){
            account.get().addNewFavoriteLearnSet(learnSet.get());
            accountRepository.save(account.get());
        }
        return "redirect:/learnSetShop";
    }
    
    
}

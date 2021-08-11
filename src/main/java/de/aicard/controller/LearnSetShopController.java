package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LearnSetShopController
{
    @Autowired
    public LearnSetRepository learnSetRepository;
    
    @Autowired
    public AccountRepository accountRepository;
    
    
    @GetMapping("/learnSetShop")
    public String getLearnSetShop(Principal principal, Model model)
    {
        List<LearnSet> learnSets = learnSetRepository.findAll();
        List<LearnSet> frontEndLearnSets = new ArrayList<>();
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        if(!learnSets.isEmpty() && account.isPresent())
        {
            for (LearnSet learnset:learnSets)
            {
                if(learnset.isAuthorizedToAccessLearnSet(account.get())){
                    boolean inList = false;
                    for (LearnSetAbo learnSetAbo : account.get().getLearnsetAbos())
                    {
                        if(learnSetAbo.getLearnSet().equals(learnset) && !inList)
                        {
                            inList = true;
                        }
                    }
                    if(!inList)
                    {
                        frontEndLearnSets.add(learnset);
                    }
                    
                }
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

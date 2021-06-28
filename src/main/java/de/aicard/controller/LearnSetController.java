package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static de.aicard.config.Session.getCookieContent;

@Controller
public class LearnSetController
{
    @Autowired
    LearnSetRepository learnSetRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    @GetMapping("/createLearnset")
    public String signUp(Model model)
    {
        // check if user is logged in -> else; send to index
        
        model.addAttribute("newLearnset", new LearnSet());
        
        return "createLearnset";
    }
    
    @PostMapping("/createLearnset")
    public String createLearnset(@ModelAttribute("newLearnset") LearnSet newLearnset, Model model/*, HttpServletRequest request, HttpServletResponse response*/)
    {
        //TODO: get id from Cookie, save Learnset with user as Owner and Owner as first person in adminList
        return "index";
    }
    
    
}

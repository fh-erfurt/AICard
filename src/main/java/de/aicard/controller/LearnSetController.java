package de.aicard.controller;

import de.aicard.config.Session;
import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.CardListRepository;
import de.aicard.storages.CardRepository;
import de.aicard.storages.LearnSetRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class LearnSetController
{
    @Autowired
    LearnSetRepository learnSetRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    CardListRepository cardListRepository;



    @GetMapping("/createLearnset")
    public String getCreateLearnset(HttpServletRequest request, HttpServletResponse response, Model model)
    {
        // check if user is logged in -> else: send to Login
        String sessionString = Session.getSessionValue(request.getCookies());
        if(sessionString == null)
        {
            return "redirect:/login";
        }
        
        model.addAttribute("newLearnset", new LearnSet());
        return "createLearnset";
    }

    @PostMapping("/createLearnset")
    public String postCreateLearnset(@ModelAttribute("newLearnset") LearnSet newLearnset, Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String accountID = Session.getSessionValue(request.getCookies());
        // check if user is logged in -> else: send to Login
        if(accountID != null)
        {
            List<Account> accountList = accountRepository.findAllById(Long.parseLong(accountID));
            if (accountList.size() == 1)
            {
                System.out.println(accountList.get(0).toString());
                newLearnset.setOwner(accountList.get(0));
                newLearnset.setAdminList(new ArrayList<>());
                newLearnset.addAdmin(accountList.get(0));
                newLearnset.setCardList(new CardList());
                newLearnset.setCommentList(new ArrayList<>());
    
                learnSetRepository.save(newLearnset);
    
                return "redirect:cardOverview/" + newLearnset.getId();
            }
        }
    
        return "redirect:/login";
    }

    @GetMapping("/learnSets")
    public String getLearnSets(Model model ,HttpServletRequest request,HttpServletResponse response)
    {
        // check if user is logged in -> else: send to Login
        String accountID = Session.getSessionValue(request.getCookies());
        
        if(accountID != null)
        {
            List<Account> accountList = accountRepository.findAllById(Long.parseLong(accountID));
            if (accountList.size() == 1)
            {
                List<LearnSet> learnSetListAdmin = learnSetRepository.findAdminLearnsets(Long.parseLong(accountID));
                List<LearnSet> learnSetListFollowed = learnSetRepository.findFollwedLearnsets(Long.parseLong(accountID));

                model.addAttribute("learnSetListAdmin", learnSetListAdmin);
                model.addAttribute("learnSetListFollowed", learnSetListFollowed);
            }
        }
        return "learnSets";
    }

    
//    @GetMapping("cardOverview/{id}")
//    @ResponseBody
//    public String getCardoverviewRest(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request)
//    {
//        JSONArray jsonArray = new JSONArray();
//
//        Optional<LearnSet> learnSet = learnSetRepository.findById(id);
//        if(learnSet.get() != null)
//        {
//            for ( Card card :  learnSet.get().getCardList().getListOfCards())
//            {
//                String path = System.getProperty("user.dir");
//                path = path + "\\\\cardFiles";
//                path = path.replace("\\", "\\\\");
//                System.out.println(path);
////                jsonArray.add(card);
//            }
//
//        }
//        return jsonArray.toString();
//    }

    
    // TODO : work in progress; problems showing each File of each Card of the LearnSet
    @GetMapping("cardOverview/{id}")
    public String getCardOverview(@PathVariable Long id,HttpServletRequest request,HttpServletResponse response, Model model)
    {
        Optional<LearnSet> learnSet =  learnSetRepository.findById(id);
        String cookieValue = Session.getSessionValue(request.getCookies());
        if(cookieValue == null){
            return "redirect:login";
        }
        List<Account> accounts = accountRepository.findAllById(Long.parseLong(cookieValue));
        //System.out.println("account Faculty " + accounts.get(0).getFaculty());
        //System.out.println("Set Faculty " + learnSet.get().getFaculty());
        
        // ach scheißdrauf
        if (learnSet.isPresent() && (
            learnSet.get().getVisibility() == Visibility.PUBLIC ||
            learnSet.get().getVisibility() == Visibility.PRIVATE && learnSet.get().getAdminList().contains(accounts.get(0)) ||
            learnSet.get().getVisibility() == Visibility.PROTECTED && accounts.get(0).getFaculty() == learnSet.get().getFaculty()))
        {
            model.addAttribute("learnSet", learnSetRepository.findById(id));
            model.addAttribute("cardList", learnSet.get().getCardList().getListOfCards());
            // get Card Models
            //cardRepository.findAllByLearnsetId();

            System.out.println(learnSetRepository.findById(id).get().getTitle());

            return "cardOverview";
        }
        else
        {
            return "redirect:/index";
        }
    }
    

    @GetMapping("/addCard/{learnSetID}")
    public String getAddCard(@PathVariable Long learnSetID, HttpServletRequest request  ,Model model)
    {
        Optional<LearnSet> learnSet = learnSetRepository.findById(learnSetID);
        
        if(learnSet.isPresent())
        {
            if(learnSetRepository.findById(learnSetID).isPresent())
            {
                if (learnSet.get().getAdminList().contains(accountRepository.findById(Long.parseLong(Session.getSessionValue(request.getCookies()))).get()))
                {
                    model.addAttribute("learnSetID", learnSetID);
                    return "addCard";
                }
            }
        }
        return "redirect:/index";
    }
    
    
    @PostMapping("/addCard/{learnSetID}")
    @ResponseBody
    public ModelAndView postAddCard(
            
            @PathVariable Long learnSetID, HttpServletRequest request, Model model,
            @RequestParam("cardFrontTextFileInput") String cardFrontTextFileInput )
    {
        ModelAndView modelAndView = new ModelAndView();
        Optional<LearnSet> learnSet = learnSetRepository.findById(learnSetID);
    
        if(learnSet.isPresent())
        {
            if(learnSet.get().getAdminList().contains(accountRepository.findById(Long.parseLong(Session.getSessionValue(request.getCookies()))).get()))
            {
                // we are here if the learnSet exists and the Owner or an Admin is logged in
                
                
            }
        }
        
        modelAndView.setViewName("index");
        return modelAndView;
    }


}

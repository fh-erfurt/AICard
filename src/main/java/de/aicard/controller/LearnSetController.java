package de.aicard.controller;

import de.aicard.config.Session;
import de.aicard.domains.account.Account;
import de.aicard.domains.account.Student;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.TextFile;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.CardRepository;
import de.aicard.storages.LearnSetRepository;


import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.aicard.config.Session.delSession;
import static de.aicard.config.Session.getCookieContent;

@Controller
public class LearnSetController
{
    @Autowired
    LearnSetRepository learnSetRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;



    @GetMapping("/createLearnset")
    public String getCreateLearnset(Model model)
    {
        // check if user is logged in -> else; send to index

        model.addAttribute("newLearnset", new LearnSet());

        return "createLearnset";
    }

    @PostMapping("/createLearnset")
    public String postCreateLearnset(@ModelAttribute("newLearnset") LearnSet newLearnset, Model model, HttpServletRequest request, HttpServletResponse response)
    {
        //TODO: get id from Cookie, save Learnset with user as Owner and Owner as first person in adminList
        String accountID = getCookieContent(request.getCookies());
        List<Account> accountList = accountRepository.findAllById(Long.parseLong(accountID));
        if(accountList.size() == 1)
        {
            System.out.println(accountList.get(0).toString());
            newLearnset.setOwner(accountList.get(0));
            newLearnset.setAdminList(new ArrayList<>());
            newLearnset.addAdmin(accountList.get(0));
            newLearnset.setCardList(new CardList());
            newLearnset.setCommentList(new ArrayList<>());

            learnSetRepository.save(newLearnset);
        }
        
        return "redirect:cardOverview/" + newLearnset.getId();
    }

    @GetMapping("/exampleCardOverview")
    public String getExampleCardOverview(Model model ,HttpServletRequest request,HttpServletResponse response)
    {
        String accountID = getCookieContent(request.getCookies());
        List<Account> accountList = accountRepository.findAllById(Long.parseLong(accountID));
        if(accountList.size() == 1)
        {

            List<LearnSet> learnSetListAdmin = learnSetRepository.findAdminLearnsets(Long.parseLong(accountID));
            List<LearnSet> learnSetListFollowed = learnSetRepository.findFollwedLearnsets(Long.parseLong(accountID));
//            System.out.println(learnSetListAdmin.size());
//            if(learnSetListAdmin.size() >= 1 || learnSetListFollowed.size() >= 1 ){
//                for(LearnSet learnSet : learnSetListAdmin){
//                    System.out.println("Admin: "+learnSet.getTitle());
//                }
//                for(LearnSet learnSet : learnSetListFollowed){
//                    System.out.println("Followed: "+learnSet.getTitle());
//                }
//            }
            model.addAttribute("learnSetListAdmin", learnSetListAdmin);
            model.addAttribute("learnSetListFollowed", learnSetListFollowed);
        }

        return "exampleCardOverview";
    }



    @GetMapping("cardOverview/{id}")
    public String getCardOverview(@PathVariable Long id,HttpServletRequest request,HttpServletResponse response, Model model)
    {
        //muckt
        Optional<LearnSet> learnSet =  learnSetRepository.findById(id);
        String cookieValue = getCookieContent(request.getCookies());
        List<Account> accounts = accountRepository.findAllById(Long.parseLong(cookieValue));
        //System.out.println("account Faculty " + accounts.get(0).getFaculty());
        //System.out.println("Set Faculty " + learnSet.get().getFaculty());

        if(learnSet.get().getVisibility() == Visibility.PUBLIC ||
                learnSet.get().getVisibility() == Visibility.PRIVATE &&
                        learnSet.get().getAdminList().contains(accounts.get(0)) ||
                            learnSet.get().getVisibility() == Visibility.PROTECTED &&
                                    accounts.get(0).getFaculty() == learnSet.get().getFaculty())
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


    @PostMapping("/addCard/{learnSetId}")
    public String postAddCard(@PathVariable Long learnSetId, HttpServletRequest request,
                              @RequestParam("cardFrontText") String cardFrontText,
                              @RequestParam("cardBackText") String cardBackText)
    {
        // need information about which LearnSet has to be edited
        String cookieContent =  getCookieContent(request.getCookies());

        Card newCard = new Card(new TextFile(cardFrontText), new TextFile(cardBackText));
        
        Optional<LearnSet> learnSet = learnSetRepository.findById(learnSetId);
        
        if (learnSet.isPresent())
        {
            learnSet.get().getCardList().addToList(newCard);
            learnSetRepository.save(learnSet.get());
        }
        else
        {
            System.out.println("no learnset");
        }
        
        
        System.out.println("Card Front Text : " + cardFrontText);
        System.out.println("Card Back Text : " + cardBackText);

        return "redirect:/cardOverview/" + learnSetId;
    }


    @GetMapping("/addCard/{id}")
    public String getAddCard(@PathVariable Long id, Model model)
    {
        // we currently only support TextFiles, other Files will be implemented later
        model.addAttribute("newCard", new Card(new TextFile(), new TextFile()));
        model.addAttribute("id", id);

        return "addCard";
    }


}

package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.account.Student;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.aicard.config.Session.getCookieContent;

@Controller
public class LearnSetController
{
    @Autowired
    LearnSetRepository learnSetRepository;
    
    @Autowired
    AccountRepository accountRepository;
    
    
    
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
        String accountID = getCookieContent(request.getCookies(), "javaSession");
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
        
        return "redirect:index";
    }
    
    @GetMapping("/exampleCardOverview")
    public String getExampleCardOverview(Model model ,HttpServletRequest request,HttpServletResponse response)
    {
        String accountID = getCookieContent(request.getCookies(), "javaSession");
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
        Optional<LearnSet> learnSet =  learnSetRepository.findById(id);
        String cookieValue = getCookieContent(request.getCookies(), "javaSession");
        List<Account> accounts = accountRepository.findAllById(Long.parseLong(cookieValue));
        
        if(learnSet.get().getVisibility() == Visibility.PUBLIC ||
                learnSet.get().getVisibility() == Visibility.PRIVATE &&
                        learnSet.get().getAdminList().contains(accounts.get(0)) ||
                            learnSet.get().getVisibility() == Visibility.PROTECTED &&
                                    accounts.get(0).getFaculty() == learnSet.get().getFaculty())
        {
            model.addAttribute("learnSet", learnSetRepository.findById(id));
            System.out.println(learnSetRepository.findById(id).get().getTitle());
            
            return "cardOverview";
        }
        else
        {
            return "redirect:/index";
        }
        
    }
    

    
    
}

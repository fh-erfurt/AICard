package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.services.AccountService;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController
{
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * This shows the logged in users Profile, while calling showProfile() with the userID as PathVariable
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/profile")
    public String showMyProfile(Model model, HttpServletRequest request, Principal principal)
    {
        if(accountService.accountExists(principal))
        {
            return showProfile(accountService.getID(principal), model, principal);
        }
//        if(Session.getSessionValue(request.getCookies()) != null)
//        {
//            return showProfile(Long.parseLong(Session.getSessionValue(request.getCookies())), model, request);
//        }
        
        return "redirect:/index";
    }

    @GetMapping("/profile/{userID}")
    public String showProfile(@PathVariable("userID") Long userID, Model model, Principal principal)
    {
        List<String> errors = new ArrayList<>();
        // only loggedIN users can see an account

            if(accountService.accountExists(userID))
            {
                if(accountService.getAccount(userID).isPresent()){
                    model.addAttribute("account", accountService.getAccount(userID).get());
                }

                
                // if the the user which profile it is show a button to edit Profile
                // TODO : this should be obsolete with SpringSec
                if(accountService.isLoggedIn(principal, userID))
                {
                    model.addAttribute("userIsLoggedIn", true);
                }
                
                return "profile";
            }
        
        return "redirect:/index";
    }
    
    @GetMapping("/updateProfile")
    public String getUpdateProfile(Principal principal,Model model)
    {
        if(accountService.accountExists(principal))
        {
            model.addAttribute("account", accountService.getAccount(principal));
            return "updateProfile";
        }
        
        return "redirect:/index";
    }

    
   @PostMapping("/updateAccount")
   public String editAccount(@ModelAttribute("account") Account theAccount, Model model) throws NoSuchAlgorithmException {

        //TODO: hier wird ID=NULL übergeben.
       List<String> errors = new ArrayList<>();
       try{
           accountService.updateAccount(theAccount);
       }
       catch(IllegalStateException e){
           errors.add(e.getMessage());
           model.addAttribute("errorList",errors);
       }

       if(errors.isEmpty()){
           return "profile";
       }
       else{
           return "editAccount";
       }
   }


}
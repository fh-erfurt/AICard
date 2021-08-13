package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.services.AccountService;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
                model.addAttribute("verified", accountService.getAccount(principal).get().getId().equals(userID));
                model.addAttribute("account", accountService.getAccount(userID).get());
                return "profile";
            }
        
        return "redirect:/index";
    }
    
    @GetMapping("/updateProfile")
    public String getUpdateProfile(Principal principal,Model model)
    {
        if(accountService.accountExists(principal))
        {
            model.addAttribute("account", accountService.getAccount(principal).get());
            return "updateProfile";
        }
        
        return "redirect:/index";
    }
    
    @ResponseBody
    @PostMapping("/updateAccount")
    public ModelAndView postUpdateAccount(@RequestParam(value="addFriendByEmail", required = false) String addFriendByEmail,
                                    @ModelAttribute("account") Account theAccount, Model model,Principal principal) throws NoSuchAlgorithmException {
    
        System.out.println("request: "+addFriendByEmail);
        //TODO: hier wird ID=NULL übergeben.
        List<String> errors = new ArrayList<>();
        Account account = accountService.getAccount(principal).get();
        if(account.getId().equals(theAccount.getId())){
            try{
                Optional<Account> friendAccount = accountService.getAccount(addFriendByEmail);
                if(friendAccount.isEmpty() && !addFriendByEmail.isEmpty())
                    throw new IllegalStateException("Der Account existiert nicht");
                System.out.println("friendlist Exists: " + theAccount.getFriends());
                
                accountService.updateAccount(theAccount, friendAccount);
            }
            catch(IllegalStateException e){
                errors.add(e.getMessage());
            }
            
            
            
        }else {
            errors.add("Du manipulatives Arschloch!");
        }
        model.addAttribute("errorList",errors);
        model.addAttribute("account", account);
        
        ModelAndView modelAndView = new ModelAndView();
        if(errors.isEmpty()){
            
            modelAndView.setViewName("redirect:/profile");
            return modelAndView;
        }
        else{
            modelAndView.setViewName("updateProfile");
            modelAndView.addObject(model);
            return modelAndView;
            //            return "updateProfile";
        }
    }
    
    @GetMapping("/removeFriendFromFriendList/{friendId}")
    public String getRemoveFriendFromFriendList(@PathVariable("friendId") Long friendId, Model model, Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        Optional<Account> friend = accountService.getAccount(friendId);
        
        if(account.isPresent() && friend.isPresent() && account.get().getFriends().contains(friend.get())){
            account.get().removeFriend(friend.get());
            accountService.saveAccount(account.get());
        }
        
    
        return "redirect:/profile";
    
    }


}
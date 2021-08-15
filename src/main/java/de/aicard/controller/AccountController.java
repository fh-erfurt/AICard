package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;

/**
 * @Author Martin Kühlborn,Clemens Berger
 */
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
     * @param principal
     * @return profile.html or index.html
     */
    @GetMapping("/profile")
    public String showMyProfile(Model model, Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        if(account.isPresent())
        {
            return showProfile(account.get().getId(), model, principal);
        }
        return "redirect:/index";
    }

    /**
     * shows the profile of the given account
     * @param userID
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/profile/{userID}")
    public String showProfile(@PathVariable("userID") Long userID, Model model, Principal principal)
    {
        List<String> errors = new ArrayList<>();
        // only loggedIN users can see an account
        Optional<Account> account = accountService.getAccount(userID);
            if(account.isPresent())
            {
                model.addAttribute("verified", accountService.getAccount(principal).get().getId().equals(userID));
                model.addAttribute("account", account.get());
                return "profile";
            }
        
        return "redirect:/index";
    }

    /**
     * shows the updateProfile.html for the logged in user
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/updateProfile")
    public String getUpdateProfile(Principal principal,Model model)
    {
        Optional<Account> account = accountService.getAccount(principal);
        if(account.isPresent())
        {
            model.addAttribute("account", account.get());
            return "updateProfile";
        }
        
        return "redirect:/index";
    }

    /**
     * handles the edit of the logged in account, here friends can be added
     * and the account data can be changed
     * @param addFriendByEmail
     * @param theAccount
     * @param model
     * @param principal
     * @return
     * @throws NoSuchAlgorithmException
     */
    @ResponseBody
    @PostMapping("/updateAccount")
    public ModelAndView postUpdateAccount(@RequestParam(value="addFriendByEmail", required = false) String addFriendByEmail,
                                    @ModelAttribute("account") Account theAccount, Model model,Principal principal) throws NoSuchAlgorithmException {

        List<String> errors = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        Optional<Account> account = accountService.getAccount(principal);
        if(account.isPresent())
        {
            if(theAccount.getId().equals(account.get().getId())){
                try{
                    Optional<Account> friendAccount = accountService.getAccount(addFriendByEmail);
                    if(friendAccount.isEmpty() && !addFriendByEmail.isEmpty())
                        throw new IllegalStateException("Der Account existiert nicht");

                    accountService.updateAccount(theAccount, friendAccount);
                }
                catch(IllegalStateException e){
                    errors.add(e.getMessage());
                }
                
                
                
            }else {
                errors.add("Du manipulatives Arschloch!");
            }
            model.addAttribute("errorList",errors);
            model.addAttribute("account", account.get());
            
            
            if(errors.isEmpty()){
                
                modelAndView.setViewName("redirect:/profile");
                return modelAndView;
            }
            else{
                modelAndView.setViewName("updateProfile");
                modelAndView.addObject(model);
                return modelAndView;
            }
        }
        
        modelAndView.setViewName("redirect:/logout");
        return modelAndView;
    }

    /**
     * removes a friend from the friendlist of the logged in user
     * @param friendId
     * @param model
     * @param principal
     * @return
     */
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
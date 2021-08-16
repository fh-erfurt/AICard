package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
/**
 * @Author Martin Kühlborn,Clemens Berger
 */
@Controller
public class LoginController {

    private final AccountService accountService;

    @Autowired
    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * shows the account registration site
     * @param model
     * @return
     */
    @GetMapping("/registration")
    public String getRegistration(Model model)
    {
        model.addAttribute("newAccount", new Account());

        return "registration";
    }

    // TODO : nach erfolgreicher Registriebrung zu /login weiterleiten und email ggf. vorladen?
    // TODO : in registrieBrung nicht zwisch

    /**
     * shows the login site
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String getLogin(Model model)
    {
        return "login";
    }


    /**
     * creates a new account if all given data is accepted
     * check on password and email
     * email must not exist in the current database and must match the emal regex
     * password must match the password regex
     * @param newAccount
     * @param model
     * @return
     */
    @ResponseBody
    @PostMapping("/createAccount")
    public ModelAndView postCreateAccount(@RequestParam("passwordProfessor2") String password2, @ModelAttribute("Account") Account newAccount, Model model)
    {
        // List of possible errors to return to user
        List<String> errors = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        try{
            if(!password2.equals(newAccount.getPassword())){
                throw new IllegalStateException("Passwörter stimmen nicht überein");
            }
            Optional<Account> account = accountService.createAccount(newAccount);
            account.ifPresent(accountService::saveAccount);
            //modelAndView.addObject("registeredEmail",newAccount.getEmail());
            modelAndView.setViewName(getLogin(model.addAttribute("registeredEmail", newAccount.getEmail())));
            return modelAndView;
        }
        catch (IllegalStateException e){
            //TODO: Dies ist eine Verzweiflungstat nachts um 11. bei Gelegenheit schöner machen.
            errors.add(e.getMessage());
            errors.add("Anmeldung Fehlgeschlagen");
            model.addAttribute("errorList",errors);
            
            modelAndView.setViewName(getRegistration(model.addAttribute("errorList", errors)));
//            return getRegistration(model.addAttribute("errorList", errors));
            return modelAndView;
        }
    }


    


}

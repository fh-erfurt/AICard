package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@Controller
public class LoginController {

    private final AccountService accountService;

    @Autowired
    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("newAccount", new Account());

        return "registration";
    }

    // TODO : nach erfolgreicher Registriebrung zu /login weiterleiten und email ggf. vorladen?
    // TODO : in registrieBrung nicht zwisch
    @GetMapping("/login")
    public String getLogin()
    {
        return "login";
    }

    

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute("Account") Account newAccount, Model model)
    {
        // List of possible errors to return to user
        List<String> errors = new ArrayList<>();

        try{
            accountService.createAccount(newAccount);
            return "redirect:/login";
        }
        catch (IllegalStateException e){
            //TODO: Dies ist eine Verzweiflungstat nachts um 11. bei Gelegenheit schöner machen.
            errors.add(e.getMessage());
            errors.add("Anmeldung Fehlgeschlagen");
            model.addAttribute("errorList",errors);
            return "registration";
        }
    }


    


}

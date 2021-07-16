package de.aicard.controller;

import de.aicard.config.RegPattern;
import de.aicard.domains.account.Account;
import de.aicard.storages.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    
    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("newAccount", new Account());

        return "registration";
    }

    // TODO : nach erfolgreicher Registriebrung zu /login weiterleiten und email ggf. vorladen?
    // TODO : in registriebrung nicht zwisch
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

        
        // prepare for check if account with this email already exists
        String profMail = newAccount.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(profMail);
        
        if (matchingEntries.isEmpty() && RegPattern.passMatches(newAccount.getPassword()) && RegPattern.emailMatches(newAccount.getEmail())) {
            // endcode password
            newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
            accountRepository.save(newAccount);
            return "redirect:/login";
        } else {
            if (!RegPattern.passMatches(newAccount.getPassword())) {
                errors.add("Passwort entspricht nicht den Passwortrichtlinien");
                System.out.println("password not matched");
            }
            if (!matchingEntries.isEmpty()) {
                //TODO: DAS GEHT SO ABER NICHT! DOCH!
                errors.add("Ein Account mit diser E-Mail Adresse existiert bereits");
                System.out.println("entry exists");
            }
            
            if(!RegPattern.emailMatches(newAccount.getEmail())){
                errors.add("die e-mail adresse ist ungültig");
            }
            errors.add("Anmeldung Fehlgeschlagen");

            model.addAttribute("errorList",errors);

            return "registration";
        }
    }


    


}

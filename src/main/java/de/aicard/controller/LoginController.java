package de.aicard.controller;

import de.aicard.config.RegPattern;
import de.aicard.domains.account.Account;
import de.aicard.domains.account.Professor;
import de.aicard.domains.account.Student;
import de.aicard.storages.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

        model.addAttribute("newProfessor", new Professor());
        model.addAttribute("newStudent", new Student());

        return "registration";
    }

    // TODO : nach erfolgreicher Registriebrung zu /login weiterleiten und email ggf. vorladen?
    // TODO : in registriebrung nicht zwisch
    @GetMapping("/login")
    public String getLogin()
    {
        return "login";
    }

    

    @PostMapping("createNewProfessor")
    public String createNewProfessor(@ModelAttribute("newProfessor") Professor newProfessor,
                                     @ModelAttribute("newStudent") Student newStudent,
                                     Model model) throws NoSuchAlgorithmException
    {
        // List of possible errors to return to user
        List<String> errors = new ArrayList<>();

        
        // prepare for check if account with this email already exists
        String profMail = newProfessor.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(profMail);
        
        if (matchingEntries.isEmpty() && RegPattern.passMatches(newProfessor.getPassword()) && RegPattern.emailMatches(newProfessor.getEmail())) {
            // endcode password
            newProfessor.setPassword(passwordEncoder.encode(newProfessor.getPassword()));
            accountRepository.save(newProfessor);
            return "redirect:/login";
        } else {
            if (!RegPattern.passMatches(newProfessor.getPassword())) {
                errors.add("Passwort entspricht nicht den Passwortrichtlinien");
                System.out.println("password not matched");
            }
            if (!matchingEntries.isEmpty()) {
                //TODO: DAS GEHT SO ABER NICHT! DOCH!
                errors.add("Ein Account mit diser E-Mail Adresse existiert bereits");
                System.out.println("entry exists");
            }
            
            if(!RegPattern.emailMatches(newProfessor.getEmail())){
                errors.add("die e-mail adresse ist ungültig");
            }
            errors.add("Anmeldung Fehlgeschlagen");

            model.addAttribute("errorList",errors);

            return "registration";
        }
    }


    @PostMapping("createNewStudent")
    public String createNewStudent(@ModelAttribute("newProfessor") Professor newProfessor,@ModelAttribute("newStudent") Student newStudent, Model model) throws NoSuchAlgorithmException
    {
        List<String> errors = new ArrayList<>();
    
        // check if Password is Strong enough
    
        // prepare for check if account with this email already exists
        String studMail = newStudent.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(studMail);
    
        

        if (matchingEntries.isEmpty() && RegPattern.passMatches(newStudent.getPassword()) && RegPattern.emailMatches(newStudent.getEmail()) ) {
            // endcode password
            newStudent.setPassword(passwordEncoder.encode(newStudent.getPassword()));
            accountRepository.save(newStudent);
            return "redirect:index";
        } else {
            if (!RegPattern.passMatches(newStudent.getPassword())) {
                errors.add("Passwort entspricht nicht den Passwortrichtlinien");
                System.out.println("password not matched");
            }
            if (!matchingEntries.isEmpty()) {
                //TODO: DAS GEHT SO ABER NICHT! DOCH!
                errors.add("Ein Account mit diser E-Mail Adresse existiert bereits");
                System.out.println("entry exists");
            }
            if(!RegPattern.emailMatches(newStudent.getEmail())){
                errors.add("die e-mail adresse ist ungültig");
            }
            errors.add("Anmeldung Fehlgeschlagen");

            model.addAttribute("errorList",errors);

            return "registration";
        }
    }


}

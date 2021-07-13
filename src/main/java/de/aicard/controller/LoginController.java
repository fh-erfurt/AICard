package de.aicard.controller;

import de.aicard.config.Session;
import de.aicard.domains.account.Account;
import de.aicard.domains.account.Professor;
import de.aicard.domains.account.Student;
import de.aicard.storages.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    private static final String patternReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

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

        // check if Password is Strong enough
        Pattern pattern = Pattern.compile(patternReg);
        String password = newProfessor.getPassword();
        Matcher matcher = pattern.matcher(password);
        
        // prepare for check if account with this email already exists
        String profMail = newProfessor.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(profMail);
        
        // endcode password
        newProfessor.setPassword(passwordEncoder.encode(newProfessor.getPassword()));
        
        if (matchingEntries.isEmpty() && matcher.matches()) {
            accountRepository.save(newProfessor);
            return "redirect:/login";
        } else {
            if (!matcher.matches()) {
                errors.add("Passwort entspricht nicht den Passwortrichtlinien");
                System.out.println("password not matched");
            }
            if (!matchingEntries.isEmpty()) {
                //TODO: DAS GEHT SO ABER NICHT! DOCH!
                errors.add("Ein Account mit diser E-Mail Adresse existiert bereits");
                System.out.println("entry exists");
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
        Pattern pattern = Pattern.compile(patternReg);
        String password = newStudent.getPassword();
        Matcher matcher = pattern.matcher(password);
    
        // prepare for check if account with this email already exists
        String studMail = newStudent.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(studMail);
    
        // endcode password
        newStudent.setPassword(passwordEncoder.encode(newStudent.getPassword()));

        if (matchingEntries.isEmpty() && matcher.matches()) {
            accountRepository.save(newStudent);
            return "redirect:index";
        } else {
            if (!matcher.matches()) {
                errors.add("Passwort entspricht nicht den Passwortrichtlinien");
                System.out.println("password not matched");
            }
            if (!matchingEntries.isEmpty()) {
                //TODO: DAS GEHT SO ABER NICHT! DOCH!
                errors.add("Ein Account mit diser E-Mail Adresse existiert bereits");
                System.out.println("entry exists");
            }
            errors.add("Anmeldung Fehlgeschlagen");

            model.addAttribute("errorList",errors);

            return "registration";
        }
    }


}

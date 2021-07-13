package de.aicard.controller;

import de.aicard.config.Session;
import de.aicard.domains.account.Account;
import de.aicard.domains.account.Professor;
import de.aicard.domains.account.Student;
import de.aicard.storages.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@Controller
public class AccountController
{


    private static final String patternReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Autowired
    AccountRepository accountRepository;
    
    /**
     * This shows the logged in users Profile, while calling showProfile() with the userID as PathVariable
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/profile")
    public String showMyProfile(Model model, HttpServletRequest request, Principal principal)
    {
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        System.out.println(principal);
        System.out.println(principal.getName());
        
        if( account.isPresent())
        {
            return showProfile(account.get().getId(), model, principal);
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
        
        
            Optional<Account> account = accountRepository.findById(userID);
            if(account.isPresent())
            {
                model.addAttribute("account", account.get());
                if(account.get() instanceof Professor)
                {
                    model.addAttribute("professorAcademicGrade", ((Professor)account.get()).getAcademicGrade());
                }
                else
                {
                    model.addAttribute("studentSemester", ((Student)account.get()).getSemester());
                }
                // if the the user which profile it is show a button to edit Profile
                // TODO : this should be obsolete with SpringSec
                Optional<Account> account1 = accountRepository.findByEmail(principal.getName());
                if(account1.get().getId().equals(userID))
                {
                    model.addAttribute("userIsLoggedIn", true);
                }
                
                return "profile";
            }
        
        return "redirect:/index";
    }
    
    @GetMapping("/updateProfile")
    public String getUpdateProfile(HttpServletRequest request,Model model)
    {
        String userIDfromCookie = Session.getSessionValue(request.getCookies());
        if(userIDfromCookie != null)
        {
            Optional<Account> account = accountRepository.findById(Long.parseLong(userIDfromCookie));
            if(account.isPresent())
            {
                if(account.get() instanceof Professor)
                {
                    model.addAttribute("isProfessor", true);
                    model.addAttribute("professorAcademicGrade", ((Professor)account.get()).getAcademicGrade());
                }
                else
                {
                    model.addAttribute("isProfessor", false);
                    model.addAttribute("studentSemester", ((Student)account.get()).getSemester());
                }
                model.addAttribute("account", account.get());
                return "updateProfile";
            }
        }
        return "redirect:/index";
    }
    
//    @PostMapping("/updateAccount")
//    public String editAccount(@ModelAttribute("account") Account theAccount, Model model) throws NoSuchAlgorithmException {
//
//        List<String> errors = new ArrayList<>();
//        Optional<Account> oldAccount = accountRepository.findById(theAccount.getId());
//        String accountEmail = theAccount.getEmail();
//        Optional<Account> matchingEntries = accountRepository.findByEmail(accountEmail);
//        if (matchingEntries.isPresent() && matchingEntries.get().getId() != oldAccount.get().getId()) {
//
//            errors.add("Ein Account mit diser E-Mail Adresse existiert bereits");
//            System.out.println("Ein Account mit diser E-Mail Adresse existiert bereits");
//            model.addAttribute("errorList",errors);
//            return "editAccount";
//        }
//        oldAccount.get().setEmail(accountEmail);
//
//
//        Pattern pattern = Pattern.compile(patternReg);
//        String password = theAccount.getPassword();
//        Matcher matcher = pattern.matcher(password);
//        //creating md5 hash
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(password.getBytes());
//        byte[] digest = md.digest();
//        String hashedPassword = DatatypeConverter.printHexBinary(digest);
//
//        if (!matcher.matches()) {
//            errors.add("Passwort entspricht nicht den Passwortrichtlinien");
//            System.out.println("password not matched");
//            model.addAttribute("errorList",errors);
//            return "editAccount";
//        }
//        oldAccount.get().setPassword(hashedPassword);
//        oldAccount.get().setName(theAccount.getName());
//        oldAccount.get().setDescription(theAccount.getDescription());
//        oldAccount.get().setFaculty(theAccount.getFaculty());
//
//        accountRepository.save(oldAccount.get());
//        return "profile";
//    }


}
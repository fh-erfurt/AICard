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
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;


public class AccountController {


    private static final String patternReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/profile")
    public String showProfile(Model model,HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        String cookieValue = Session.getSessionValue(request.getCookies());
        if(cookieValue == null){
            return "redirect:/login";
        }
        return "profile";
    }
    @RequestMapping("/editAccount")
        public Model getEditAccount(HttpServletRequest request,Model model) {

        String accountID = Session.getSessionValue(request.getCookies());
        Optional<Account> account = accountRepository.findById(Long.parseLong(accountID));
        model.addAttribute("account", account);

        return model;
    }
    @PostMapping("/updateAccount")
    public String editAccount(@ModelAttribute("account") Account theAccount, Model model) throws NoSuchAlgorithmException {

        List<String> errors = new ArrayList<>();
        Optional<Account> oldAccount = accountRepository.findById(theAccount.getId());
        String accountEmail = theAccount.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(accountEmail);
        if (matchingEntries.isPresent() && matchingEntries.get().getId() != oldAccount.get().getId()) {

            errors.add("Ein Account mit diser E-Mail Adresse existiert bereits");
            System.out.println("Ein Account mit diser E-Mail Adresse existiert bereits");
            model.addAttribute("errorList",errors);
            return "editAccount";
        }
        oldAccount.get().setEmail(accountEmail);


        Pattern pattern = Pattern.compile(patternReg);
        String password = theAccount.getPassword();
        Matcher matcher = pattern.matcher(password);
        //creating md5 hash
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hashedPassword = DatatypeConverter.printHexBinary(digest);

        if (!matcher.matches()) {
            errors.add("Passwort entspricht nicht den Passwortrichtlinien");
            System.out.println("password not matched");
            model.addAttribute("errorList",errors);
            return "editAccount";
        }
        oldAccount.get().setPassword(hashedPassword);
        oldAccount.get().setName(theAccount.getName());
        oldAccount.get().setDescription(theAccount.getDescription());
        oldAccount.get().setFaculty(theAccount.getFaculty());

        accountRepository.save(oldAccount.get());
        return "profile";
    }


}
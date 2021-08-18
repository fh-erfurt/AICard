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
 * controller for login handles the login function and registration
 *
 * @author Martin Kühlborn,Clemens Berger
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
     *
     * @param model /
     * @return String
     */
    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("newAccount", new Account());

        return "registration";
    }


    /**
     * shows the login site
     *
     * @param model model
     * @return String
     */
    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }


    /**
     * creates a new account if all given data is accepted
     * check on password and email
     * email must not exist in the current database and must match the emal regex
     * password must match the password regex
     *
     * @param newAccount newAccount
     * @param model      model
     * @return ModelAndView
     */
    @ResponseBody
    @PostMapping("/createAccount")
    public ModelAndView postCreateAccount(@RequestParam("passwordProfessor2") String password2, @ModelAttribute("Account") Account newAccount, Model model) {
        // List of possible errors to return to user
        List<String> errors = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (!password2.equals(newAccount.getPassword())) {
                throw new IllegalStateException("Passwörter stimmen nicht überein");
            }
            Optional<Account> account = accountService.createAccount(newAccount);
            account.ifPresent(accountService::saveAccount);

            modelAndView.setViewName(getLogin(model.addAttribute("registeredEmail", newAccount.getEmail())));
            return modelAndView;
        } catch (IllegalStateException e) {
            errors.add(e.getMessage());
            errors.add("Anmeldung Fehlgeschlagen");

            modelAndView.setViewName(getRegistration(model.addAttribute("errorList", errors)));
            return modelAndView;
        }
    }
}
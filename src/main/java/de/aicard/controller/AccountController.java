package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * handles editing for accounts and shows profiles
 *
 * @author Martin Kühlborn,Clemens Berger
 */
@Controller
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * This shows the logged in users Profile, while calling showProfile() with the userID as PathVariable
     *
     * @param model     /
     * @param principal /
     * @return profile.html or index.html
     */
    @GetMapping("/profile")
    public String showMyProfile(Model model, Principal principal) {
        Optional<Account> account = accountService.getAccount(principal);
        if (account.isPresent()) {
            return showProfile(account.get().getId(), model, principal);
        }
        return "redirect:/index";
    }

    /**
     * shows the profile of the given account
     *
     * @param userID    /
     * @param model     /
     * @param principal /
     * @return html
     */
    @GetMapping("/profile/{userID}")
    public String showProfile(@PathVariable("userID") Long userID, Model model, Principal principal) {
        // only loggedIN users can see an account
        Optional<Account> account = accountService.getAccount(userID);
        Optional<Account> myAccount = accountService.getAccount(principal);

        if (account.isPresent() && myAccount.isPresent()) {
            model.addAttribute("isMyFriend", myAccount.get().getFriends().contains(account.get()));
            model.addAttribute("userIsProfileAccount", myAccount.get().getId().equals(userID));
            model.addAttribute("account", account.get());

            return "profile";
        }
        return "redirect:/index";
    }

    /**
     * shows the updateProfile.html for the logged in user
     *
     * @param principal /
     * @param model     /
     * @return html
     */
    @GetMapping("/updateProfile")
    public String getUpdateProfile(Principal principal, Model model) {
        Optional<Account> account = accountService.getAccount(principal);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            return "updateProfile";
        }

        return "redirect:/login";
    }

    /**
     * handles the edit of the logged in account, here friends can be added
     * and the account data can be changed
     * friends are added via their account email
     *
     * @param addFriendByEmail account email to add friend
     * @param theAccount       /
     * @param model            /
     * @param principal        /
     * @return html
     */
    @ResponseBody
    @PostMapping("/updateAccount")
    public ModelAndView postUpdateAccount(@RequestParam("passwordProfessor2") String password2, @RequestParam(value = "addFriendByEmail", required = false) String addFriendByEmail,
                                          @ModelAttribute("account") Account theAccount, Model model, Principal principal) {
        List<String> errors = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        Optional<Account> account = accountService.getAccount(principal);
        if (account.isPresent()) {
            if (theAccount.getId().equals(account.get().getId())) {
                try {
                    if (!password2.equals(theAccount.getPassword())) {
                        throw new IllegalStateException("Passwörter stimmen nicht überein");
                    }
                    Optional<Account> friendAccount = accountService.getAccount(addFriendByEmail);
                    if (friendAccount.isEmpty() && !addFriendByEmail.isEmpty())
                        throw new IllegalStateException("Der Account existiert nicht");
                    accountService.updateAccount(theAccount, friendAccount);
                } catch (IllegalStateException e) {
                    errors.add(e.getMessage());
                }
            } else {
                errors.add("Du manipulatives Arschloch!");
            }
            model.addAttribute("errorList", errors);
            model.addAttribute("account", account.get());
            if (errors.isEmpty()) {
                modelAndView.setViewName("redirect:/profile");
            } else {
                modelAndView.setViewName("updateProfile");
                modelAndView.addObject(model);
            }
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    /**
     * removes a friend from the friendlist of the logged in user
     *
     * @param friendId  friend account id
     * @param principal /
     * @return html
     */
    @GetMapping("/removeFriendFromFriendList/{friendId}")
    public String getRemoveFriendFromFriendList(@PathVariable("friendId") Long friendId, Principal principal) {
        Optional<Account> account = accountService.getAccount(principal);
        Optional<Account> friend = accountService.getAccount(friendId);

        if (account.isPresent() && friend.isPresent() && account.get().getFriends().contains(friend.get())) {
            account.get().removeFriend(friend.get());
            accountService.saveAccount(account.get());
        }
        return "redirect:/profile";
    }

    /**
     * friends are added to the friendlist via a button on their profile
     * @param friendId /
     * @param principal /
     * @return friends profile
     */
    @GetMapping("/addFriend/{friendId}")
    public String getAddFriend(@PathVariable("friendId") Long friendId, Principal principal) {
        Optional<Account> account = accountService.getAccount(principal);
        Optional<Account> friend = accountService.getAccount(friendId);

        if (account.isPresent() && friend.isPresent()) {
            if (!account.get().getFriends().contains(friend.get())) {
                account.get().addFriend(friend.get());
                accountService.saveAccount(account.get());
            }
        }
        return "redirect:/profile/" + friendId;
    }


}
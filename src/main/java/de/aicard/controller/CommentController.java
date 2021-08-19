package de.aicard.controller;

import de.aicard.domains.Social.Comment;
import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Recommended;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.services.AccountService;
import de.aicard.services.LearnSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

/**
 * adds a comment to the corresponding LearnSet
 *
 * @author Clemens Berger
 */
@Controller
public class CommentController {
    public final AccountService accountService;
    public final LearnSetService learnSetService;

    @Autowired
    public CommentController(LearnSetService learnSetService, AccountService accountService) {
        this.learnSetService = learnSetService;
        this.accountService = accountService;
    }

    /**
     * shows the commentlist of the learnset
     *
     * @param learnSetId /
     * @param model      /
     * @param principal  /
     * @return html
     */
    @GetMapping("/getComments/{learnSetId}")
    public String getComments(@PathVariable("learnSetId") Long learnSetId, Model model, Principal principal) {
        Optional<Account> account = accountService.getAccount(principal);
        Optional<LearnSet> learnSet = learnSetService.getLearnSet(learnSetId);
        if (account.isPresent() && learnSet.isPresent() && learnSet.get().isAuthorizedToAccessLearnSet(account.get())) {
            boolean hasCommented = false;
            for (Comment comment : learnSet.get().getCommentList()) {
                if (comment.getSender().equals(account.get())) {
                    hasCommented = true;
                }

            }
            System.out.println("hasCommented: " + hasCommented);
            model.addAttribute("hasCommented", hasCommented);
            model.addAttribute("newComment", new Comment());
            model.addAttribute("learnSet", learnSet.get());
            return "comments";
        }
        return "redirect:/learnSet/" + learnSetId;
    }

    /**
     * adds a comment to the commentlist of the learnset
     * a user can only comment once to the same learnset
     * @param recommend  string which is converted to enum of Recommended
     * @param learnSetId /
     * @param comment    /
     * @param model      /
     * @param principal  /
     * @return modelAndView
     */
    @ResponseBody
    @PostMapping("/addComment/{learnSetId}")
    public ModelAndView addComment(@RequestParam("recommend") String recommend, @PathVariable("learnSetId") Long learnSetId, @ModelAttribute("newComment") Comment comment, Model model, Principal principal) {
        System.out.println("recommend: " + recommend);
        Optional<LearnSet> learnSet = learnSetService.getLearnSet(learnSetId);
        Optional<Account> account = accountService.getAccount(principal);


        try {

            if (learnSet.isPresent() && account.isPresent() &&
                    learnSet.get().isAuthorizedToAccessLearnSet(account.get()) &&
                    !comment.getMessage().trim().isEmpty()) {
                if (recommend.equals("yes")) {
                    comment.setRecommended(Recommended.YES);
                } else if (recommend.equals("no")) {
                    comment.setRecommended(Recommended.NO);
                } else {
                    throw new IllegalStateException("wrong Enum value");
                }

                learnSet.get().addComment(new Comment(comment.getMessage().trim(), account.get(), comment.getRecommended()));
                learnSetService.saveLearnSet(learnSet.get());
            } else {
                throw new IllegalStateException("fehlende Kommentardaten");
            }
        } catch (IllegalStateException e) {
            System.out.println("exception: " + e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getComments(learnSetId, model, principal));
        return modelAndView;
    }

}

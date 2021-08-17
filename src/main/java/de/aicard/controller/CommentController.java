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

@Controller
public class CommentController
{
    public final AccountService accountService;
    public final LearnSetService learnSetService;
    
    @Autowired
    public CommentController(LearnSetService learnSetService,AccountService accountService){
        this.learnSetService = learnSetService;
        this.accountService = accountService;
    }
    
    @GetMapping("/getComments/{learnSetId}")
    public String getComments(@PathVariable("learnSetId") Long learnSetId, Model model, Principal principal){
        Optional<Account> account = accountService.getAccount(principal);
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(learnSetId);
        if(account.isPresent() && learnSet.isPresent() && learnSetService.accountIsAuthorized(account.get(),learnSet.get()))
        {
            boolean hasCommented = false;
            for (Comment comment: learnSet.get().getCommentList())
            {
                if(comment.getSender().equals(account.get()))
                {
                    hasCommented = true;
                }
                
            }
            System.out.println("hasCommented: "+hasCommented);
            model.addAttribute("hasCommented", hasCommented);
            model.addAttribute("newComment", new Comment());
            model.addAttribute("learnSet",learnSet.get());
            return "comments";
        }
        return "redirect:/learnSet/"+learnSetId;
    }
    
    @ResponseBody
    @PostMapping("/addComment/{learnSetId}")
    public ModelAndView addComment(@RequestParam("recommend")String recommend, @PathVariable("learnSetId") Long learnSetId, @ModelAttribute("newComment") Comment comment, Model model, Principal principal)
    {
        System.out.println("recommend: "+recommend);
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(learnSetId);
        Optional<Account> account = accountService.getAccount(principal);
        
        
        
        try
        {
            
            if (learnSet.isPresent() && account.isPresent() &&
                    learnSetService.accountIsAuthorized(account.get(), learnSet.get()) &&
                        !comment.getMessage().trim().isEmpty())
            {
                if(recommend.equals("yes")){
                    comment.setRecommended(Recommended.YES);
                }else if(recommend.equals("no")){
                    comment.setRecommended(Recommended.NO);
                }else{
                    throw new IllegalStateException("wrong Enum value");
                }
                
                learnSet.get().addComment(new Comment(comment.getMessage().trim(), account.get(), comment.getRecommended()));
                learnSetService.saveLearnSet(learnSet.get());
            }else{
                throw new IllegalStateException("fehlende Kommentardaten");
            }
        }
        catch (IllegalStateException e)
        {
            System.out.println("exception: "+e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getComments(learnSetId,model,principal));
        return modelAndView;
    }

}

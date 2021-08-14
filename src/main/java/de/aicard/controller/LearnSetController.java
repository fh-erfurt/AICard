package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.domains.learnset.LearningSession;
import de.aicard.services.*;


import de.aicard.storages.CardStatusRepository;
import de.aicard.storages.LearnSetAboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.activation.FileTypeMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class LearnSetController
{
    private final AccountService accountService;

    private final LearnSetService learnSetService;

    private final LearnSetAboService learnSetAboService;

    private final CardService cardService;

    private final LearningSessionService learningSessionService;
    
    @Autowired
    public LearnSetAboRepository learnSetAboRepository;

    @Autowired
    public CardStatusRepository cardStatusRepository;
    
    @Autowired
    public LearnSetController(AccountService accountService, LearnSetService learnSetService, LearnSetAboService learnSetAboService, CardService cardService,LearningSessionService learningSessionService) {
        this.accountService = accountService;
        this.learnSetService = learnSetService;
        this.learnSetAboService = learnSetAboService;
        this.cardService = cardService;
        this.learningSessionService = learningSessionService;
    }


    @GetMapping("/createLearnset")
    public String getCreateLearnset(Model model)
    {
        model.addAttribute("newLearnset", new LearnSet());
        return "createLearnset";
    }

    @PostMapping("/createLearnset")
    public String postCreateLearnset(@ModelAttribute("newLearnset") LearnSet newLearnset, Model model, Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        System.out.println("account isPresent: "+account.isPresent());
        if(accountService.isLoggedIn(principal) && account.isPresent()){
            LearnSet learnSet = learnSetService.createLearnSet(newLearnset,account.get());
            learnSetService.saveLearnSet(learnSet);
            return "redirect:cardOverview/" + learnSet.getId();
        }
    
        return "redirect:/login";
    }

    @GetMapping("/learnSets")
    public String getLearnSets(Model model ,Principal principal)
    {
        // check if user is logged in -> else: send to Login
        if(accountService.isLoggedIn(principal))
        {
            //TODO: Das geht noch eleganter.
            List<LearnSetAbo> abos = accountService.getLearnSetAbos(principal);
            List<LearnSetAbo> ownLearnSetAbos = new ArrayList<>();
            List<LearnSetAbo> favoriteLearnSetAbos = new ArrayList<>();

            for ( LearnSetAbo learnSetAbo : abos)
            {
                if(accountService.getAccount(principal).isPresent() && learnSetAbo.getLearnSet().getAdminList().contains(accountService.getAccount(principal).get())){
                    ownLearnSetAbos.add(learnSetAbo);
                }else{
                    favoriteLearnSetAbos.add(learnSetAbo);
                }
            }
            
            
            model.addAttribute("ownLearnSetAbos", ownLearnSetAbos);
            model.addAttribute("favoriteLearnSetAbos", favoriteLearnSetAbos);
        }
        return "learnSets";
    }

    @GetMapping("cardOverview/{id}")
    public String getCardOverview(@PathVariable Long id,Principal principal, Model model)
    {
        String filePath = "/learnSetImage/";
        
        if (learnSetService.accountIsAuthorized(principal, id))
        {
            // check if user has rights! to editn Learnset
            //zusätzlicher check auf den owner
            boolean isAdmin = accountService.getAccount(principal).isPresent() && learnSetService.getLearnSetByLearnSetId(id).getAdminList().contains(accountService.getAccount(principal).get());
            boolean isOwner = accountService.getAccount(principal).isPresent() && learnSetService.getLearnSetByLearnSetId(id).getOwner().equals(accountService.getAccount(principal).get());
            System.out.println("isAdmin: "+isAdmin);
            // TODO : check if the CardContentFile exists; what should we do if it doesnt?
            CardList cardList = learnSetService.getCardList(id);
            Optional<Account> account = accountService.getAccount(principal);
            if(cardList != null && account.isPresent()){
                model.addAttribute("isOwner", isOwner);
                model.addAttribute("learnSet", learnSetService.getLearnSetByLearnSetId(id));
                List<Card> listOfCards = cardService.setCardData(filePath, cardList);
                model.addAttribute("cardList", listOfCards);
                model.addAttribute("isAdmin", isAdmin);
                return "cardOverview";
            }
        }
        return "redirect:/index";
    }

    // getImagesForLearnSet
    @GetMapping("/learnSetImage/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String fileName) throws IOException
    {
        File img = new File(System.getProperty("user.dir") + "\\cardFiles\\" + fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }
    
    @GetMapping("/deleteCard/{id}")
    public String getDeleteCard(@PathVariable("id") Long id, Principal principal)
    {

        if(learnSetService.isAdmin(principal, cardService.getLearnSetIdByCardId(id)))
        {
            Long learnSetId = cardService.getLearnSetIdByCardId(id);
            Card card = cardService.getCardById(id);
            cardService.deleteCard(card);
            

            return "redirect:/cardOverview/" + learnSetId;
        }

        return "redirect:/index";

    }
    
    @GetMapping("/editCard/{id}")
    public String getEditCard(@PathVariable("id") Long id, Principal principal, Model model)
    {
        return "editCard";
    }
    
    @GetMapping("/deleteLearnSet/{id}")
    public String getDeleteLearnSet(@PathVariable("id") Long id, Principal principal)
    {
        if(learnSetService.isOwner(principal, id))
        {
            // delete all cards and corresponding cardFiles
            //TODO: Prüfen, ob JPA das nicht automatisch macht.
            accountService.deleteLearnSetAbosByLearnSetId(id);
            learnSetService.deleteLearnSet(id);
        }
        
        return "redirect:/learnSets";
    }
    
    @GetMapping("/editLearnSet/{id}")
    public String getEditLearnSet(@PathVariable("id") Long id, Principal principal, Model model)
    {
        
        LearnSet learnSet = learnSetService.getLearnSetByLearnSetId(id);
        Optional<Account> account = accountService.getAccount(principal);
        if(learnSet != null && account.isPresent() && learnSet.getOwner().equals(account.get()))
        {
            model.addAttribute("owner",account.get());
            model.addAttribute("learnSetOld",learnSet);
            
            return "editLearnSet";
            
        }
        
        return "redirect:/index";
    }
    
    @PostMapping("/updateLearnSet/{learnSetId}")
    public String postUpdateLearnSet(@PathVariable("learnSetId") Long learnSetId ,@ModelAttribute("learnSetOld") LearnSet learnSet ,Principal principal)
    {
        
        LearnSet learnSetOld = learnSetService.getLearnSetByLearnSetId(learnSetId);
        Optional<Account> account = accountService.getAccount(principal);
        if(account.isPresent() && learnSetService.accountIsAuthorized(principal, learnSetOld.getId()) && learnSetOld.getOwner().equals(account.get()))
        {
            if(learnSet != null && learnSet.getTitle() != null && learnSet.getDescription() != null && learnSet.getFaculty() != null && learnSet.getVisibility() != null)
            {
                learnSetOld.setTitle(learnSet.getTitle());
                learnSetOld.setDescription(learnSet.getDescription());
                learnSetOld.setFaculty(learnSet.getFaculty());
                learnSetOld.setVisibility(learnSet.getVisibility());
                learnSetService.saveLearnSet(learnSetOld);
            }
        }
        
        return "redirect:/cardOverview/" + learnSetId;
    }
    
    @GetMapping("/unfollowLearnSet/{followedLearnSetAboId}")
    public String getUnfollowedLearnSetAboId(@PathVariable("followedLearnSetAboId") Long followedLearnSetAboId, Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        Optional<LearnSetAbo> abo = learnSetAboRepository.findById(followedLearnSetAboId);
        if(account.isPresent() && abo.isPresent()){
            account.get().removeLearnSetAbo(abo.get());
            accountService.saveAccount(account.get());
//            abo.get().setLearnSet(null);
//            abo.get().getLearningSession().setCardStatusList(null);
//            learnSetAboRepository.save(abo.get());
            learnSetAboRepository.delete(abo.get());
        }
        
        return "redirect:/learnSets";
    }
    
    @GetMapping("/removeAccountFromAdminList/{learnSetId}/{accountId}")
    public String getRemoveAccountFromAdminList(@PathVariable("learnSetId") Long learnSetId,
                                                @PathVariable("accountId") Long accountId, Principal principal)
    {
        Optional<Account> owner = accountService.getAccount(principal);
        Optional<Account> delAdmin = accountService.getAccount(accountId);
        LearnSet learnSet = learnSetService.getLearnSetByLearnSetId(learnSetId);
        if(owner.isPresent() && learnSet != null && delAdmin.isPresent()  && owner.get().equals(learnSet.getOwner())){
            if(!owner.get().equals(delAdmin.get())){
            
                learnSet.removeAdmin(delAdmin.get());
                learnSetService.saveLearnSet(learnSet);
            }
        }
    
        return "redirect:/editLearnSet/" + learnSetId;
    }
    
    @GetMapping("/addAccountToAdminList/{learnSetId}/{friendId}")
    public String getAddAccountToAdminList(@PathVariable("learnSetId") Long learnSetId,
                                           @PathVariable("friendId") Long friendId,Principal principal)
    {
        Optional<Account> owner = accountService.getAccount(principal);
        Optional<Account> friend = accountService.getAccount(friendId);
        LearnSet learnSet = learnSetService.getLearnSetByLearnSetId(learnSetId);
    
        if (owner.isPresent() && friend.isPresent() && learnSet != null)
        {
            if (learnSet.getOwner().equals(owner.get()))
            {
                learnSet.addAdmin(friend.get());
                learnSetService.saveLearnSet(learnSet);
            }
        }
        return "redirect:/editLearnSet/" + learnSetId;
    }

}

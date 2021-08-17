package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.services.*;


import de.aicard.storages.CardStatusRepository;
import de.aicard.storages.LearnSetAboRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @Author Martin Kühlborn,Clemens Berger
 */
@Controller
public class LearnSetController
{
    private final AccountService accountService;

    private final LearnSetService learnSetService;


    private final CardService cardService;

    
    @Autowired
    public LearnSetAboRepository learnSetAboRepository;

    @Autowired
    public CardStatusRepository cardStatusRepository;
    
    @Autowired
    public LearnSetController(AccountService accountService, LearnSetService learnSetService, CardService cardService) {
        this.accountService = accountService;
        this.learnSetService = learnSetService;
        this.cardService = cardService;
    }

    /**
     * shows the createLearnset.html with a new learnSet
     * @param model
     * @return
     */
    @GetMapping("/createLearnset")
    public String getCreateLearnset(Model model)
    {
        model.addAttribute("newLearnset", new LearnSet());
        return "createLearnset";
    }

    /**
     * creates a new learnSet with the given Data on the logged in Account
     * @param newLearnset
     * @param model
     * @param principal
     * @return
     */
    @PostMapping("/createLearnset")
    public String postCreateLearnset(@ModelAttribute("newLearnset") LearnSet newLearnset, Model model, Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        if(account.isPresent()){
            LearnSet learnSet = learnSetService.createLearnSet(newLearnset,account.get());
            learnSetService.saveLearnSet(learnSet);
            return "redirect:cardOverview/" + learnSet.getId();
        }
    
        return "redirect:/login";
    }

    /**
     * get all learnSetabos of an account they are distinguished between just abos and learnsets with admin rights
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/learnSets")
    public String getLearnSets(Model model ,Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        // check if user is logged in -> else: send to Login
        if(account.isPresent())
        {
            //TODO: Das geht noch eleganter.
            List<LearnSetAbo> abos = account.get().getLearnsetAbos();
            List<LearnSetAbo> ownLearnSetAbos = new ArrayList<>();
            List<LearnSetAbo> favoriteLearnSetAbos = new ArrayList<>();

            for ( LearnSetAbo learnSetAbo : abos)
            {
                if(learnSetAbo.getLearnSet().getOwner().equals(account.get())){
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

    /**
     * shows some data of the learnSet and all cards of the learnset
     * this site gives the ability to add cards if the user is an admin
     * @param id
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("cardOverview/{id}")
    public String getCardOverview(@PathVariable Long id,Principal principal, Model model)
    {
        String filePath = "/getFile/";
        Optional<Account> account = accountService.getAccount(principal);
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(id);
        
        if (account.isPresent() && learnSet.isPresent() && learnSetService.accountIsAuthorized(account.get(), learnSet.get()))
        {
            // check if user has rights! to editn Learnset
            //zusätzlicher check auf den owner
            boolean isAdmin = learnSetService.isAdmin(account.get(),learnSet.get());
            boolean isOwner = learnSetService.isOwner(account.get(), learnSet.get());
            // TODO : check if the CardContentFile exists; what should we do if it doesnt?
            CardList cardList = learnSet.get().getCardList();
            if(cardList != null){
                model.addAttribute("isOwner", isOwner);
                model.addAttribute("learnSet", learnSet.get());
                cardList.setCardPath(filePath);
                model.addAttribute("cardList", cardList.getListOfCards());
                model.addAttribute("isAdmin", isAdmin);
                return "cardOverview";
            }
        }
        return "redirect:/index";
    }

    /**
     * ???????????????????????????????
     * @param fileName
     * @return
     * @throws IOException
     */
    // getImagesForLearnSet
    @GetMapping("/getFile/{fileName}")
    public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) throws IOException
    {
        File img = new File(System.getProperty("user.dir") + "\\cardFiles\\" + fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    /**
     * deletes the given card if user has rights when deleting a card all
     * learnSetAbos with the learnset which holds the card are updated
     * @param id
     * @param principal
     * @return
     */
    @GetMapping("/deleteCard/{id}")
    public String getDeleteCard(@PathVariable("id") Long id, Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        Long learnSetId = cardService.getLearnSetIdByCardId(id);
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(learnSetId);

        if(account.isPresent() && learnSet.isPresent() && learnSetService.isAdmin(account.get(), learnSet.get()))
        {
            Card card = cardService.getCardById(id);
            cardService.deleteCard(card);
            return "redirect:/cardOverview/" + learnSetId;
        }

        return "redirect:/index";

    }

    /**
     * deletes a learnSet if the user is the owner
     * all LearnSetAbos with this learnSet are deleted too
     * @param id
     * @param principal
     * @return
     */
    @GetMapping("/deleteLearnSet/{id}")
    public String getDeleteLearnSet(@PathVariable("id") Long id, Principal principal)
    {
        Optional<Account> account = accountService.getAccount(principal);
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(id);
        
        if(account.isPresent() && learnSet.isPresent() && learnSetService.isOwner(account.get(), learnSet.get()))
        {
            // delete all cards and corresponding cardFiles
            //TODO: Prüfen, ob JPA das nicht automatisch macht.
            accountService.deleteLearnSetAbosByLearnSetId(id);
            learnSetService.deleteLearnSet(id);
        }
        
        return "redirect:/learnSets";
    }

    /**
     * shows the site to edit the learnSet data if the user is the owner
     * @param id
     * @param principal
     * @param model
     * @return
     */
    @GetMapping("/editLearnSet/{id}")
    public String getEditLearnSet(@PathVariable("id") Long id, Principal principal, Model model)
    {
        
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(id);
        Optional<Account> account = accountService.getAccount(principal);
        if(learnSet.isPresent() && account.isPresent() && learnSet.get().getOwner().equals(account.get()))
        {
            model.addAttribute("owner",account.get());
            model.addAttribute("learnSetOld",learnSet.get());
            
            return "editLearnSet";
            
        }
        
        return "redirect:/index";
    }

    /**
     * edits data of the learnSet if the user is the owner
     * @param learnSetId
     * @param learnSet
     * @param principal
     * @return
     */
    @PostMapping("/updateLearnSet/{learnSetId}")
    public String postUpdateLearnSet(@PathVariable("learnSetId") Long learnSetId ,@ModelAttribute("learnSetOld") LearnSet learnSet ,Principal principal)
    {
        
        Optional<LearnSet> learnSetOld = learnSetService.getLearnSetByLearnSetId(learnSetId);
        Optional<Account> account = accountService.getAccount(principal);
        if(account.isPresent() && learnSetOld.isPresent() && learnSetService.accountIsAuthorized(account.get(), learnSetOld.get()) && learnSetOld.get().getOwner().equals(account.get()))
        {
            if(learnSet != null && learnSet.getTitle() != null && learnSet.getDescription() != null && learnSet.getFaculty() != null && learnSet.getVisibility() != null)
            {
                learnSetOld.get().setTitle(learnSet.getTitle());
                learnSetOld.get().setDescription(learnSet.getDescription());
                learnSetOld.get().setFaculty(learnSet.getFaculty());
                learnSetOld.get().setVisibility(learnSet.getVisibility());
                learnSetService.saveLearnSet(learnSetOld.get());
            }
        }
        
        return "redirect:/cardOverview/" + learnSetId;
    }

    /**
     * removes the learnSetAbo from the list of the account
     * owners and admins can't deabo!!!
     * @param followedLearnSetAboId
     * @param principal
     * @return
     */
    @GetMapping("/unfollowLearnSet/{followedLearnSetAboId}")
    public String getUnfollowedLearnSetAboId(@PathVariable("followedLearnSetAboId") Long followedLearnSetAboId, Principal principal)
    {
        //TODO abos of admins shoud have an unfollow button
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

    /**
     * removes an account from the adminlist only the owner can add and remove admins
     * @param learnSetId
     * @param accountId
     * @param principal
     * @return
     */
    @GetMapping("/removeAccountFromAdminList/{learnSetId}/{accountId}")
    public String getRemoveAccountFromAdminList(@PathVariable("learnSetId") Long learnSetId,
                                                @PathVariable("accountId") Long accountId, Principal principal)
    {
        Optional<Account> owner = accountService.getAccount(principal);
        Optional<Account> delAdmin = accountService.getAccount(accountId);
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(learnSetId);
        if(owner.isPresent() && learnSet != null && delAdmin.isPresent()  && owner.get().equals(learnSet.get().getOwner())){
            if(!owner.get().equals(delAdmin.get())){
            
                learnSet.get().removeAdmin(delAdmin.get());
                learnSetService.saveLearnSet(learnSet.get());
            }
        }
    
        return "redirect:/editLearnSet/" + learnSetId;
    }

    /**
     * adds an Account to the adminList only the owner can add and remove admins
     * @param learnSetId
     * @param friendId
     * @param principal
     * @return
     */
    @GetMapping("/addAccountToAdminList/{learnSetId}/{friendId}")
    public String getAddAccountToAdminList(@PathVariable("learnSetId") Long learnSetId,
                                           @PathVariable("friendId") Long friendId,Principal principal)
    {
        Optional<Account> owner = accountService.getAccount(principal);
        Optional<Account> friend = accountService.getAccount(friendId);
        Optional<LearnSet> learnSet = learnSetService.getLearnSetByLearnSetId(learnSetId);
    
        if (owner.isPresent() && friend.isPresent() && learnSet.isPresent())
        {
            if (learnSet.get().getOwner().equals(owner.get()))
            {
                learnSet.get().addAdmin(friend.get());
                learnSetService.saveLearnSet(learnSet.get());
            }
        }
        return "redirect:/editLearnSet/" + learnSetId;
    }

}

package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.services.AccountService;
import de.aicard.services.CardService;
import de.aicard.services.LearnSetAboService;
import de.aicard.services.LearnSetService;


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

    @Autowired
    public LearnSetController(AccountService accountService, LearnSetService learnSetService, LearnSetAboService learnSetAboService, CardService cardService) {
        this.accountService = accountService;
        this.learnSetService = learnSetService;
        this.learnSetAboService = learnSetAboService;
        this.cardService = cardService;
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
        if(accountService.isLoggedIn(principal)){

            Long id = learnSetService.createLearnSet(newLearnset, principal);
//TODO: ERfolg prüfen
            return "redirect:cardOverview/" + id;
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
            List<LearnSetAbo> abos = learnSetAboService.getLearnSetAbos(principal);
            List<LearnSetAbo> ownLearnSetAbos = new ArrayList<LearnSetAbo>();
            List<LearnSetAbo> favoriteLearnSetAbos = new ArrayList<LearnSetAbo>();

            for ( LearnSetAbo learnSetAbo : abos)
            {
                if(learnSetAbo.getLearnSet().getAdminList().contains(accountService.getAccount(principal))){
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
        // ach scheißdrauf
        if (learnSetService.accountIsAuthorized(principal, id))
        {
            // TODO : check if the CardContentFile exists; what should we do if it doesnt?
            CardList cardList = learnSetService.getCardList(id);
            if(cardList != null){
                model.addAttribute("learnSet", learnSetService.getLearnSetByLearnSetId(id));
                List<Card> listOfCards = cardService.setCardData(filePath, cardList);
                model.addAttribute("cardList", listOfCards);
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
        Long learnSetId =  learnSetService.getLearnSetIdByCardId(id);
        if(learnSetService.isAdmin(principal, learnSetId))
        {
            cardService.deleteCard(id);
//
        }
        return "redirect:/cardOverview/" + learnSetId;
    }
    
    @GetMapping("/editCard/{id}")
    public String getEditCard(@PathVariable("id") Long id, Principal principal, Model model)
    {
        return "editCard";
    }
    
    @GetMapping("/deleteLearnSet/{id}")
    public String getDeleteLearnSet(@PathVariable("id") Long id, Principal principal)
    {
        if(learnSetService.isAdmin(principal, id))
        {
            
            // delete all cards and corresponding cardFiles
            //TODO: Prüfen, ob JPA das nicht automatisch macht.
            cardService.deleteAllCardsFromLearnSet(id);
            learnSetService.deleteAllAccountReferences(id);
            learnSetService.deleteLearnSet(id);
        }
        
        return "redirect:/learnSets";
    }
    
    @GetMapping("/editLearnSet/{id}")
    public String getEditLearnSet(@PathVariable("id") Long id, Principal principal, Model model)
    {
        
        return "editLearnSet";
    }
    

}

package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.CardListRepository;
import de.aicard.storages.CardRepository;
import de.aicard.storages.LearnSetRepository;


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


@Controller
public class LearnSetController
{
    @Autowired
    LearnSetRepository learnSetRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;
    
    @Autowired
    CardListRepository cardListRepository;



    @GetMapping("/createLearnset")
    public String getCreateLearnset(Model model)
    {
        model.addAttribute("newLearnset", new LearnSet());
        return "createLearnset";
    }

    @PostMapping("/createLearnset")
    public String postCreateLearnset(@ModelAttribute("newLearnset") LearnSet newLearnset, Model model, Principal principal)
    {
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        
        // check if user is logged in -> else: send to Login
        if(account.isPresent())
        {
                //System.out.println(account.get());
                newLearnset.setOwner(account.get());
                newLearnset.setAdminList(new ArrayList<>());
                newLearnset.addAdmin(account.get());
                newLearnset.setCardList(new CardList());
                newLearnset.setCommentList(new ArrayList<>());
    
                learnSetRepository.save(newLearnset);
    
                return "redirect:cardOverview/" + newLearnset.getId();
        }
    
        return "redirect:/login";
    }

    @GetMapping("/learnSets")
    public String getLearnSets(Model model ,Principal principal)
    {
        // check if user is logged in -> else: send to Login
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        if(account.isPresent())
        {
            List<LearnSet> learnSetListAdmin = learnSetRepository.findAdminLearnsets(account.get().getId());
            List<LearnSet> learnSetListFollowed = learnSetRepository.findFollwedLearnsets(account.get().getId());
            
            model.addAttribute("learnSetListAdmin", learnSetListAdmin);
            model.addAttribute("learnSetListFollowed", learnSetListFollowed);
        }
        return "learnSets";
    }

    @GetMapping("cardOverview/{id}")
    public String getCardOverview(@PathVariable Long id,Principal principal, Model model)
    {
        Optional<LearnSet> learnSet =  learnSetRepository.findById(id);
        
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        //System.out.println("account Faculty " + accounts.get(0).getFaculty());
        //System.out.println("Set Faculty " + learnSet.get().getFaculty());
        
        // ach scheißdrauf
        if (learnSet.isPresent() && account.isPresent() && (
            learnSet.get().getVisibility() == Visibility.PUBLIC ||
            learnSet.get().getVisibility() == Visibility.PRIVATE && learnSet.get().getAdminList().contains(account.get()) ||
            learnSet.get().getVisibility() == Visibility.PROTECTED && account.get().getFaculty() == learnSet.get().getFaculty()))
        {
            // TODO : check if the CardContentFile exists; what should we do if it doesnt?
            model.addAttribute("learnSet", learnSet.get());
            List<Card> cardListList = learnSet.get().getCardList().getListOfCards();
            String filePath = "/learnSetImage/";
            for ( Card card : cardListList)
            {
                if(card.getCardFront().getType() != DataType.TextFile)
                {
                    card.getCardFront().setData(filePath + card.getCardFront().getData());
                }


                if(card.getCardBack().getType() != DataType.TextFile)
                {
                    card.getCardBack().setData(filePath + card.getCardBack().getData());
                }
            }
            
            model.addAttribute("cardList", cardListList);
            
            System.out.println(learnSetRepository.findById(id).get().getTitle());

            return "cardOverview";
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
        Optional<Card> card = cardRepository.findById(id);
        Optional<LearnSet> learnSet =  learnSetRepository.getLearnSetByCardId(card.get().getId());
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        
        if(learnSet.isPresent() && account.isPresent() && learnSet.get().getAdminList().contains(account.get()))
        {
            
            
            if(card.get().getCardFront().getType() != DataType.TextFile)
            {
                // TODO : sollte das in ein TryCatch oder so ähnlich?
                File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.get().getCardFront().getData());
                file.delete();
            }
            if(card.get().getCardBack().getType() != DataType.TextFile)
            {
                // TODO : sollte das in ein TryCatch oder so ähnlich?
                File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.get().getCardBack().getData());
                file.delete();
            }
    
            learnSet.get().getCardList().removeFromList(card.get());
            cardRepository.delete(card.get());
//            cardRepository.deleteById(card.get().getId(),card.get().getCardFront().getId(), card.get().getCardBack().getId());
        }
        return "redirect:/cardOverview/" + learnSet.get().getId();
    }
    
    @GetMapping("/editCard/{id}")
    public String getEditCard(@PathVariable("id") Long id, Principal principal, Model model)
    {
        return "editCard";
    }
    
    @GetMapping("/deleteLearnSet/{id}")
    public String getDeleteLearnSet(@PathVariable("id") Long id, Principal principal)
    {
        Optional<LearnSet> learnSet =  learnSetRepository.findById(id);
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
    
        if(learnSet.isPresent() && account.isPresent() && learnSet.get().getAdminList().contains(account.get()))
        {
            
            // delete each cardFile from cardFile-Folder
            for (Card card : learnSet.get().getCardList().getListOfCards())
            {
                if(card.getCardFront().getType() != DataType.TextFile)
                {
                    // TODO : sollte das in ein TryCatch oder so ähnlich?
                    File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.getCardFront().getData());
                    file.delete();
                }
                if(card.getCardBack().getType() != DataType.TextFile)
                {
                    // TODO : sollte das in ein TryCatch oder so ähnlich?
                    File file = new File(System.getProperty("user.dir") + "\\cardFiles\\" + card.getCardBack().getData());
                    file.delete();
                }
            }
            
            // delete every reference that exists to this learnset
            List<Account> accountList = accountRepository.findAll();
            for (Account account1 : accountList)
            {
                if(account1.getOwnLearnSets().contains(learnSet))
                {
                    account1.getOwnLearnSets().remove(learnSet);
                }
                if(account1.getFavoriteLearnSets().contains(learnSet))
                {
                    account1.getFavoriteLearnSets().remove(learnSet);
                }
                // TODO : falls ein LearnSetsAbo existiert, lösche diese referenz auch!
            }
            learnSet.get().setOwner(null);
            learnSetRepository.delete(learnSet.get());
        }
        
        return "redirect:/learnSets";
    }
    
    @GetMapping("/editLearnSet/{id}")
    public String getEditLearnSet(@PathVariable("id") Long id, Principal principal, Model model)
    {
        
        return "editLearnSet";
    }
    

}

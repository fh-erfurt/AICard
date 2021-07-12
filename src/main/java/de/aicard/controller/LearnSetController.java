package de.aicard.controller;

import de.aicard.config.Session;
import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public String getCreateLearnset(HttpServletRequest request, HttpServletResponse response, Model model)
    {
        // check if user is logged in -> else: send to Login
        String sessionString = Session.getSessionValue(request.getCookies());
        if(sessionString == null)
        {
            return "redirect:/login";
        }
        
        model.addAttribute("newLearnset", new LearnSet());
        return "createLearnset";
    }

    @PostMapping("/createLearnset")
    public String postCreateLearnset(@ModelAttribute("newLearnset") LearnSet newLearnset, Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String accountID = Session.getSessionValue(request.getCookies());
        // check if user is logged in -> else: send to Login
        if(accountID != null)
        {
            List<Account> accountList = accountRepository.findAllById(Long.parseLong(accountID));
            if (accountList.size() == 1)
            {
                System.out.println(accountList.get(0).toString());
                newLearnset.setOwner(accountList.get(0));
                newLearnset.setAdminList(new ArrayList<>());
                newLearnset.addAdmin(accountList.get(0));
                newLearnset.setCardList(new CardList());
                newLearnset.setCommentList(new ArrayList<>());
    
                learnSetRepository.save(newLearnset);
    
                return "redirect:cardOverview/" + newLearnset.getId();
            }
        }
    
        return "redirect:/login";
    }

    @GetMapping("/learnSets")
    public String getLearnSets(Model model ,HttpServletRequest request,HttpServletResponse response)
    {
        // check if user is logged in -> else: send to Login
        String accountID = Session.getSessionValue(request.getCookies());
        
        if(accountID != null)
        {
            List<Account> accountList = accountRepository.findAllById(Long.parseLong(accountID));
            if (accountList.size() == 1)
            {
                List<LearnSet> learnSetListAdmin = learnSetRepository.findAdminLearnsets(Long.parseLong(accountID));
                List<LearnSet> learnSetListFollowed = learnSetRepository.findFollwedLearnsets(Long.parseLong(accountID));

                model.addAttribute("learnSetListAdmin", learnSetListAdmin);
                model.addAttribute("learnSetListFollowed", learnSetListFollowed);
            }
        }
        return "learnSets";
    }

    
//    @GetMapping("cardOverview/{id}")
//    @ResponseBody
//    public String getCardoverviewRest(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request)
//    {
//        JSONArray jsonArray = new JSONArray();
//
//        Optional<LearnSet> learnSet = learnSetRepository.findById(id);
//        if(learnSet.get() != null)
//        {
//            for ( Card card :  learnSet.get().getCardList().getListOfCards())
//            {
//                String path = System.getProperty("user.dir");
//                path = path + "\\\\cardFiles";
//                path = path.replace("\\", "\\\\");
//                System.out.println(path);
////                jsonArray.add(card);
//            }
//
//        }
//        return jsonArray.toString();
//    }

    
    // TODO : work in progress; problems showing each File of each Card of the LearnSet
    @GetMapping("cardOverview/{id}")
    public String getCardOverview(@PathVariable Long id,HttpServletRequest request,HttpServletResponse response, Model model)
    {
        Optional<LearnSet> learnSet =  learnSetRepository.findById(id);
        String cookieValue = Session.getSessionValue(request.getCookies());
        if(cookieValue == null){
            return "redirect:/login";
        }
        List<Account> accounts = accountRepository.findAllById(Long.parseLong(cookieValue));
        //System.out.println("account Faculty " + accounts.get(0).getFaculty());
        //System.out.println("Set Faculty " + learnSet.get().getFaculty());
        
        // ach scheißdrauf
        if (learnSet.isPresent() && (
            learnSet.get().getVisibility() == Visibility.PUBLIC ||
            learnSet.get().getVisibility() == Visibility.PRIVATE && learnSet.get().getAdminList().contains(accounts.get(0)) ||
            learnSet.get().getVisibility() == Visibility.PROTECTED && accounts.get(0).getFaculty() == learnSet.get().getFaculty()))
        {
            // TODO : check if the CardContentFile exists; what should I do if it doesnt?
            model.addAttribute("learnSet", learnSetRepository.findById(id));
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
        else
        {
            return "redirect:/index";
        }
    }
    
    // getImagesForLearnSet
    @GetMapping("/learnSetImage/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String fileName) throws IOException
    {
        File img = new File(System.getProperty("user.dir") + "\\cardFiles\\" + fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }
    


}

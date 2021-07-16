package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AddCardController
{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LearnSetRepository learnSetRepository;
    
    
    
    
    
    @GetMapping("/addCard/{learnSetID}")
    public String getAddCard(@PathVariable Long learnSetID, Principal principal, Model model)
    {
        Optional<LearnSet> learnSet = learnSetRepository.findById(learnSetID);
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        if(learnSet.isPresent() && account.isPresent())
        {
            if (learnSet.get().getAdminList().contains(account.get()))
            {
                model.addAttribute("learnSetID", learnSetID);
                return "addCard";
            }
        }
        return "redirect:/index";
    }
    
    // --- we offer the possibility for all files to be send but only save those that are selected by the FileTypeSelector
    @PostMapping("/addCard/{learnSetID}")
    @ResponseBody
    public ModelAndView postAddCard(
            
            @PathVariable Long learnSetID,Principal principal, Model model,
            @RequestParam("cardFrontType") String cardFrontType,
            @RequestParam(value = "cardFrontTextFileTitle", required = false) String cardFrontTextFileTile, @RequestParam(value = "cardFrontTextFileInput", required = false) String cardFrontTextFileInput,
            @RequestParam(value = "cardFrontPictureFileTitle", required = false) String cardFrontPictureFileTitle, @RequestParam(value = "cardFrontPictureFileInput", required = false) MultipartFile cardFrontPictureFileInput,
            @RequestParam(value = "cardFrontVideoFileTitle", required = false) String cardFrontVideoFileTitle, @RequestParam(value = "cardFrontVideoFileInput", required = false) MultipartFile cardFrontVideoFileInput,
            @RequestParam(value = "cardFrontAudioFileTitle", required = false) String cardFrontAudioFileTitle, @RequestParam(value = "cardFrontAudioFileInput", required = false) MultipartFile cardFrontAudioFileInput,

            @RequestParam("cardBackType") String cardBackType,
            @RequestParam(value = "cardBackTextFileTitle" , required = false) String cardBackTextFileTitle, @RequestParam(value = "cardBackTextFileInput", required = false) String cardBackTextFileInput,
            @RequestParam(value = "cardBackPictureFileTitle", required = false) String cardBackPictureFileTitle, @RequestParam(value = "cardBackPictureFileInput", required = false) MultipartFile cardBackPictureFileInput,
            @RequestParam(value = "cardBackVideoFileTitle", required = false) String cardBackVideoFileTitle, @RequestParam(value = "cardBackVideoFileInput", required = false) MultipartFile cardBackVideoFileInput,
            @RequestParam(value = "cardBackAudioFileTitle", required = false) String cardBackAudioFileTitle, @RequestParam(value = "cardBackAudioFileInput", required = false) MultipartFile cardBackAudioFileInput
    )throws IOException
    {
        // if cardFiles Folder doesnt exist, create it!
        if(! Files.exists(Path.of(System.getProperty("user.dir") + "\\cardFiles")))
        {
            Files.createDirectory(Path.of(System.getProperty("user.dir") + "\\cardFiles"));
        }
        
        // --- Logic start ---
        ModelAndView modelAndView = new ModelAndView();
        List<String> errors = new ArrayList<>();
        Optional<LearnSet> learnSet = learnSetRepository.findById(learnSetID);
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        if(learnSet.isPresent() && account.isPresent())
        {
            // TODO : simplify this into a loop or a Service with a loop -> no code repetition
            if (learnSet.get().getAdminList().contains(account.get()))
            {
                // we are here if the learnSet exists and the Owner or an Admin is logged in
                Card card = new Card();
                CardContent cardContentFront = new CardContent();
                CardContent cardContentBack = new CardContent();
                String cardFrontFilePath = System.getProperty("user.dir") + "\\cardFiles\\";
//                String cardFrontFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\learnSetImages\\";

    
                // --- --- -- --- ---
                // --- Card Front ---
                // --- --- -- --- ---
                if (cardFrontType.equals(DataType.PictureFile.name()))
                {
                    cardContentFront.setTitle(cardFrontPictureFileTitle);
                    cardContentFront.setType(DataType.PictureFile);
    
                    if (cardFrontPictureFileInput != null && ! cardFrontPictureFileInput.isEmpty())
                    {
                        // file exists
                        String fileName = System.currentTimeMillis() + "_" + cardFrontPictureFileInput.getOriginalFilename();
                        cardFrontFilePath = cardFrontFilePath + fileName;
                        cardFrontFilePath = cardFrontFilePath.replace("\\", "\\\\");
                        File newFile = new File(cardFrontFilePath);
                        cardFrontPictureFileInput.transferTo(newFile);
    
                        String mimetype = new MimetypesFileTypeMap().getContentType(newFile);
                        String fileType = mimetype.split("/")[0];
                        if (fileType.equals("image"))
                        {
                            cardContentFront.setData(fileName);
    
                        }
                        else
                        {
                            newFile.delete();
                            errors.add("Falscher Dateityp für PictureFile");
                        }
                    }
                    else
                    {
                        errors.add("Keine Datei hochgeladen");
                    }
                }
                //Audio
                //TODO: correct paths for audio front
                //cardFrontFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\audio\\learnSetAudio\\";
                if (cardFrontType.equals(DataType.AudioFile.name()))
                {
                    cardContentFront.setTitle(cardFrontAudioFileTitle);
                    cardContentFront.setType(DataType.AudioFile);
    
                    if (cardFrontAudioFileInput != null && ! cardFrontAudioFileInput.isEmpty())
                    {
                        // file exists
                        String fileName = System.currentTimeMillis() + "_" + cardFrontAudioFileInput.getOriginalFilename();
                        cardFrontFilePath = cardFrontFilePath + fileName;
                        cardFrontFilePath = cardFrontFilePath.replace("\\", "\\\\");
                        File newFile = new File(cardFrontFilePath);
                        cardFrontAudioFileInput.transferTo(newFile);
                        cardContentFront.setData(fileName);
    
                        // todo: not working for type audio
                        String mimetype = new MimetypesFileTypeMap().getContentType(newFile); //  keine ahnung was das hier soll
                        //System.out.println("mimetype "+mimetype);
                        //String fileType = mimetype.split("/")[0];

                        //System.out.println("Filetype Audio "+fileType);
                        String fileType = cardFrontAudioFileInput.getContentType().split("/")[0];


                        if(fileType.equals("audio"))
                        {
                            System.out.println("Audio erkannt "+cardFrontAudioFileInput.getOriginalFilename());
                            cardContentFront.setData(fileName);
                            // jetzt wird die datei gespeichert oder was
                        }
                        else
                        {
                            newFile.delete();
                            errors.add("Falscher Dateityp für Audio");
                        }
                    }
                    else
                    {
                        errors.add("Keine Datei hochgeladen");
                    }
                }
                //Video
                //cardFrontFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\video\\learnSetVideo\\";
                if (cardFrontType.equals(DataType.VideoFile.name()))
                {
                    cardContentFront.setTitle(cardFrontVideoFileTitle);
                    cardContentFront.setType(DataType.VideoFile);
    
                    if (cardFrontVideoFileInput != null && ! cardFrontVideoFileInput.isEmpty())
                    {
                        // file exists
                        String fileName = System.currentTimeMillis() + "_" + cardFrontVideoFileInput.getOriginalFilename();
                        cardFrontFilePath = cardFrontFilePath + fileName;
                        cardFrontFilePath = cardFrontFilePath.replace("\\", "\\\\");
                        File newFile = new File(cardFrontFilePath);
                        cardFrontVideoFileInput.transferTo(newFile);
    
                        cardContentFront.setData(fileName);
    
                        // todo: not working for type video
                        String mimetype = new MimetypesFileTypeMap().getContentType(newFile);
                        //String fileType = mimetype.split("/")[0]; // ?? warum machst du das hier
                        System.out.println(cardFrontVideoFileInput.getContentType());
                        String fileType = cardFrontVideoFileInput.getContentType().split("/")[0];
                        if(fileType.equals("video"))
                        {
                            System.out.println("Video erkannt "+cardFrontVideoFileInput.getOriginalFilename());
                            cardContentFront.setData(fileName);
                        }
                        else
                        {
                            newFile.delete();
                            errors.add("Falscher Dateityp für PictureFile");
                        }
                    }
                    else
                    {
                        errors.add("Keine Datei hochgeladen");
                    }
                }
                //Text
                if (cardFrontType.equals(DataType.TextFile.name()))
                {
                    if (cardFrontTextFileInput != null && !cardFrontTextFileInput.isEmpty())
                    {
                        cardContentFront.setTitle(cardFrontTextFileTile);
                        cardContentFront.setData(cardFrontTextFileInput);
                        cardContentFront.setType(DataType.TextFile);
                    }
                    else
                    {
                        errors.add("Keine eingaben ins Textfeld erkannt.");
                    }
                }
    
                // --- --- -- --- ---
                // --- Card Back ---
                // --- --- -- --- ---
                String cardBackFilePath = System.getProperty("user.dir") + "\\cardFiles\\";
//                String cardBackFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\learnSetImages\\";
                if (cardBackType.equals(DataType.PictureFile.name()))
                {
                    cardContentBack.setTitle(cardBackPictureFileTitle);
                    cardContentBack.setType(DataType.PictureFile);
    
                    if (cardBackPictureFileInput != null && ! cardBackPictureFileInput.isEmpty())
                    {
                        // file exists
                        String fileName = System.currentTimeMillis() + "_" + cardBackPictureFileInput.getOriginalFilename();
                        cardBackFilePath = cardBackFilePath + fileName;
                        cardBackFilePath = cardBackFilePath.replace("\\", "\\\\");
                        File newFile = new File(cardBackFilePath);
                        cardBackPictureFileInput.transferTo(newFile);
    
                        String mimetype = new MimetypesFileTypeMap().getContentType(newFile);
                        String fileType = mimetype.split("/")[0];
                        if (fileType.equals("image"))
                        {

                            cardContentBack.setData(fileName);
    
                        }
                        else
                        {
                            newFile.delete();
                            errors.add("Falscher Dateityp für PictureFile");
                        }
                    }
                    else
                    {
                        errors.add("Keine Datei hochgeladen");
                    }
                }
                //cardBackFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\audio\\learnSetAudio\\";
                if (cardBackType.equals(DataType.AudioFile.name()))
                {
                    cardContentBack.setTitle(cardBackAudioFileTitle);
                    cardContentBack.setType(DataType.AudioFile);
    
                    if (cardBackAudioFileInput != null && ! cardBackAudioFileInput.isEmpty())
                    {
                        // file exists
                        String fileName = System.currentTimeMillis() + "_" + cardBackAudioFileInput.getOriginalFilename();
                        cardBackFilePath = cardBackFilePath + fileName;
                        cardBackFilePath = cardBackFilePath.replace("\\", "\\\\");
                        File newFile = new File(cardBackFilePath);
                        cardBackAudioFileInput.transferTo(newFile);
    
                        cardContentBack.setData(fileName);

                        String mimetype = new MimetypesFileTypeMap().getContentType(newFile);
                        //String fileType = mimetype.split("/")[0];
                        String fileType = cardBackAudioFileInput.getContentType().split("/")[0];
                        if(fileType.equals("audio"))
                        {
                            System.out.println("Audio erkannt "+cardBackAudioFileInput.getOriginalFilename());
                            cardContentBack.setData(fileName);
                        }
                        else
                        {
                            newFile.delete();
                            errors.add("Falscher Dateityp für PictureFile");
                        }
                    }
                    else
                    {
                        errors.add("Keine Datei hochgeladen");
                    }
    
                }
                // Video Back
                //cardBackFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\video\\learnSetVideo\\";
                if (cardBackType.equals(DataType.VideoFile.name()))
                {
                    cardContentBack.setTitle(cardBackVideoFileTitle);
                    cardContentBack.setType(DataType.VideoFile);
    
                    if (cardBackVideoFileInput != null && ! cardBackVideoFileInput.isEmpty())
                    {
                        // file exists
                        String fileName = System.currentTimeMillis() + "_" + cardBackVideoFileInput.getOriginalFilename();
                        cardBackFilePath = cardBackFilePath + fileName;
                        cardBackFilePath = cardBackFilePath.replace("\\", "\\\\");
                        File newFile = new File(cardBackFilePath);
                        cardBackVideoFileInput.transferTo(newFile);
    
                        cardContentBack.setData(fileName);

                        String mimetype = new MimetypesFileTypeMap().getContentType(newFile);
                        //String fileType = mimetype.split("/")[0];
                        String fileType = cardBackVideoFileInput.getContentType().split("/")[0];
                        //System.out.println(fileType);
                        if(fileType.equals("video"))
                        {
                            System.out.println("Video erkannt "+cardBackVideoFileInput.getOriginalFilename());
                            cardContentBack.setData(fileName);
                        }
                        else
                        {
                            newFile.delete();
                            errors.add("Falscher Dateityp für PictureFile");
                        }
                    }
                    else
                    {
                        errors.add("Keine Datei hochgeladen");
                    }
                }
    
    
                if (cardBackType.equals(DataType.TextFile.name()))
                {
                    if(cardBackTextFileInput != null && !cardBackTextFileInput.isEmpty())
                    {
                        cardContentBack.setTitle(cardBackTextFileTitle);
                        cardContentBack.setData(cardBackTextFileInput);
                        cardContentBack.setType(DataType.TextFile);
                    }
                    else
                    {
                        errors.add("Keine eingaben ins Textfeld erkannt.");
                    }
                }
                
                
                if(errors.size() == 0)
                {
                    card.setCardFront(cardContentFront);
                    card.setCardBack(cardContentBack);
                    learnSet.get().getCardList().addToList(card);
                    learnSetRepository.save(learnSet.get());
                    
                    modelAndView.setViewName("redirect:/cardOverview/" + learnSetID);
                    return modelAndView;
                }
            }
        }
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }
    
}

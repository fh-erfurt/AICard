package de.aicard.controller;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardContent;
import de.aicard.domains.enums.DataType;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.services.AccountService;
import de.aicard.services.CardService;
import de.aicard.services.LearnSetService;
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

@Controller
public class AddCardController {
    private final LearnSetService learnSetService;
    private final CardService cardService;
    private final AccountService accountService;

    @Autowired
    public AddCardController(LearnSetService learnSetService, CardService cardService, AccountService accountService) {
        this.learnSetService = learnSetService;
        this.cardService = cardService;
        this.accountService = accountService;
    }

    @GetMapping("/addCard/{learnSetID}")
    public String getAddCard(@PathVariable Long learnSetID, Principal principal, Model model) {
        if (learnSetService.accountIsAuthorized(principal, learnSetID)) {
            model.addAttribute("learnSetID", learnSetID);
            return "addCard";
        }
        return "redirect:/index";
    }

    // --- we offer the possibility for all files to be send but only save those that are selected by the FileTypeSelector
    @PostMapping("/addCard/{learnSetID}")
    @ResponseBody
    public ModelAndView postAddCard(

            @PathVariable Long learnSetID, Principal principal, Model model,
            @RequestParam("cardFrontType") String cardFrontType,
            @RequestParam(value = "cardFrontTextFileTitle", required = false) String cardFrontTextFileTile, @RequestParam(value = "cardFrontTextFileInput", required = false) String cardFrontTextFileInput,
            @RequestParam(value = "cardFrontPictureFileTitle", required = false) String cardFrontPictureFileTitle, @RequestParam(value = "cardFrontPictureFileInput", required = false) MultipartFile cardFrontPictureFileInput,
            @RequestParam(value = "cardFrontVideoFileTitle", required = false) String cardFrontVideoFileTitle, @RequestParam(value = "cardFrontVideoFileInput", required = false) MultipartFile cardFrontVideoFileInput,
            @RequestParam(value = "cardFrontAudioFileTitle", required = false) String cardFrontAudioFileTitle, @RequestParam(value = "cardFrontAudioFileInput", required = false) MultipartFile cardFrontAudioFileInput,

            @RequestParam("cardBackType") String cardBackType,
            @RequestParam(value = "cardBackTextFileTitle", required = false) String cardBackTextFileTitle, @RequestParam(value = "cardBackTextFileInput", required = false) String cardBackTextFileInput,
            @RequestParam(value = "cardBackPictureFileTitle", required = false) String cardBackPictureFileTitle, @RequestParam(value = "cardBackPictureFileInput", required = false) MultipartFile cardBackPictureFileInput,
            @RequestParam(value = "cardBackVideoFileTitle", required = false) String cardBackVideoFileTitle, @RequestParam(value = "cardBackVideoFileInput", required = false) MultipartFile cardBackVideoFileInput,
            @RequestParam(value = "cardBackAudioFileTitle", required = false) String cardBackAudioFileTitle, @RequestParam(value = "cardBackAudioFileInput", required = false) MultipartFile cardBackAudioFileInput
    ) throws IOException {
        // if cardFiles Folder doesnt exist, create it!
        if (!Files.exists(Path.of(System.getProperty("user.dir") + "\\cardFiles"))) {
            Files.createDirectory(Path.of(System.getProperty("user.dir") + "\\cardFiles"));
        }

        // --- Logic start ---
        ModelAndView modelAndView = new ModelAndView();
        List<String> errors = new ArrayList<>();
        Card newCard = null;

        // TODO : überprüfe bei allen LernSet Änderungen ob der Account darauf zugriff hat
        LearnSet learnSet = learnSetService.getLearnSetByLearnSetId(learnSetID);
        if (learnSetService.accountIsAuthorized(principal, learnSetID) && learnSet != null && learnSet.getAdminList().contains(accountService.getAccount(principal))) {
            // we are here if the learnSet exists and the Owner or an Admin is logged in
            String cardFrontTitel = cardService.getCorrectTitle(cardFrontType, cardFrontPictureFileTitle,
                    cardFrontTextFileTile, cardFrontAudioFileTitle,
                    cardFrontAudioFileTitle);
            String cardBackTitel = cardService.getCorrectTitle(cardBackType, cardBackPictureFileTitle,
                    cardBackTextFileTitle, cardBackAudioFileTitle,
                    cardBackAudioFileTitle);

            //TODO: Doppelter Code kann noch verbessert werden, wenn Zeit
            try {
                if (cardFrontType.equals(DataType.TextFile.name())) {

                    String cardFrontInput = cardFrontTextFileInput;
                    if (cardBackType.equals(DataType.TextFile.name())) {
                        String cardBackInput = cardBackTextFileInput;
                        newCard = cardService.addNewCard(cardFrontType, cardFrontTitel, cardFrontInput,
                                cardBackType, cardBackTitel, cardBackInput);

                    } else {
                        MultipartFile cardBackInput = cardService.getCorrectInput(cardBackType, cardBackVideoFileInput,
                                cardBackPictureFileInput, cardBackAudioFileInput);
                        newCard = cardService.addNewCard(cardFrontType, cardFrontTitel, cardFrontInput,
                                cardBackType, cardBackTitel, cardBackInput);
                    }
                } else {
                    MultipartFile cardFrontInput = cardService.getCorrectInput(cardFrontType, cardFrontVideoFileInput,
                            cardFrontPictureFileInput, cardFrontAudioFileInput);
                    if (cardBackType.equals(DataType.TextFile.name())) {
                        String cardBackInput = cardBackTextFileInput;
                        newCard = cardService.addNewCard(cardFrontType, cardFrontTitel, cardFrontInput,
                                cardBackType, cardBackTitel, cardBackInput);
                    } else {
                        MultipartFile cardBackInput = cardService.getCorrectInput(cardBackType, cardBackVideoFileInput,
                                cardBackPictureFileInput, cardBackAudioFileInput);
                        newCard = cardService.addNewCard(cardFrontType, cardFrontTitel, cardFrontInput,
                                cardBackType, cardBackTitel, cardBackInput);
                    }
                }
            } catch (IllegalStateException e) {
                errors.add(e.getMessage());
            }

        }
        if (errors.isEmpty()) {
            learnSetService.addCardToList(learnSetID, newCard);
            modelAndView.setViewName("redirect:/cardOverview/" + learnSetID);
            return modelAndView;
        } else {
            for (String error: errors)
            {
                System.out.println(error);
            }
            // TODO : write errors to frontend -> viel spaß frontend team :)
            modelAndView.setViewName("redirect:/index");
            return modelAndView;
        }

    }

}
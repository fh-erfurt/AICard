package de.aicard.controller;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * adds a card to the corresponding learnSet
 * @author Martin Kühlborn,Clemens Berger
 */
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

    /**
     * shows the addCard.html if the user can access it
     * @param learnSetID /
     * @param principal /
     * @param model /
     * @return html
     */

    @GetMapping("/addCard/{learnSetID}")
    public String getAddCard(@PathVariable Long learnSetID, Principal principal, Model model) {
        Optional<Account> account = accountService.getAccount(principal);
        Optional<LearnSet> learnSet = learnSetService.getLearnSet(learnSetID);
        
        if (account.isPresent() && learnSet.isPresent() && learnSet.get().isAuthorizedToAccessLearnSet(account.get())) {
            model.addAttribute("learnSetID", learnSetID);
            return "addCard";
        }
        return "redirect:/index";
    }

    /**
     * handles creatng cards for a LearnSet
     * different params are needed to identify datatypes such as video,audio,text and pictures
     * for front and back side
     * @param learnSetID /
     * @param principal /
     * @param cardFrontType /
     * @param cardFrontTextFileTile /
     * @param cardFrontTextFileInput /
     * @param cardFrontPictureFileTitle /
     * @param cardFrontPictureFileInput /
     * @param cardFrontVideoFileTitle /
     * @param cardFrontVideoFileInput /
     * @param cardFrontAudioFileTitle /
     * @param cardFrontAudioFileInput /
     * @param cardBackType /
     * @param cardBackTextFileTitle /
     * @param cardBackTextFileInput /
     * @param cardBackPictureFileTitle /
     * @param cardBackPictureFileInput /
     * @param cardBackVideoFileTitle /
     * @param cardBackVideoFileInput /
     * @param cardBackAudioFileTitle /
     * @param cardBackAudioFileInput /
     * @return html
     */
    // --- we offer the possibility for all files to be send but only save those that are selected by the FileTypeSelector
    @PostMapping("/addCard/{learnSetID}")
    @ResponseBody
    public ModelAndView postAddCard(

            @PathVariable Long learnSetID, Principal principal,
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

        Optional<LearnSet> learnSet = learnSetService.getLearnSet(learnSetID);
        Optional<Account> account = accountService.getAccount(principal);
        
        if (account.isPresent() &&
                learnSet.isPresent() &&
                learnSet.get().isAuthorizedToAccessLearnSet(account.get()) &&
                learnSet.get().getAdminList().contains(account.get()))
        {
            
            // we are here if the learnSet exists and the Owner or an Admin is logged in
            String cardFrontTitel = cardService.getCorrectTitle(cardFrontType, cardFrontPictureFileTitle,
                    cardFrontTextFileTile, cardFrontVideoFileTitle, cardFrontAudioFileTitle);
            String cardBackTitel = cardService.getCorrectTitle(cardBackType, cardBackPictureFileTitle,
                    cardBackTextFileTitle,cardBackVideoFileTitle, cardBackAudioFileTitle);

            //TODO: Doppelter Code kann noch verbessert werden, wenn Zeit
            try {
                if (cardFrontType.equals(DataType.TextFile.name())) {

                    if (cardBackType.equals(DataType.TextFile.name())) {
                        newCard = cardService.addNewCard(cardFrontType, cardFrontTitel, cardFrontTextFileInput,
                                cardBackType, cardBackTitel, cardBackTextFileInput);

                    } else {
                        MultipartFile cardBackInput = cardService.getCorrectInput(cardBackType, cardBackVideoFileInput,
                                cardBackPictureFileInput, cardBackAudioFileInput);
                        newCard = cardService.addNewCard(cardFrontType, cardFrontTitel, cardFrontTextFileInput,
                                cardBackType, cardBackTitel, cardBackInput);
                    }
                } else {
                    MultipartFile cardFrontInput = cardService.getCorrectInput(cardFrontType, cardFrontVideoFileInput,
                            cardFrontPictureFileInput, cardFrontAudioFileInput);
                    if (cardBackType.equals(DataType.TextFile.name())) {
                        newCard = cardService.addNewCard(cardFrontType, cardFrontTitel, cardFrontInput,
                                cardBackType, cardBackTitel, cardBackTextFileInput);
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
        if (errors.isEmpty() && learnSet.isPresent()) {
            learnSet.get().getCardList().addToList(newCard);
            learnSetService.saveLearnSet(learnSet.get());
            
            modelAndView.setViewName("redirect:/cardOverview/" + learnSetID);
            
        }
        else
        {
            // for internal debugging
            for (String error: errors)
            {
                System.out.println(error);
            }
            
            modelAndView.setViewName("redirect:/addCard/" + learnSetID);
        }
        return modelAndView;
    }

}
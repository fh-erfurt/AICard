package de.aicard.services;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.CardListRepository;
import de.aicard.storages.LearnSetAboRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LearnSetService {
    @Autowired
    LearnSetRepository learnSetRepository;

    private final AccountService accountService;
    private final LearningSessionService learningSessionService;
    private final CardService cardService;
    
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public LearnSetAboRepository learnSetAboRepository;
    @Autowired
    public CardListRepository cardListRepository;

    @Autowired
    public LearnSetService(AccountService accountService,LearningSessionService learningSessionService,CardService cardService) {
        this.accountService = accountService;
        this.learningSessionService = learningSessionService;
        this.cardService = cardService;
    }

    public LearnSet createLearnSet(LearnSet learnSet, Account account)
    {
        account.createNewOwnLearnSet(learnSet.getTitle(),learnSet.getDescription(),learnSet.getFaculty(),learnSet.getVisibility());
        return account.getOwnLearnSets().get(account.getOwnLearnSets().size()-1);
    }

    public Boolean learnSetExists(Long id){
        return learnSetRepository.existsById(id);
    }

    public LearnSet getLearnSetByLearnSetId(Long learnSetId){
        return learnSetRepository.findById(learnSetId).orElse(null);
    }

    public Boolean isAdmin(Principal principal, Long id){
        if (this.learnSetExists(id) && accountService.accountExists(principal) ){
            return this.getLearnSetByLearnSetId(id).getAdminList().contains(accountService.getAccount(principal).get());
        }
        else{
            return false;
        }
    }
    //isPresent check
    public Boolean accountIsAuthorized(Principal principal, Long id){
        if(this.learnSetExists(id) && accountService.accountExists(principal)){
            return this.getLearnSetByLearnSetId(id).isAuthorizedToAccessLearnSet(accountService.getAccount(principal).get());
        }
        else{
            return false;
        }
    }

    public CardList getCardList(Long learnSetID){
        if(this.learnSetExists(learnSetID)){
            return this.getLearnSetByLearnSetId(learnSetID).getCardList();
        }
        return null;
    }
    
    public void saveLearnSet(LearnSet learnset){
        if(learnset != null){
            learnSetRepository.save(learnset);
        }
    }

    public void addCardToList(Long learnSetId, Card card){
        if(learnSetExists(learnSetId)){
            LearnSet learnSet = learnSetRepository.findById(learnSetId).get();

            learnSet.getCardList().addToList(card);
            learnSetRepository.save(learnSet);
        }


    }

    public void deleteLearnSet(Long id){
        LearnSet learnSet = this.getLearnSetByLearnSetId(id);
        //TODO: übernommen aus Controller. Bei gelegenheit prüfen, ob es auch ohne geht/ JPA Konfiguration checken:
        // sicherstellen, dass Owner nicht cascaded gelöscht wird, wenn man ihn drin lässt.
        // owner/admin -> null
        // -> löschen learnSetAbos (sessions/CardStatus)
        // -> all cards löschen
        // -> cardlist löschen
        // -> delete LearnSet

        //sett list to null
        learnSet.setAdminList(null);
        learnSet.setOwner(null);

        List<Account> accounts = accountRepository.findAll();
        //delete all learning Sessions based on this learnSet
        learningSessionService.deleteLearningSessionsByLearnSet(learnSet);
        //remove all LearnSetReferences in accounts
        for (Account account:accounts) {
            accountService.removeLearnSet(account,learnSet);
            // TODO : nicht nur aus den Abos sondern auch aus Own Learnsets löschen? !? ?11 Elf
        }
        //delete all cards of the learnSet
        List<Card> cardList = learnSet.getCardList().getListOfCards();
        for (int i = cardList.size()-1;i>=0;i--) {
            cardService.deleteCard(cardList.get(i));
        }
        //delete the cardlist
        cardListRepository.delete(learnSet.getCardList());
        //delete learnSet
        learnSetRepository.delete(learnSet);
        List<LearnSetAbo> abos = learnSetAboRepository.findAll();
        for (LearnSetAbo abo:abos) {
            if(abo.getLearnSet()==null && abo.getLearningSession()==null){
                learnSetAboRepository.delete(abo);
            }
        }

    }
    
    public void deleteAllAccountReferences(Long id){
        Optional<LearnSet> toDel = learnSetRepository.findById(id);
        List<Account> accountList = accountRepository.findAll();
        if(toDel.isPresent()){
            for (Account account1 : accountList)
            {
                account1.getOwnLearnSets().remove(toDel.get());
                account1.deleteLearnSetAboByLearnSet(toDel.get());
                // TODO : falls ein LearnSetsAbo existiert, lösche diese referenz auch!
            }
            toDel.get().setOwner(null);
        }
    }

}

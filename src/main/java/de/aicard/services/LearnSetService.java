package de.aicard.services;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.AccountRepository;
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
    
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public LearnSetAboRepository learnSetAboRepository;

    @Autowired
    public LearnSetService(AccountService accountService) {
        this.accountService = accountService;
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
    
    public Long getLearnSetIdByCardId(Long cardId){
        Optional<LearnSet> learnSet = learnSetRepository.getLearnSetByCardId(cardId);
        if(learnSet.isPresent()){
            return learnSet.get().getId();
        }
        return -1L;
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

    public void removeCardFromList(Card card){
        Long cardId = card.getId();
        Long learnSetId = this.getLearnSetIdByCardId(cardId);
        if(learnSetId>=-1L && learnSetRepository.existsById(learnSetId)){
            LearnSet learnSet = learnSetRepository.findById(learnSetId).get();
            learnSet.getCardList().removeFromList(card);
            learnSetRepository.save(learnSet);
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
//        LearnSet learnSet = this.getLearnSetByLearnSetId(id);
        //TODO: übernommen aus Controller. Bei gelegenheit prüfen, ob es auch ohne geht/ JPA Konfiguration checken:
        // sicherstellen, dass Owner nicht cascaded gelöscht wird, wenn man ihn drin lässt.
        // owner/admin -> null
        // -> löschen learnSetAbos (sessions/CardStatus)
        // -> all cards löschen
        // -> cardlist löschen
        // -> delete LearnSet


//        learnSet.setOwner(null);
//        learnSet.setAdminList(null);
//        System.out.println("learnSetId: "+id);

        learnSetRepository.deleteById(id);
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

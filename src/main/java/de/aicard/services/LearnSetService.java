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
    private final CardService cardService;
    
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public LearnSetAboRepository learnSetAboRepository;
    @Autowired
    public CardListRepository cardListRepository;

    @Autowired
    public LearnSetService(AccountService accountService,CardService cardService) {
        this.accountService = accountService;
        this.cardService = cardService;
    }

    public LearnSet createLearnSet(LearnSet learnSet, Account account)
    {
        account.createNewOwnLearnSet(learnSet.getTitle(),learnSet.getDescription(),learnSet.getFaculty(),learnSet.getVisibility());
        return account.getLearnsetAbos().get(account.getLearnsetAbos().size()-1).getLearnSet();
    }
    

    public Optional<LearnSet> getLearnSetByLearnSetId(Long learnSetId){
        return learnSetRepository.findById(learnSetId);
    }

    public Boolean isAdmin(Account account, LearnSet learnSet)
    {
        if(learnSet != null && learnSet.getAdminList() != null){
            return learnSet.getAdminList().contains(account);
        }
        return false;
    }
    public Boolean isOwner(Account account,LearnSet learnSet){
        if(learnSet != null && learnSet.getOwner() != null){
            return learnSet.getOwner().equals(account);
        }
        return false;
    }
    
    //isPresent check
    public Boolean accountIsAuthorized(Account account,LearnSet learnSet)
    {
        if(learnSet != null)
        {
            return learnSet.isAuthorizedToAccessLearnSet(account);
        }else {
            return false;
        }
    }
    
    public void saveLearnSet(LearnSet learnset){
        if(learnset != null){
            learnSetRepository.save(learnset);
        }
    }

    public void deleteLearnSet(Long id)
    {
        Optional<LearnSet> learnSet = this.getLearnSetByLearnSetId(id);
        //TODO: übernommen aus Controller. Bei gelegenheit prüfen, ob es auch ohne geht/ JPA Konfiguration checken:
        // sicherstellen, dass Owner nicht cascaded gelöscht wird, wenn man ihn drin lässt.
        // owner/admin -> null
        // -> löschen learnSetAbos (sessions/CardStatus)
        // -> all cards löschen
        // -> cardlist löschen
        // -> delete LearnSet
        learnSet.get().setOwner(null);
        learnSet.get().setAdminList(null);
        learnSetRepository.delete(learnSet.get());
    }

}

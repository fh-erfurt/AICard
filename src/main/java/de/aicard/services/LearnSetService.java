package de.aicard.services;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;

@Service
public class LearnSetService {
    @Autowired
    LearnSetRepository learnSetRepository;

    private final AccountService accountService;

    @Autowired
    public LearnSetService(AccountService accountService) {
        this.accountService = accountService;
    }


    public Long createLearnSet(Principal principal) {

        if (accountService.accountExists(principal)) {
            Account account = accountService.getAccount(principal);
            LearnSet newLearnset = new LearnSet();
            newLearnset.setOwner(account);
            newLearnset.setAdminList(new ArrayList<>());
            newLearnset.addAdmin(account);
            newLearnset.setCardList(new CardList());
            newLearnset.setCommentList(new ArrayList<>());
            learnSetRepository.save(newLearnset);

            accountService.addLearnSet(account, newLearnset);

            return newLearnset.getId();

        }

        return Long.valueOf(-1);
    }

    public Boolean learnSetExists(Long id){
        return learnSetRepository.existsById(id);
    }

    public LearnSet getLearnSetByLearnSetId(Long learnSetId){
        LearnSet learnSet = learnSetRepository.findById(learnSetId).get();
        return learnSet;
    }

    public Long getLearnSetIdByCardId(Long cardId){
        LearnSet learnSet = learnSetRepository.getLearnSetByCardId(cardId).get();
        return learnSet.getId();
    }

    public Boolean isAdmin(Principal principal, Long id){
        if (this.learnSetExists(id) && accountService.accountExists(principal) ){
            return this.getLearnSetByLearnSetId(id).getAdminList().contains(accountService.getAccount(principal));
        }
        else{
            return false;
        }
    }

    public Boolean accountIsAuthorized(Principal principal, Long id){
        if(this.learnSetExists(id) && accountService.accountExists(principal)){
            return this.getLearnSetByLearnSetId(id).isAuthorizedToAccessLearnSet(accountService.getAccount(principal));
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

    public void removeCardFromList(Card card){
        Long cardId = card.getId();
        Long learnSetId = this.getLearnSetIdByCardId(cardId);
        if(learnSetRepository.existsById(learnSetId)){
            LearnSet learnSet = learnSetRepository.findById(learnSetId).get();
            learnSet.getCardList().removeFromList(card);
        }
    }

    public void deleteLearnSet(Long id){
        LearnSet learnSet = this.getLearnSetByLearnSetId(id);
        //TODO: übernommen aus Controller. Bei gelegenheit prüfen, ob es auch ohne geht/ JPA Konfiguration checken:
        // sicherstellen, dass Owner nicht cascaded gelöscht wird, wenn man ihn drin lässt.
        learnSet.setOwner(null);
        learnSetRepository.deleteById(id);
    }

}

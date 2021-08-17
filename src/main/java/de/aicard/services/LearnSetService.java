package de.aicard.services;

import de.aicard.domains.account.Account;
import de.aicard.domains.card.Card;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.CardListRepository;
import de.aicard.storages.LearnSetAboRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnSetService {

    
    @Autowired
    LearnSetRepository learnSetRepository;
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public LearnSetAboRepository learnSetAboRepository;
    @Autowired
    public CardListRepository cardListRepository;

    @Autowired
    public LearnSetService() {
    }

    public List<LearnSet> findAll(){
        return learnSetRepository.findAll();
    }
    
    public LearnSet createLearnSet(LearnSet learnSet, Account account)
    {
        account.createNewOwnLearnSet(learnSet.getTitle(),learnSet.getDescription(),learnSet.getFaculty(),learnSet.getVisibility());
        return account.getLearnsetAbos().get(account.getLearnsetAbos().size()-1).getLearnSet();
    }
    
    public Optional<LearnSet> getLearnSet(Long learnSetId){
        return learnSetRepository.findById(learnSetId);
    }
    
    public Optional<LearnSet> getLearnSetByCardId(Long cardId){
        return learnSetRepository.getLearnSetByCardId(cardId);
    }
   
    public void saveLearnSet(LearnSet learnset){
        if(learnset != null){
            learnSetRepository.save(learnset);
        }
    }

    public void deleteLearnSet(LearnSet learnSet)
    {
        learnSet.setOwner(null);
        learnSet.setAdminList(null);
        for (Card card:learnSet.getCardList().getListOfCards())
        {
            card.deleteCardContent();
        }
        learnSetRepository.delete(learnSet);
    }
}

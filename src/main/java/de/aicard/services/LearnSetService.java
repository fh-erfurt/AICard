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

/**
 * handles creations, deletions of learnsets and LearnSetRepository calls
 *
 * @author Clemens Berger, Daniel Michel, Martin Kuehlborn
 */
@Service
public class LearnSetService
{
    
    
    final LearnSetRepository learnSetRepository;
    final AccountRepository accountRepository;
    final LearnSetAboRepository learnSetAboRepository;
    final CardListRepository cardListRepository;
    
    @Autowired
    public LearnSetService(LearnSetRepository learnSetRepository, AccountRepository accountRepository, LearnSetAboRepository learnSetAboRepository, CardListRepository cardListRepository)
    {
        this.learnSetRepository = learnSetRepository;
        this.accountRepository = accountRepository;
        this.learnSetAboRepository = learnSetAboRepository;
        this.cardListRepository = cardListRepository;
    }
    
    public List<LearnSet> findAll()
    {
        return learnSetRepository.findAll();
    }
    
    /**
     * creates new own learn and creates new learnsetabo for it
     *
     * @param learnSet /
     * @param account  /
     * @return last inserted learnset from learnsetabos in given account
     */
    public LearnSet createLearnSet(LearnSet learnSet, Account account)
    {
        account.createNewOwnLearnSet(learnSet.getTitle(), learnSet.getDescription(), learnSet.getFaculty(), learnSet.getVisibility());
        return account.getLearnsetAbos().get(account.getLearnsetAbos().size() - 1).getLearnSet();
    }
    
    public Optional<LearnSet> getLearnSet(Long learnSetId)
    {
        return learnSetRepository.findById(learnSetId);
    }
    
    public Optional<LearnSet> getLearnSetByCardId(Long cardId)
    {
        return learnSetRepository.getLearnSetByCardId(cardId);
    }
    
    /**
     * save learnset
     *
     * @param learnset given learnset
     */
    public void saveLearnSet(LearnSet learnset)
    {
        if (learnset != null)
        {
            learnSetRepository.save(learnset);
        }
    }
    
    /**
     * deletes learnset after removing the owner and adminlist
     * deletes cardcontent for each card in learset
     *
     * @param learnSet given learnset
     */
    public void deleteLearnSet(LearnSet learnSet)
    {
        learnSet.setOwner(null);
        learnSet.setAdminList(null);
        for (Card card : learnSet.getCardList().getListOfCards())
        {
            card.deleteCardContent();
        }
        learnSetRepository.delete(learnSet);
    }
}

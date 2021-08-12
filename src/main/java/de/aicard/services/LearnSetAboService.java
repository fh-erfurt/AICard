package de.aicard.services;

import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.LearnSetAboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LearnSetAboService {
    @Autowired
    LearnSetAboRepository learnSetAboRepository;

    private final AccountService accountService;

    @Autowired
    public LearnSetAboService(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<LearnSetAbo> getLearnSetAbos(Principal principal){

        List<LearnSetAbo> erg = new ArrayList<>();
        Optional<Account> account = accountService.getAccount(principal);
        if(account.isPresent()){
            erg = account.get().getLearnsetAbos();
        }
        return erg;
    }

    public void deleteLearnSetAbosByLearnSetId(Long id){


        learnSetAboRepository.deleteAllByLearnSet(id);
    }

}

package de.aicard.services;

import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.LearnSetAboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

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
        return accountService.getAccount(principal).getLearnsetAbos();
    }
}

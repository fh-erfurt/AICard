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


    @Autowired
    public LearnSetAboService() {

    }

}

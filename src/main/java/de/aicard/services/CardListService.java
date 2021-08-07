package de.aicard.services;

import de.aicard.domains.learnset.CardList;
import de.aicard.storages.CardListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardListService {
    @Autowired
    CardListRepository cardListRepository;


}

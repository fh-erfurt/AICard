package de.aicard.storages;

import de.aicard.core.H2Controller;
import de.aicard.domains.learnset.CardList;

public class CardListRepository extends BaseRepository<CardList>{
    public CardListRepository(){
        super(H2Controller.getManager().getEntityManager(), CardList.class);
    }
}

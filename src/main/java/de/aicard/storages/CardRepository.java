package de.aicard.storages;

import de.aicard.core.H2Controller;
import de.aicard.domains.card.Card;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardRepository extends BaseRepository<Card>{

    public CardRepository(){
        super(H2Controller.getManager().getEntityManager(), Card.class);
    }
}

package de.aicard.storages;

import de.aicard.core.H2Controller;
import de.aicard.domains.card.CardStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardStatusRepository extends BaseRepository<CardStatus>
{

    public CardStatusRepository()
    {
        super(H2Controller.getManager().getEntityManager(), CardStatus.class);
    }
}

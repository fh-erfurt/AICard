package de.aicard.storages;


import de.aicard.domains.Social.Chat;
import de.aicard.core.H2Controller;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ChatRepository extends BaseRepository<Chat>{

    public ChatRepository(){
        super(H2Controller.getManager().getEntityManager(), Chat.class);
    }

    public Optional<Chat> findBy(String participant)
    {
        TypedQuery<Chat> query =  entityManager.createQuery("SELECT chat FROM" + Chat.class.getCanonicalName() +
                " chat WHERE chat.participants.chatHasParticipant( :participant ) = " + true + " ",Chat.class);

        query.setParameter("participant",participant);

        List<Chat> loaded = query.getResultList();
        if(loaded.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(loaded.get(0));
    }
}

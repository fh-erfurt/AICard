package de.aicard.db.storages;

import de.aicard.Social.Chat;
import de.aicard.Social.Message;
import de.aicard.account.Account;
import de.aicard.card.AudioFile;
import de.aicard.db.core.H2Controller;
import de.aicard.db.domains.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Slf4j
public class MessageRepository extends BaseRepository<Message> {

    public MessageRepository(){
        super(H2Controller.getManager().getEntityManager(), Message.class);
    }

    public Optional<Message> findBy(Account sender){
        TypedQuery<Message> query =  entityManager.createQuery("SELECT message FROM " + Message.class.getCanonicalName() +
                " message WHERE message.sender = :sender" ,Message.class);

        query.setParameter("sender",sender);

        List<Message> loaded = query.getResultList();
        if(loaded.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(loaded.get(0));
    }
}

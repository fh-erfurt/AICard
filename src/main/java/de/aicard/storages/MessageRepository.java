package de.aicard.storages;

import de.aicard.domains.Social.Message;
import de.aicard.domains.account.Account;
import de.aicard.core.H2Controller;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Slf4j
public class MessageRepository extends BaseRepository<Message> {

    public MessageRepository(){
        super(H2Controller.getManager().getEntityManager(), Message.class);
    }

    public Optional<Message> findBy(String sender){
        TypedQuery<Message> query =  entityManager.createQuery("SELECT message FROM " + Message.class.getCanonicalName() +
                " message WHERE message.sender.getName() = '" + sender + "'" ,Message.class);

        //query.setParameter("name", sender);


        List<Message> loaded = query.getResultList();
        if(loaded.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(loaded.get(0));
    }
}

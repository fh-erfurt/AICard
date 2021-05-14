package de.aicard.db.storages;

import de.aicard.account.Account;
import de.aicard.db.core.H2Controller;
import de.aicard.db.domains.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
public class AccountRepository extends BaseRepository<Account> {

        public AccountRepository(){
            super(H2Controller.getManager().getEntityManager(), Account.class);
        }

        public Optional<Account> findBy(String name){
            TypedQuery<Account> query =  entityManager.createQuery("SELECT account FROM" + Account.class.getCanonicalName() + " account WHERE account.name = :name",Account.class);
            query.setParameter("name",name);
            List<Account> loaded = query.getResultList();
            if(loaded.isEmpty()){
                return Optional.empty();
            }
            return Optional.of(loaded.get(0));
        }
}

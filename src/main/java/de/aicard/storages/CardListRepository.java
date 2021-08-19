package de.aicard.storages;

import de.aicard.domains.learnset.CardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardListRepository extends JpaRepository<CardList, Long>
{

}

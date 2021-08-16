package de.aicard.storages;

import de.aicard.domains.card.CardStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardStatusRepository extends JpaRepository<CardStatus, Long>
{

}

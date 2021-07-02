package de.aicard.storages;

import de.aicard.domains.card.CardContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardContentRepository extends JpaRepository<CardContent, Long> {

}

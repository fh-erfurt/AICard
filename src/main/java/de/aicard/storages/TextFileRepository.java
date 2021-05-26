package de.aicard.storages;

import de.aicard.domains.card.TextFile;
import de.aicard.core.H2Controller;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TextFileRepository extends JpaRepository<TextFile, Long>
{

}


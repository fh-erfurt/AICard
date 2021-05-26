package de.aicard.storages;

import de.aicard.domains.card.AudioFile;
import de.aicard.core.H2Controller;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface AudioFileRepository extends JpaRepository<AudioFile, Long>
{

    Optional<AudioFile> findByTitle(String title);
}

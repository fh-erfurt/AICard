package de.aicard.storages;

import de.aicard.domains.card.VideoFile;
import de.aicard.core.H2Controller;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface VideoFileRepository  extends JpaRepository<VideoFile, Long>
{
    Optional<VideoFile> findByTitle(String title);
}

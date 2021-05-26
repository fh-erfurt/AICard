package de.aicard.storages;

import de.aicard.domains.card.PictureFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PictureFileRepository extends JpaRepository<PictureFile, Long>
    {
        
        Optional<PictureFile> findByTitle(String title);

    }

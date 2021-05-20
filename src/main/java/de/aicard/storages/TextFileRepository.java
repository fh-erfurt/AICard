package de.aicard.storages;

import de.aicard.domains.card.TextFile;
import de.aicard.core.H2Controller;


public class TextFileRepository extends BaseRepository<TextFile>
{
    public TextFileRepository()
    {
        super(H2Controller.getManager().getEntityManager(), TextFile.class);
    }
    
}


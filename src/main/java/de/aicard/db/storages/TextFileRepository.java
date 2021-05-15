package de.aicard.db.storages;

import de.aicard.card.TextFile;
import de.aicard.db.core.H2Controller;


public class TextFileRepository extends BaseRepository<TextFile>
{
    public TextFileRepository()
    {
        super(H2Controller.getManager().getEntityManager(), TextFile.class);
    }
    
}


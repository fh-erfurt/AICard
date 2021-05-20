package de.aicard.storages;

import de.aicard.domains.card.AudioFile;
import de.aicard.core.H2Controller;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class AudioFileRepository extends BaseRepository<AudioFile>
{
    public AudioFileRepository()
    {
        super(H2Controller.getManager().getEntityManager(), AudioFile.class);
    }
    
    public Optional<AudioFile> findBy(String title)
    {
        TypedQuery<AudioFile> query = entityManager.createQuery("SELECT audiofile FROM " + AudioFile.class.getCanonicalName() +
                                                                        " audiofile WHERE audiofile.title = :title" , AudioFile.class);
        query.setParameter("title", title);
        
        List<AudioFile> loaded = query.getResultList();
        if(loaded.isEmpty())
        {
            return Optional.empty();
        }
        
        return Optional.of(loaded.get(0));
    }
}

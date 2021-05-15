package de.aicard.db.storages;

import de.aicard.card.VideoFile;
import de.aicard.db.core.H2Controller;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class VideoFileRepository  extends BaseRepository<VideoFile>
{
    public VideoFileRepository()
    {
        super(H2Controller.getManager().getEntityManager(), VideoFile.class);
    }
    
    public Optional<VideoFile> findBy(String title)
    {
        TypedQuery<VideoFile> query = entityManager.createQuery("SELECT videofile FROM " + VideoFile.class.getCanonicalName() +
                                                                        " videofile WHERE videofile.title = :title" , VideoFile.class);
        query.setParameter("title", title);
        
        List<VideoFile> loaded = query.getResultList();
        if(loaded.isEmpty())
        {
            return Optional.empty();
        }
        
        return Optional.of(loaded.get(0));
    }
}

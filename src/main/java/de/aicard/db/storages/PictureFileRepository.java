package de.aicard.db.storages;

import de.aicard.card.PictureFile;
import de.aicard.db.core.H2Controller;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PictureFileRepository extends BaseRepository<PictureFile>
    {
        public PictureFileRepository()
        {
            super(H2Controller.getManager().getEntityManager(), PictureFile.class);
        }
        
        public Optional<PictureFile> findBy(String title)
        {
            TypedQuery<PictureFile> query = entityManager.createQuery("SELECT picturefile FROM " + PictureFile.class.getCanonicalName() +
                                                                            " picturefile WHERE picturefile.title = :title" , PictureFile.class);
            query.setParameter("title", title);
            
            List<PictureFile> loaded = query.getResultList();
            if(loaded.isEmpty())
            {
                return Optional.empty();
            }
            
            return Optional.of(loaded.get(0));
        }
    }

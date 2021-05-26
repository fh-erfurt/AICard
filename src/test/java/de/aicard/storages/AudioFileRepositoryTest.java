package de.aicard.storages;

import de.aicard.domains.card.AudioFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test class for the functions of AudioFile
 *
 * @author Clemens Berger
 */
public class AudioFileRepositoryTest
{
    AudioFileRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new AudioFileRepository();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
    }

   // String _email, String _password, String _name, String _description,int _semester,Faculty _faculty)
    @Test
    void save()
    {
        //repository = new AudioFileRepository();
        // GIVEN
        AudioFile given = new AudioFile("Musik","Musik1");

        // WHEN
        Long result = repository.save(given);

        // THEN
        Assertions.assertTrue(result != null && result > 0);
    }
//String _email, String _password, String _name, String _description, AcademicGrade _academic
    @Test
    void findAll(){
        //repository = new AudioFileRepository();
        AudioFile given1 = new AudioFile("Musik","Musik1");
        AudioFile given2 = new AudioFile("Musik","Musik2");

        List<Long> idsOfPersisted = new ArrayList<>();
        idsOfPersisted.add(repository.save(given1));
        idsOfPersisted.add(repository.save(given2));

        List<AudioFile> result = repository.findAll();

        Assertions.assertTrue(result != null && !result.isEmpty());
        Assertions.assertFalse(idsOfPersisted.isEmpty());
    }
    @Test
    void findbyTitle(){
        //repository = new AudioFileRepository();
        AudioFile given1 = new AudioFile("Musik","Musik1");
        AudioFile given2 = new AudioFile("Musik","Musik2");

        repository.save(given1);
        repository.save(given2);

        Optional<AudioFile> result = repository.findBy("Musik2");

        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get().getTitle(),"Musik2");
    }
}
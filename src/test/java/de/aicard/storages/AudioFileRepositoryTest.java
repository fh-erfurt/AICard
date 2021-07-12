package de.aicard.storages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test class for the functions of AudioFile
 *
 * @author Clemens Berger
 */
@DataJpaTest
public class AudioFileRepositoryTest
{
//    @Autowired
//    AudioFileRepository audioFileRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//        audioFileRepository.deleteAll();
//    }
//
//   // String _email, String _password, String _name, String _description,int _semester,Faculty _faculty)
//    @Test
//    void save()
//    {
//        //videoFileRepository = new AudioFileRepository();
//        // GIVEN
//        AudioFile given = new AudioFile("Musik","Musik1");
//
//        // WHEN
//        AudioFile result = audioFileRepository.save(given);
//        Long resultID = result.getId();
//
//        // THEN
//        Assertions.assertTrue(resultID != null && resultID > 0);
//    }
////String _email, String _password, String _name, String _description, AcademicGrade _academic
//    @Test
//    void findAll(){
//        //videoFileRepository = new AudioFileRepository();
//        AudioFile given1 = new AudioFile("Musik","Musik1");
//        AudioFile given2 = new AudioFile("Musik","Musik2");
//
//        List<Long> idsOfPersisted = new ArrayList<>();
//        AudioFile saved1 = audioFileRepository.save(given1);
//        AudioFile saved2 = audioFileRepository.save(given2);
//        idsOfPersisted.add(saved1.getId());
//        idsOfPersisted.add(saved2.getId());
//
//        List<AudioFile> result = audioFileRepository.findAll();
//
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertFalse(idsOfPersisted.isEmpty());
//    }
//    @Test
//    void findbyTitle(){
//        //videoFileRepository = new AudioFileRepository();
//        AudioFile given1 = new AudioFile("Musik","Musik1");
//        AudioFile given2 = new AudioFile("Musik","Musik2");
//
//        audioFileRepository.save(given1);
//        audioFileRepository.save(given2);
//
//        Optional<AudioFile> result = audioFileRepository.findByTitle("Musik2");
//
//        Assertions.assertTrue(result.isPresent());
//        Assertions.assertSame(result.get().getTitle(),"Musik2");
//    }
}
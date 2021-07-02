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
 * Test class for the functions of PictureFileRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class PictureFileRepositoryTest
{
//    @Autowired
//    CardContentRepository pictureFileRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//        pictureFileRepository.deleteAll();
//    }
//
//
//    @Test
//    void save()
//    {
//
//        // GIVEN
//        Card given = new PictureFile("A picture","Nice picture");
//
//        // WHEN
//        PictureFile result = pictureFileRepository.save(given);
//        Long resultID = result.getId();
//
//        // THEN
//        Assertions.assertTrue(resultID != null && resultID > 0);
//    }
////String _email, String _password, String _name, String _description, AcademicGrade _academic
//    @Test
//    void findAll(){
//
//        PictureFile given1 = new PictureFile("A picture","first nice picture");
//        PictureFile given2 = new PictureFile("Another picture","a second nice picture");
//
//        List<Long> idsOfPersisted = new ArrayList<>();
//        PictureFile saved1 = pictureFileRepository.save(given1);
//        PictureFile saved2 = pictureFileRepository.save(given2);
//        idsOfPersisted.add(saved1.getId());
//        idsOfPersisted.add(saved2.getId());
//
//        List<PictureFile> result = pictureFileRepository.findAll();
//
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertFalse(idsOfPersisted.isEmpty());
//    }
//    @Test
//    void findByTitle(){
//
//        PictureFile given1 = new PictureFile("A picture","Picture1");
//        PictureFile given2 = new PictureFile("Second picture","Picture2");
//
//        pictureFileRepository.save(given1);
//        pictureFileRepository.save(given2);
//
//        Optional<PictureFile> result = pictureFileRepository.findByTitle("Picture1");
//
//        Assertions.assertTrue(result.isPresent());
//        Assertions.assertSame(result.get().getTitle(),"Picture1");
//    }
}
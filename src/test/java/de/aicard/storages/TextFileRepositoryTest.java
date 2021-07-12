package de.aicard.storages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the functions of TextFileRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class TextFileRepositoryTest {
//    @Autowired
//    TextFileRepository textFileRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//        textFileRepository.deleteAll();
//    }
//
//    @Test
//    void save() {
//        // GIVEN
//        TextFile given = new TextFile("Text of a TextFile","textFile");
//
//        // WHEN
//        TextFile result = textFileRepository.save(given);
//        Long resultID = result.getId();
//
//        // THEN
//        Assertions.assertTrue(resultID != null && resultID > 0);
//    }
//
//    //String _email, String _password, String _name, String _description, AcademicGrade _academic
//    @Test
//    void findAll() {
//        //videoFileRepository = new AudioFileRepository();
//        TextFile given1 = new TextFile("Text1","Title1");
//        TextFile given2 = new TextFile("Text2","Title2");
//
//        List<Long> idsOfPersisted = new ArrayList<>();
//        TextFile saved1 = textFileRepository.save(given1);
//        TextFile saved2 = textFileRepository.save(given2);
//        idsOfPersisted.add(saved1.getId());
//        idsOfPersisted.add(saved2.getId());
//
//        List<TextFile> result = textFileRepository.findAll();
//
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertFalse(idsOfPersisted.isEmpty());
//    }
}
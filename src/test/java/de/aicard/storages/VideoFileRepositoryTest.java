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
 * @author Daniel Michel
 */
@DataJpaTest
public class VideoFileRepositoryTest
{
//    @Autowired
//    VideoFileRepository videoFileRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//        videoFileRepository.deleteAll();
//    }
//
//    @Test
//    void save()
//    {
//        // GIVEN
//        VideoFile given = new VideoFile("Video","Video1");
//
//        // WHEN
//        VideoFile result = videoFileRepository.save(given);
//        Long resultID = result.getId();
//
//        // THEN
//        Assertions.assertTrue(resultID != null && resultID > 0);
//    }
////String _email, String _password, String _name, String _description, AcademicGrade _academic
//    @Test
//    void findAll(){
//        VideoFile given1 = new VideoFile("Video","Video1");
//        VideoFile given2 = new VideoFile("Video","Video2");
//
//        List<Long> idsOfPersisted = new ArrayList<>();
//        VideoFile saved1 = videoFileRepository.save(given1);
//        VideoFile saved2 = videoFileRepository.save(given2);
//        idsOfPersisted.add(saved1.getId());
//        idsOfPersisted.add(saved2.getId());
//
//        List<VideoFile> result = videoFileRepository.findAll();
//
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertFalse(idsOfPersisted.isEmpty());
//    }
//    @Test
//    void findByTitle(){
//        //videoFileRepository = new AudioFileRepository();
//        VideoFile given1 = new VideoFile("Video","Video1");
//        VideoFile given2 = new VideoFile("Video","Video2");
//
//        videoFileRepository.save(given1);
//        videoFileRepository.save(given2);
//
//        Optional<VideoFile> result = videoFileRepository.findByTitle("Video2");
//
//        Assertions.assertTrue(result.isPresent());
//        Assertions.assertSame(result.get().getTitle(),"Video2");
//    }
}
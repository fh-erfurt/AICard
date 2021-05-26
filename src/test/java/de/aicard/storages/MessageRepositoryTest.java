package de.aicard.storages;

import de.aicard.domains.Social.Message;
import de.aicard.domains.account.Student;
import de.aicard.domains.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class MessageRepositoryTest {

    Faculty APPLIED_COMPUTER_SCIENCE ;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    AccountRepository accountRepository;


    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {

        messageRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void save() {
        // GIVEN
        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
        accountRepository.save(givenStudent1);
        Message givenMessage = new Message("this is a new message",givenStudent1);

        // WHEN
        Message result = messageRepository.save(givenMessage);
        Long resultId = result.getId();

        // THEN
        Assertions.assertTrue(resultId != null && resultId >0);

    }
/*
    @Test
    void findAll() {
        // GIVEN
        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
        Student givenStudent2 = new Student ("email2@fh-erfurt.de","password2","student2","std2",1,APPLIED_COMPUTER_SCIENCE);

        Message givenMessage1 = new Message("this is a new message",givenStudent1);
        Message givenMessage2 = new Message("this is a new message",givenStudent2);

        List<Long> idsOfPersisted = new ArrayList<>();
        idsOfPersisted.add(videoFileRepository.save(givenMessage1));
        idsOfPersisted.add(videoFileRepository.save(givenMessage2));

        // WHEN
        List<Message> result = videoFileRepository.findAll();

        // THEN
        Assertions.assertThat(result).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        Assertions.assertThat(idsOfPersisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }

    @Test
    void findBySender() {

        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
        Student givenStudent2 = new Student ("email2@fh-erfurt.de","password2","student2","std2",1,APPLIED_COMPUTER_SCIENCE);

        Message givenMessage1 = new Message("this is a new message",givenStudent1);
        Message givenMessage2 = new Message("this is a new message",givenStudent2);

        videoFileRepository.save(givenMessage1);
        videoFileRepository.save(givenMessage2);

        Optional<Message> result = videoFileRepository.findBy("student1");

        Assertions.assertThat(result).isNotNull().isNotEmpty();
        Assertions.assertEquals(result.get(),givenMessage1);

    }
    @Test
    void delete()
    {

    }

*/

}
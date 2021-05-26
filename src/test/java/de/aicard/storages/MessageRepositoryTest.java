package de.aicard.storages;

import de.aicard.domains.Social.Chat;
import de.aicard.domains.Social.Message;
import de.aicard.domains.account.Student;
import de.aicard.domains.enums.Faculty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


class MessageRepositoryTest {

    Faculty APPLIED_COMPUTER_SCIENCE ;
    MessageRepository repository;


    @BeforeEach
    public void beforeEach() {
        repository = new MessageRepository();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
        new MessageRepository().deleteAll();
    }

    @Test
    void save() {
        // GIVEN
        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);

        Message givenMessage = new Message("this is a new message",givenStudent1);

        // WHEN
        Long result = repository.save(givenMessage);

        // THEN
        Assertions.assertTrue(result != 0 && result >0);

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
        idsOfPersisted.add(repository.save(givenMessage1));
        idsOfPersisted.add(repository.save(givenMessage2));

        // WHEN
        List<Message> result = repository.findAll();

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

        repository.save(givenMessage1);
        repository.save(givenMessage2);

        Optional<Message> result = repository.findBy("student1");

        Assertions.assertThat(result).isNotNull().isNotEmpty();
        Assertions.assertEquals(result.get(),givenMessage1);

    }
    @Test
    void delete()
    {

    }

*/

}
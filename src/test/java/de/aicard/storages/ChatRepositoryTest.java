package de.aicard.storages;

import de.aicard.domains.Social.Chat;
import de.aicard.domains.account.Student;
import de.aicard.domains.enums.Faculty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class ChatRepositoryTest {
    Faculty APPLIED_COMPUTER_SCIENCE ;
    ChatRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new ChatRepository();
    }

    @AfterEach
    public void afterEach() {
        repository.deleteAll();
        new ChatRepository().deleteAll();
    }

    @Test
    void save() {
        // GIVEN

        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
        Student givenStudent2 = new Student ("email2@fh-erfurt.de","password2","student2","std2",1,APPLIED_COMPUTER_SCIENCE);

        Chat givenChat = new Chat(givenStudent1,givenStudent2,"This is a new message ");

        // WHEN
        Long result = repository.save(givenChat);

        // THEN
        Assertions.assertTrue(result != 0 && result >0);

    }
    
    
    @Test
    void findAll() {
        // GIVEN
        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
        Student givenStudent2 = new Student ("email2@fh-erfurt.de","password2","student2","std2",1,APPLIED_COMPUTER_SCIENCE);
        Student givenStudent3 = new Student ("email3@fh-erfurt.de","password3","student3","std3",1,APPLIED_COMPUTER_SCIENCE);

        Chat givenChat1 = new Chat(givenStudent1,givenStudent2,"This is a new message ");
        Chat givenChat2 = new Chat(givenStudent1,givenStudent3,"This is a new message ");

        List<Long> idsOfPersisted = new ArrayList<>();
        idsOfPersisted.add(repository.save(givenChat1));
        idsOfPersisted.add(repository.save(givenChat2));

        // WHEN
        List<Chat> result = repository.findAll();

        // THEN
        //Assertions.assertEquals(result).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
        //Assertions.assertEquals(idsOfPersisted).isNotNull().isNotEmpty().allMatch(Objects::nonNull);
    }


    @Test
    void findByParticipantName() {
        // GIVEN
        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
        Student givenStudent2 = new Student ("email2@fh-erfurt.de","password2","student2","std2",1,APPLIED_COMPUTER_SCIENCE);
        Student givenStudent3 = new Student ("email3@fh-erfurt.de","password3","student3","std3",1,APPLIED_COMPUTER_SCIENCE);

        Chat givenChat1 = new Chat(givenStudent1,givenStudent2,"This is a new message ");
        Chat givenChat2 = new Chat(givenStudent1,givenStudent3,"This is a new message ");

        repository.save(givenChat1);
        repository.save(givenChat2);


        //WHEN
        Optional <Chat> result = repository.findBy("student3");

        //THEN
        //Assertions.assertThat(result).isNotNull().isNotEmpty();
        //Assertions.assertEquals(result.get(),givenChat2);

    }

    @Test
    void delete()
    {

    }


}
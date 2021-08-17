package de.aicard.storages;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class MessageRepositoryTest {

//    Faculty APPLIED_COMPUTER_SCIENCE ;
//    @Autowired
//    MessageRepository messageRepository;
//    @Autowired
//    AccountRepository accountRepository;
//
//
//    @BeforeEach
//    public void beforeEach() {
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//
//        messageRepository.deleteAll();
//        accountRepository.deleteAll();
//    }
//
//    @Test
//    void save() {
//        // GIVEN
//        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
//        accountRepository.save(givenStudent1);
//        Message givenMessage = new Message("this is a new message",givenStudent1);
//
//        // WHEN
//        Message result = messageRepository.save(givenMessage);
//        Long resultId = result.getId();
//
//        // THEN
//        Assertions.assertTrue(resultId != null && resultId >0);
//
//    }
//
//    @Test
//    void findAll() {
//        // GIVEN
//        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
//        Student givenStudent2 = new Student ("email2@fh-erfurt.de","password2","student2","std2",1,APPLIED_COMPUTER_SCIENCE);
//
//        accountRepository.save(givenStudent1);
//        accountRepository.save(givenStudent2);
//
//        Message givenMessage1 = new Message("this is a new message",givenStudent1);
//        Message givenMessage2 = new Message("this is a new message",givenStudent2);
//
//
//        List<Long> idsOfPersisted = new ArrayList<>();
//        Message saved1 = messageRepository.save(givenMessage1);
//        Message saved2 = messageRepository.save(givenMessage1);
//
//
//        idsOfPersisted.add(saved1.getId());
//        idsOfPersisted.add(saved2.getId());
//
//        // WHEN
//        List<Message> result = messageRepository.findAll();
//
//        // THEN
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertFalse(idsOfPersisted.isEmpty());
//    }
//
//    @Test
//    void findBySender() {
//
//        Student givenStudent1 = new Student ("email1@fh-erfurt.de","password1","student1","std1",1,APPLIED_COMPUTER_SCIENCE);
//        Student givenStudent2 = new Student ("email2@fh-erfurt.de","password2","student2","std2",1,APPLIED_COMPUTER_SCIENCE);
//
//        accountRepository.save(givenStudent1);
//        accountRepository.save(givenStudent2);
//
//        Message givenMessage1 = new Message("this is a new message",givenStudent1);
//        Message givenMessage2 = new Message("this is a new message",givenStudent2);
//
//        messageRepository.save(givenMessage1);
//        messageRepository.save(givenMessage2);
//
//        Optional<Message> result = messageRepository.findBySender(givenStudent1);
//
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertEquals(result.get(),givenMessage1);
//
//    }

}
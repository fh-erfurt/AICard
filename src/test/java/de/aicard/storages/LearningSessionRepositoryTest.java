package de.aicard.storages;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.learnset.LearningSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the functions of LearningSessionRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class LearningSessionRepositoryTest {
//    @Autowired
//    LearningSessionRepository learningSessionRepository;
//    @Autowired
//    CardContentRepository textFileRepository;
//    @Autowired
//    CardRepository cardRepository;
//    @Autowired
//    CardStatusRepository cardStatusRepository;
//
//    @BeforeEach
//    public void beforeEach() {
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//
//        learningSessionRepository.deleteAll();
//        textFileRepository.deleteAll();
//        cardRepository.deleteAll();
//        cardStatusRepository.deleteAll();
//    }
//
//    @Test
//    void save() {
//        // GIVEN
//
//        //2 CardFront, 2 CardBack
//        TextFile cardFront1 = new TextFile("Front of Card 1","Front1");
//        TextFile cardFront2 = new TextFile("Front of Card 2","Front2");
//        TextFile cardBack1 = new TextFile("Back of Card 1","Back1");
//        TextFile cardBack2 = new TextFile("Back of Card 2","Back1");
//
//        textFileRepository.save(cardFront1);
//        textFileRepository.save(cardFront2);
//        textFileRepository.save(cardBack1);
//        textFileRepository.save(cardBack2);
//
//        //2x Card
//        Card card1 = new Card(cardFront1, cardBack1);
//        Card card2 = new Card(cardFront2, cardBack2);
//
//        cardRepository.save(card1);
//        cardRepository.save(card2);
//
//        //2x CardStatus
//        CardStatus status1 = new CardStatus(card1);
//        CardStatus status2 = new CardStatus(card2);
//
//        cardStatusRepository.save(status1);
//        cardStatusRepository.save(status2);
//
//        //List<CardStatus>
//        List<CardStatus> statusList = new ArrayList<>();
//        statusList.add(status1);
//        statusList.add(status2);
//
//        LearningSession given = new LearningSession(statusList);
//
//        // WHEN
//        LearningSession result;
//        result = learningSessionRepository.save(given);
//        Long resultID = result.getId();
//
//        // THEN
//        Assertions.assertTrue(resultID != null && resultID > 0);
//    }
//
//    @Test
//    void findAll() {
//
//        //GIVEN 1
//        //2 CardFront, 2 CardBack
//        TextFile cardFront1 = new TextFile("Front of Card 1","Front1");
//        TextFile cardFront2 = new TextFile("Front of Card 2","Front2");
//        TextFile cardBack1 = new TextFile("Back of Card 1","Back1");
//        TextFile cardBack2 = new TextFile("Back of Card 2","Back2");
//
//        textFileRepository.save(cardFront1);
//        textFileRepository.save(cardFront2);
//        textFileRepository.save(cardBack1);
//        textFileRepository.save(cardBack2);
//
//        //2x Card
//        Card card1 = new Card(cardFront1, cardBack1);
//        Card card2 = new Card(cardFront2, cardBack2);
//
//        cardRepository.save(card1);
//        cardRepository.save(card2);
//
//        //2x CardStatus
//        CardStatus status1 = new CardStatus(card1);
//        CardStatus status2 = new CardStatus(card2);
//
//        cardStatusRepository.save(status1);
//        cardStatusRepository.save(status2);
//
//        //List<CardStatus>
//        List<CardStatus> statusList = new ArrayList<>();
//        statusList.add(status1);
//        statusList.add(status2);
//
//        LearningSession given1 = new LearningSession(statusList);
//
//        //GIVEN 2
//
//        //2 CardFront, 2 CardBack
//        TextFile cardFront3 = new TextFile("Front of Card 3","Front3");
//        TextFile cardFront4 = new TextFile("Front of Card 4","Front4");
//        TextFile cardBack3 = new TextFile("Back of Card 3","Back3");
//        TextFile cardBack4 = new TextFile("Back of Card 4","Back4");
//
//        textFileRepository.save(cardFront3);
//        textFileRepository.save(cardFront4);
//        textFileRepository.save(cardBack3);
//        textFileRepository.save(cardBack4);
//
//        //2x Card
//        Card card3 = new Card(cardFront3, cardBack3);
//        Card card4 = new Card(cardFront4, cardBack4);
//
//        cardRepository.save(card3);
//        cardRepository.save(card4);
//
//        //2x CardStatus
//        CardStatus status3 = new CardStatus(card3);
//        CardStatus status4 = new CardStatus(card4);
//
//        cardStatusRepository.save(status3);
//        cardStatusRepository.save(status4);
//
//        //List<CardStatus>
//        List<CardStatus> statusList2 = new ArrayList<>();
//        statusList2.add(status3);
//        statusList2.add(status4);
//
//        LearningSession given2 = new LearningSession(statusList2);
//
//        List<Long> idsOfPersisted = new ArrayList<>();
//        LearningSession saved1 = learningSessionRepository.save(given1);
//        LearningSession saved2 = learningSessionRepository.save(given2);
//        idsOfPersisted.add(saved1.getId());
//        idsOfPersisted.add(saved2.getId());
//
//        List<LearningSession> result = learningSessionRepository.findAll();
//
//        Assertions.assertTrue(result != null && !result.isEmpty());
//        Assertions.assertFalse(idsOfPersisted.isEmpty());
//    }
}
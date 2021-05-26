package de.aicard.storages;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.card.TextFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the functions of CardStatusRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class CardStatusRepositoryTest
{
    @Autowired
    private CardStatusRepository cardStatusRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TextFileRepository textFileRepository;

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach()
    {
        cardStatusRepository.deleteAll();
        cardRepository.deleteAll();
        textFileRepository.deleteAll();
    }


    @Test
    void save() {
        // GIVEN
        TextFile frontCard = new TextFile("Front of Card");
        TextFile backCard = new TextFile("Back of Card");
        textFileRepository.save(frontCard);
        textFileRepository.save(backCard);

        Card card = new Card(frontCard, backCard);
        cardRepository.save(card);

        CardStatus given = new CardStatus(card);

        // WHEN
        CardStatus result = cardStatusRepository.save(given);
        Long resultID = result.getId();

        // THEN
        Assertions.assertTrue(resultID != null && resultID > 0);
    }

    @Test
    void findAll(){
        //given
        TextFile frontCard1 = new TextFile("Front of Card 1");
        TextFile backCard1 = new TextFile("Back of Card 1");
        TextFile frontCard2 = new TextFile("Front of Card 2");
        TextFile backCard2 = new TextFile("Back of Card 2");
        textFileRepository.save(frontCard1);
        textFileRepository.save(backCard1);
        textFileRepository.save(frontCard2);
        textFileRepository.save(backCard2);

        Card card1 = new Card(frontCard1, backCard1);
        Card card2 = new Card(frontCard2, backCard2);
        cardRepository.save(card1);
        cardRepository.save(card2);

        CardStatus given1 = new CardStatus(card1);
        CardStatus given2 = new CardStatus(card2);

        List<Long> idsOfPersisted = new ArrayList<>();
        CardStatus saved1 = cardStatusRepository.save(given1);
        CardStatus saved2 = cardStatusRepository.save(given2);
        idsOfPersisted.add(saved1.getId());
        idsOfPersisted.add(saved2.getId());

        List<CardStatus> result;
        result = cardStatusRepository.findAll();

        Assertions.assertTrue(result != null && !result.isEmpty());
        Assertions.assertFalse(idsOfPersisted.isEmpty());
    }

}
package de.aicard.storages;

import de.aicard.domains.card.Card;
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
 * Test class for the functions of CardRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class CardRepositoryTest
{
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TextFileRepository textFileRepository;

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {

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

        Card given = new Card(frontCard, backCard);


        // WHEN
        Card result = cardRepository.save(given);
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

        Card given1 = new Card(frontCard1, backCard1);
        Card given2 = new Card(frontCard2, backCard2);


        List<Long> idsOfPersisted = new ArrayList<>();
        Card saved1 = cardRepository.save(given1);
        Card saved2 = cardRepository.save(given2);
        idsOfPersisted.add(saved1.getId());
        idsOfPersisted.add(saved2.getId());

        List<Card> result;
        result = cardRepository.findAll();

        Assertions.assertTrue(result != null && !result.isEmpty());
        Assertions.assertFalse(idsOfPersisted.isEmpty());
    }

}
package de.aicard.storages;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.TextFile;
import de.aicard.domains.learnset.CardList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the functions of CardListRepository
 *
 * @author Daniel Michel
 */
@DataJpaTest
public class CardListRepositoryTest
{
    @Autowired
    private CardListRepository cardListRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TextFileRepository textFileRepository;

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {

        cardListRepository.deleteAll();
        cardRepository.deleteAll();
        textFileRepository.deleteAll();
    }


    @Test
    void save() {
        // GIVEN
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

        ArrayList<Card> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);

        CardList given = new CardList(list);

        // WHEN
        CardList result = cardListRepository.save(given);
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

        ArrayList<Card> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);

        CardList given1 = new CardList(list);

        TextFile frontCard3 = new TextFile("Front of Card 3");
        TextFile backCard3 = new TextFile("Back of Card 3");
        TextFile frontCard4 = new TextFile("Front of Card 4");
        TextFile backCard4 = new TextFile("Back of Card 4");
        textFileRepository.save(frontCard3);
        textFileRepository.save(backCard3);
        textFileRepository.save(frontCard4);
        textFileRepository.save(backCard4);

        Card card3 = new Card(frontCard3, backCard3);
        Card card4 = new Card(frontCard4, backCard4);
        cardRepository.save(card3);
        cardRepository.save(card4);

        ArrayList<Card> list2 = new ArrayList<>();
        list2.add(card3);
        list2.add(card4);

        CardList given2 = new CardList(list2);

        List<Long> idsOfPersisted = new ArrayList<>();
        CardList saved1 = cardListRepository.save(given1);
        CardList saved2 = cardListRepository.save(given2);
        idsOfPersisted.add(saved1.getId());
        idsOfPersisted.add(saved2.getId());

        List<CardList> result;
        result = cardListRepository.findAll();

        Assertions.assertTrue(result != null && !result.isEmpty());
        Assertions.assertFalse(idsOfPersisted.isEmpty());
    }

}
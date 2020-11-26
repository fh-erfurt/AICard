package de.aicard;

import java.util.ArrayList;
import java.util.Arrays;

public class CardListTest {

    public static boolean cardTestOne() {

        Card[] cards = new Card[3];
        cards[0] = new Card("Card1", "Card1", Card.CARDKNOWLEDGELEVEL.NOINFORMATION, 0);
        cards[1] = new Card("Card2", "Card2", Card.CARDKNOWLEDGELEVEL.NOINFORMATION, 0);
        cards[2] = new Card("Card3", "Card3", Card.CARDKNOWLEDGELEVEL.NOINFORMATION, 0);

        ArrayList<Card> cardList = new ArrayList<Card>(Arrays.asList(cards));

        CardList test = new CardList(cards);
        CardList test1 = new CardList();
        for (int i = 0; i < 3; i++) {
            test1.addToList(cards[i]);
        }
        CardList test2 = new CardList(cardList);

        boolean done = false;
        //tests
        for (int i = 0; i < 4; i++) {
            System.out.println("(1) test: " + test.getCurrCard().getCardTextFront());
            test.next();
            if(!done) {
                test.addToList(new Card("Card4", "Card4", Card.CARDKNOWLEDGELEVEL.NOINFORMATION, 0));
                done = true;
            }
        }

        for(int i = 0;i<5;i++){
            System.out.println("(2) test: " + test.getCurrCard().getCardTextFront());
            test.previous();
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("(3) test1: " + test1.getCurrCard().getCardTextFront());
            test1.next();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("(4) test2: " + test2.getCurrCard().getCardTextFront());
            test2.next();
        }
        return true;
    }


}

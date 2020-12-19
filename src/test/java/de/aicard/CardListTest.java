package de.aicard;

import de.aicard.card.CardList;

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
        CardList test3 = new CardList();

        test3.getCurrentCard();


        boolean done = false;
        //tests
        for (int i = 0; i < test.getListLength(); i++) {
            System.out.println("(1) test: " + test.getCurrentCard().getCardTextFront());
            test.next();
            if(!done) {
                test.addToList(new Card("Card4", "Card4", Card.CARDKNOWLEDGELEVEL.NOINFORMATION, 0));
                done = true;
            }
        }
        for(int i = 0;i<test.getListLength();i++){
            System.out.println("(2) test: " + test.getCurrentCard().getCardTextFront());
            test.previous();
        }
        if(true){
            for (int i = 0; i < test1.getListLength(); i++) {
                System.out.println("(3) test1: " + test1.getCurrentCard().getCardTextFront());
                test1.next();
            }
            for (int i = 0; i < test2.getListLength(); i++) {
                System.out.println("(4) test2: " + test2.getCurrentCard().getCardTextFront());
                test2.next();
            }
        }

        test.removeFromList(test.getCurrentCard());
        test.removeFromList(0);

        for (int i = 0;i < test.getListLength();i++) {
            System.out.println("(5) test: " + test.getCurrentCard().getCardTextFront());
            test.next();
        }

        return true;
    }

}

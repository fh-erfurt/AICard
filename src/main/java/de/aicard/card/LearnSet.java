package de.aicard.card;

import de.aicard.card.Card;
import de.aicard.enums.Faculty;
import de.aicard.enums.State;
import de.aicard.learnset.CardList;
import de.aicard.learnset.LearningSession;

public class LearnSet {
    /* This class represents a learn set, which is a accumulation of cards of a specific topic (cardList).
        They have a title, a description and they belong to a specific faculty.
        They are in a specific state, either new, processing, or learned.

     */

    private String title;
    private String description;
    private State state;
    private Faculty faculty;
    private CardList cardList;
    private int currentCard;


    public LearnSet(String title, String description, Faculty faculty){
        /*
        The constructor of LearnSet gets the parameters title, description, faculty and cardList.
        It sets the state of the new LearnSet to NEW and the index of the current card to 0.
         */
        this.title = title;
        this.description = description;
        this.state = State.NEW;
        this.faculty = faculty;
        this.cardList = new CardList();
        this.currentCard = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public State getState() {
        return state;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public CardList getCardList() {
        return cardList;
    }

    public int getCurrentCard() {return currentCard;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setCardList(CardList cardList) {
        this.cardList = cardList;
    }

    public void setCurrentCard(int currentCard) {this.currentCard = currentCard;}


    public void createCard(){
        /*
        with this function, new Cards can be created and added to the cardList of the LearningSet
         */
        Card newCard= new Card();
        cardList.addToList(newCard);
    }

    public void createLearningSession(int noOfCards){
        /*
        This function creates a LearningSession of cards in the learnSet
         */

        LearningSession session = new LearningSession(noOfCards, this.cardList); //TODO adjust the function when Card constructor is known.
    }

}

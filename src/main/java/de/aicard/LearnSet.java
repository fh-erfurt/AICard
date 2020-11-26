package de.aicard;

import java.util.ArrayList;

public class LearnSet {

    //TODO Deklaration der Enums auslagern

    enum State{
        //TODO discuss possible states
        NEW,
        PROCESSING,
        LEARNED
    }


    enum Faculty{
        //TODO changed type of faculty from String to enum. Hope that's ok?
        //TODO add all Faculties of FH Erfurt
        AI,
        SOMETHINGSOCIAL

    }
    private String title;
    private String description;
    private State state;
    private Faculty faculty;
    private ArrayList<Card> cardList = new ArrayList<Card> ();
    private int currentCard;


    public LearnSet(String title, String description, State state, Faculty faculty, ArrayList cardList){
        this.title = title;
        this.description = description;
        this.state = state;
        this.faculty = faculty;
        this.cardList = cardList;
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

    public ArrayList getCardList() {
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

    public void setCardList(ArrayList cardList) {
        this.cardList = cardList;
    }

    public void setCurrentCard(int currentCard) {this.currentCard = currentCard;}


    public void createCard(){
        Card newCard= new Card();
        cardList.add(newCard);
    }

    public void createLearningSession(int noOfCards){

        LearningSession session = new LearningSession(noOfCards); //TODO adjust the function when Card constructor is known.
    }

}

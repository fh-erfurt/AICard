package de.aicard.learnset;


import de.aicard.card.Card;
import de.aicard.card.CardStatus;
import de.aicard.enums.CardKnowledgeLevel;

import java.util.ArrayList;

/**
 * Provides a ArrayList of CardStatus, so that the Account can Learn the Cards. Also safes information
 * about which Card is the Card currently shown and if the Session is active (or already finished).
 *
 * @Author Daniel Michel
 */

public class LearningSession
{
    //attributes
    /**
     * The Card that is currently shown.
     */
    private int currentCard;
    /**
     * The List of Cards the Account is learning in this session, combined with the Account-specific
     * information of the Cards, saved in an ArrayList of CardStatus.
     */
    private ArrayList<CardStatus> cardStatusList;
    /**
     * Information, wheather the Session is active (there is at least one other card to learn in the List)
     */
    private boolean isActive;


    /**
     * Constructor of LearningSession
     *
     * sets currentCard to 0 and isActive to true.
     *
     * @param _cardStatusList The List for the LearningSession.
     */
    public LearningSession(ArrayList<CardStatus> _cardStatusList){
        this.cardStatusList = _cardStatusList;
        this.currentCard = 0;
        this.isActive = true;
    }

    //setter & getter
    public void setCurrentCard(int _currentCard){this.currentCard = _currentCard;}
    public int getCurrentCard(){return this.currentCard;}
    public boolean getIsActive(){return this.isActive;}



    public void setCardStatusList(ArrayList<CardStatus> cardStatusList) {
        this.cardStatusList = cardStatusList;
    }

    public ArrayList<CardStatus> getCardStatusList() {
        return cardStatusList;
    }

    //methods

    /**
     * Method called if the Card was known by the User.
     *
     * It increases the CardKnowledgeLevel and turns to the next Card.
     */
    public void cardKnown()
    {
        this.cardStatusList.get(this.currentCard).increaseCardKnowledgeLevel();
        this.next();
    }

    /**
     * Method called if the Card was not known by the User.
     *
     * It decreases the CardKnowledgeLevel and turns to the next Card.
     */
    public void cardUnKnown()
    {
        this.cardStatusList.get(this.currentCard).decreaseCardKnowledgeLevel();
        this.next();
    }

    /**
     * Method called to go to next Card in the LearningSession.
     *
     * Checks, if the current Card was the last Card of the Session. If so, it sets isActive to false.
     * If not, it increases currentCard by one.
     */
    public void next(){
        if(this.currentCard == this.cardStatusList.size()){
            this.isActive = false;
        }
        else{
            this.currentCard++;
        }
    }

}

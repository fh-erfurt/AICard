package de.aicard.learnset;


import de.aicard.card.Card;
import de.aicard.card.CardStatus;
import de.aicard.enums.CardKnowledgeLevel;

import java.util.ArrayList;

public class LearningSession
{
    //attributes
    private int currentCard;
    private ArrayList<CardStatus> cardStatusList;
    private boolean isActive;



    public LearningSession(ArrayList<CardStatus> _cardStatusList){
        this.cardStatusList = _cardStatusList;
        this.currentCard = 0;
        this.isActive = true;
    }

    //setter & getter
    public void setCurrentCard(int _currentCard){this.currentCard = _currentCard;}
    public int getCurrentCard(){return this.currentCard;}
    public boolean getIsActive(){return this.isActive;}

   // public void setCardList(CardList _cardList){this.cardList = _cardList;}
    //public CardList getCardList(){return this.cardList;}

    public void setCardStatusList(ArrayList<CardStatus> cardStatusList) {
        this.cardStatusList = cardStatusList;
    }

    public ArrayList<CardStatus> getCardStatusList() {
        return cardStatusList;
    }

    //methods


    public void cardKnown()
    {
        this.cardStatusList.get(this.currentCard).increaseCardKnowledgeLevel();
        this.currentCard++;
        if(this.currentCard == this.cardStatusList.size()){
            this.isActive = false;
        }

    }

    public void cardUnKnown()
    {
        this.cardStatusList.get(this.currentCard).decreaseCardKnowledgeLevel();
        this.currentCard++;
        if(this.currentCard == this.cardStatusList.size()){
            this.isActive = false;
        }
    }

}

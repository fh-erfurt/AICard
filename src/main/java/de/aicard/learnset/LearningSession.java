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
    //todo kann weg.
    private void riseCardKnowledgeLevel() //rises CardKnowledgeLevel of the currentCard
    {
        CardStatus currentStatus=this.cardStatusList.get(this.currentCard);
        switch (currentStatus.getCardKnowledgeLevel())
        {
            case NOINFORMATION:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.SOMEINFORMATION);
                break;

            case SOMEINFORMATION:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.KNOW);
                break;

            case KNOW:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.KNOWWELL);
                break;

            case KNOWWELL:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.KNOWVERYWELL);
                break;

            case KNOWVERYWELL:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.KNOWVERYWELL);
                break;

            default:
                System.out.println("Error:riseCardKnowledgeLevel() failed");
                break;
      }
    }

    //todo kann weg...
    private void lowerCardKnowledgeLevel() //lowers CardKnowledgeLevel of the currentCard
    {
        CardStatus currentStatus=this.cardStatusList.get(this.currentCard);
        switch (currentStatus.getCardKnowledgeLevel())
        {
            case NOINFORMATION:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.NOINFORMATION);
                break;

            case SOMEINFORMATION:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.NOINFORMATION);
                break;

            case KNOW:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.SOMEINFORMATION);
                break;

            case KNOWWELL:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.KNOW);
                break;

            case KNOWVERYWELL:
                currentStatus.setCardKnowledgeLevel(CardKnowledgeLevel.KNOWWELL);
                break;

            default:
                System.out.println("Error:lowerCardKnowledgeLevel() failed");
                break;

        }
    }

    public void cardKnown()
    {
        //TODO Funktion von CardStatus aufrufen
        riseCardKnowledgeLevel();
        this.currentCard++;
        if(this.currentCard == this.cardStatusList.size()){
            this.isActive = false;
        }

    }

    public void cardUnKnown()
    {
        //ToDO Funktion von CardStatus aufrufen
        lowerCardKnowledgeLevel();
        this.currentCard++;
        if(this.currentCard == this.cardStatusList.size()){
            this.isActive = false;
        }
    }

}

package de.aicard.learnset;


import de.aicard.card.Card;
import de.aicard.card.CardStatus;
import de.aicard.enums.CardKnowledgeLevel;

import java.util.ArrayList;

public class LearningSession
{
    //attributes
    private int currentCard;
   // private CardList cardList;
    private ArrayList<CardStatus> cardStatusList;


    //Constructer
    //public LearningSession(CardList _cardList)
    //{
        //this.currentCard = 0;
    //    cardList = _cardList;
   // }

    public LearningSession(ArrayList<CardStatus> _cardStatusList){
        this.cardStatusList = _cardStatusList;
        this.currentCard = 0;
    }

    //setter & getter
    public void setCurrentCard(int _currentCard){this.currentCard = _currentCard;}
    public int getCurrentCard(){return this.currentCard;}

   // public void setCardList(CardList _cardList){this.cardList = _cardList;}
    //public CardList getCardList(){return this.cardList;}

    public void setCardStatusList(ArrayList<CardStatus> cardStatusList) {
        this.cardStatusList = cardStatusList;
    }

    public ArrayList<CardStatus> getCardStatusList() {
        return cardStatusList;
    }

    //methods

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
    //private Card searchForCardKnowledgeLevel() //TODO suche nach CardKnowledgeLevel - Listarray

    //public void createCardList(int _numOfCards, CardList _cardList)
    //{
      //  this.cardList = new CardList();
        //int numberOfCardsOnSessionList = 0;
        //while(_numOfCards>numberOfCardsOnSessionList)
        //{
          //  //TODO hier soll gesucht werden nach Karten von NOINFORMATION bis KNOWVERYWELL und zu der CardList des Objekts hinzugefügt werden
        //}
    //}

    public void cardKnown()
    {
        riseCardKnowledgeLevel();
        this.currentCard++;
        //TODO check if it is the last card in the list. if it is -> stop learning session. Else: currentCard++
    }

    public void cardUnKnown()
    {
        lowerCardKnowledgeLevel();
        this.currentCard++;
        //TODO siehe cardKnown()
    }

}

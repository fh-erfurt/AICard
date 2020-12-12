package de.aicard;


public class LearningSession
{
    //attributes
    //private int currentCard;
    private CardList cardList;


    //Constructer
    public LearningSession(int _numOfCards, CardList _cardList)
    {
        //this.currentCard = 0;
        createCardList(_numOfCards, _cardList);
    }

    //setter & getter
    //public void setCurrentCard(int _currentCard){this.currentCard = _currentCard;}
    //public int getCurrentCard(){return this.currentCard;}

    public void setCardList(CardList _cardList){this.cardList = _cardList;}
    public CardList getCardList(){return this.cardList;}

    //methods

    private void riseCardKnowledgeLevel() //rises CardKnowledgeLevel of the currentCard
    {
        Card currentCard=this.cardList.getCurrentCard();
        switch (currentCard.getCardKnowledgeLevel())
        {
            case NOINFORMATION:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.SOMEINFORMATION);
                break;

            case SOMEINFORMATION:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.KNOW);
                break;

            case KNOW:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.KNOWWELL);
                break;

            case KNOWWELL:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.KNOWVERYWELL);
                break;

            case KNOWVERYWELL:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.KNOWVERYWELL);
                break;

            default:
                System.out.println("Error:riseCardKnowledgeLevel() failed");
                break;
      }
    }

    private void lowerCardKnowledgeLevel() //lowers CardKnowledgeLevel of the currentCard
    {
        Card currentCard=this.cardList.getCurrentCard();
        switch (currentCard.getCardKnowledgeLevel())
        {
            case NOINFORMATION:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.NOINFORMATION);
                break;

            case SOMEINFORMATION:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.NOINFORMATION);
                break;

            case KNOW:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.SOMEINFORMATION);
                break;

            case KNOWWELL:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.KNOW);
                break;

            case KNOWVERYWELL:
                currentCard.setCardKnowledgeLevel(Enums.CARDKNOWLEDGELEVEL.KNOWWELL);
                break;

            default:
                System.out.println("Error:lowerCardKnowledgeLevel() failed");
                break;

        }
    }
    //private Card searchForCardKnowledgeLevel() //TODO suche nach CardKnowledgeLevel - Listarray

    public void createCardList(int _numOfCards, CardList _cardList)
    {
        this.cardList = new CardList();
        int numberOfCardsOnSessionList = 0;
        while(_numOfCards>numberOfCardsOnSessionList)
        {
            //TODO hier soll gesucht werden nach Karten von NOINFORMATION bis KNOWVERYWELL und zu der CardList des Objekts hinzugefügt werden
        }
    }

    public void cardKnown()
    {
        riseCardKnowledgeLevel();
        this.cardList.next();
    }

    public void cardUnKnown()
    {
        lowerCardKnowledgeLevel();
        this.cardList.next();
    }

}

package de.aicard;


public class LearningSession {

    //attributes
    private int currentCard;
    private Card[] cardList;
    private Skipper canSkip;


    //Constructer
    public LearningSession(int _numOfCards) {
      this.currentCard = 0;
      this.cardList = new Card[_numOfCards];
      this.canSkip = new Skipper();
    }

    //setter & getter
    public void setCurrentCard(int _currentCard){this.currentCard = _currentCard;}
    public int getCurrentCard(){return this.currentCard;}

    public void setCardList(Card[] _cardList){this.cardList = _cardList;}
    public Card[] getCardList(){return this.cardList;}

    public void setCanSkip(Skipper _canSkip){this.canSkip = _canSkip;}      //TODO Brauch der Skipper getter und setter?
    public Skipper getCanSkip(){return this.canSkip;}

    //methods
    public void createCardList(int _numOfCards){} //TODO ich weiß nicht mehr was die Funktionen macht. brauche Dokumentation::habe es in den Constructor hinzugefügt
    public void cardKnown(){}
    public void cardUnKnown(){}
}
//TODO Erkennt andere Klassen nicht im Package
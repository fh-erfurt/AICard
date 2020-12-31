package de.aicard.learnset;

import de.aicard.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardList {
    //attributes
    private List<de.aicard.card.Card> cardList;
    private int ListIndex;

    //Constructor
    public CardList(){
        cardList = new ArrayList<de.aicard.card.Card>();
        ListIndex = 0;
    }
    public CardList(de.aicard.card.Card[] _cards){
        cardList = new ArrayList<de.aicard.card.Card>();
        cardList.addAll(Arrays.asList(_cards));
        ListIndex = 0;
    }
    public CardList(ArrayList<de.aicard.card.Card> _arrList){
        cardList = new ArrayList<de.aicard.card.Card>();
        cardList = _arrList;
        ListIndex = 0;
    }
    //functions

    public void addToList(de.aicard.card.Card _card){

        cardList.add(_card);
    }
    public void removeFromList(de.aicard.card.Card _card){
        cardList.remove(_card);
    }

    public void removeFromList(int _index){
        cardList.remove(_index);
    }

    public Card getCurrentCard(){
        if(cardList.get(ListIndex) != null){
            return cardList.get(ListIndex);
        }
        else {
            return null;
        }
    }

    public Card getCardByIndex(int _index){
        return cardList.get(_index);
    }

    public int getListLength(){
        return cardList.size();
    }

    public void next(){
        if (ListIndex < cardList.size() - 1){
           ListIndex++;
        }
    }
    public void previous(){
        if (ListIndex > 0){
            ListIndex--;
        }
    }

}

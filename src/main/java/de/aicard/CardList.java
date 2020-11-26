package de.aicard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardList {
    //attributes
    private List<Card> cardList;
    private int ListIndex;

    //Constructor
    public CardList(){
        cardList = new ArrayList<Card>();
        ListIndex = 0;
    }
    public CardList(Card[] _cards){
        cardList = new ArrayList<Card>();
        cardList.addAll(Arrays.asList(_cards));
        ListIndex = 0;
    }
    public CardList(ArrayList<Card> _arrList){
        cardList = new ArrayList<Card>();
        cardList = _arrList;
        ListIndex = 0;
    }

    //functions
    public void addToList(Card _card){
        cardList.add(_card);
    }
    public Card getCurrCard(){
        return cardList.get(ListIndex);
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

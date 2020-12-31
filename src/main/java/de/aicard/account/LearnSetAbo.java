package de.aicard.account;

import de.aicard.learnset.CardList;
import de.aicard.card.CardStatus;
import de.aicard.enums.CardKnowledgeLevel;
import de.aicard.enums.State;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearningSession;

import java.util.ArrayList;

public class LearnSetAbo
{
    private ArrayList<CardStatus> m_cardStatus;
    private State m_learnSetStatus;
    private LearnSet m_learnSet;

    public State getM_learnSetStatus() {
        return m_learnSetStatus;
    }

    public void setM_learnSetStatus(State m_learnSetStatus) {
        this.m_learnSetStatus = m_learnSetStatus;
    }

    public LearnSet getM_learnSet() {
        return m_learnSet;
    }

    public void setM_learnSet(LearnSet m_learnSet) {
        this.m_learnSet = m_learnSet;
    }



    public ArrayList<CardStatus> getM_cardStatus() {
        return m_cardStatus;
    }

    public void setM_cardStatus(ArrayList<CardStatus> m_cardStatus) {
        this.m_cardStatus = m_cardStatus;
    }

    public LearnSetAbo(LearnSet _learnSet){
        m_learnSet = _learnSet;
        m_learnSetStatus = State.NEW;
        m_cardStatus = new ArrayList<CardStatus>();
        //TODO Add initialization of cardStatus to constructor, when CardStatus is implemented
    }

    private CardList getCardOfKnowledgeLevel(CardKnowledgeLevel level){
        //TODO check when CardStatus is implemented
        CardList result = new CardList();
        for(int i=0; i<(this.m_cardStatus.size()); i++){
            CardStatus status = this.m_cardStatus.get(i);
            if (status.getCardKnowledgeLevel() == level){
                result.addToList(status.getCard());
            }
        }
        return result;
    }

    private CardList createCardListForSession(int _numOfCards){
        CardList resultCardList = new CardList();

        for (CardKnowledgeLevel level : CardKnowledgeLevel.values()) {
            CardList lowestLevelList = getCardOfKnowledgeLevel(level);
            if(lowestLevelList != null){
                for(int i = 0; i< lowestLevelList.getListLength(); i++){
                    while (resultCardList.getListLength()<_numOfCards){
                        resultCardList.addToList(lowestLevelList.getCardByIndex(i)); //TODO CardList braucht eine allgemeine get Methode für Elemente der Liste
                    }
                }
            }
            if (resultCardList.getListLength()==_numOfCards) break;
        }

        return resultCardList;
    }

    public LearningSession createLearningSession(int _numOfCards){

        CardList sessionList = createCardListForSession(_numOfCards);
        //TODO LearningSession needs constructor with only a cardList as parameter, because the list can only be created here

        return new LearningSession(sessionList);


    }
}

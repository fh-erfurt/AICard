package de.aicard.account;

import de.aicard.card.Card;
import de.aicard.learnset.CardList;
import de.aicard.card.CardStatus;
import de.aicard.enums.CardKnowledgeLevel;
import de.aicard.enums.State;
import de.aicard.card.LearnSet;
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
        for(int i = 0; i<m_learnSet.getCardList().getListLength(); i++){
            m_cardStatus.add(new CardStatus(m_learnSet.getCardList().getCardByIndex(i)));
        }
    }

    private ArrayList<CardStatus> getCardStatusOfKnowledgeLevel(CardKnowledgeLevel level){
        //TODO check when CardStatus is implemented
        ArrayList<CardStatus> result = new ArrayList<CardStatus>();
        for(int i=0; i<(this.m_cardStatus.size()); i++){
            CardStatus status = this.m_cardStatus.get(i);
            if (status.getCardKnowledgeLevel() == level){
                result.add(status);
            }
        }
        return result;
    }

    private ArrayList<CardStatus> createCardStatusListForSession(int _numOfCards){
        ArrayList<CardStatus> resultCardStatusList = new ArrayList<CardStatus>();

        for (CardKnowledgeLevel level : CardKnowledgeLevel.values()) {
            ArrayList<CardStatus> lowestLevelList = getCardStatusOfKnowledgeLevel(level);
            if(lowestLevelList != null){
                for(int i = 0; i< lowestLevelList.size(); i++){
                    while (resultCardStatusList.size()<_numOfCards){
                        resultCardStatusList.add(lowestLevelList.get(i)); //TODO CardList braucht eine allgemeine get Methode für Elemente der Liste
                    }
                }
            }
            if (resultCardStatusList.size()==_numOfCards) break;
        }

        return resultCardStatusList;
    }

    public LearningSession createLearningSession(int _numOfCards){

        ArrayList<CardStatus> sessionList = createCardStatusListForSession(_numOfCards);
        //TODO LearningSession needs constructor with only a cardList as parameter, because the list can only be created here

        return new LearningSession(sessionList);


    }
}

package de.aicard.account;

import de.aicard.card.Card;
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
    private int m_evaluation;
    //TODO create setter/getter die getEvaluation etc aufrufen von LearnSet und Delete im Learnset...

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

    public int get_evaluation(){return this.m_evaluation;}

    public void set_evaluation(int _evaluation){
        if(this.m_evaluation != -1){
            this.m_learnSet.deleteEvaluation(this.m_evaluation);
        }
        this.m_evaluation = _evaluation;
        this.m_learnSet.addEvaluation(_evaluation);
    }

    public void delete_evaluation(){
        if(this.m_evaluation != -1){
            this.m_learnSet.deleteEvaluation(this.m_evaluation);
            this.m_evaluation = -1;
        }
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
        m_evaluation = -1;
        for(int i = 0; i<m_learnSet.getCardList().getListLength(); i++){
            try {
                m_cardStatus.add(new CardStatus(m_learnSet.getCardList().getCardByIndex(i)));
            }
            // TODO was machen wir mit den Exceptions? ausgeben? weiterwerfen?
            catch(NullPointerException e){
                System.out.println(e); // sorry für rumschieren :D aber wir sollen keine Ausgabe machen
            }                          // am besten eine Logger ausgabe verwenden denke ich
            catch(Exception e){        // mfg Martin
                System.out.println();
            }
        }
    }

    private ArrayList<CardStatus> getCardStatusOfKnowledgeLevel(CardKnowledgeLevel level){
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
                        resultCardStatusList.add(lowestLevelList.get(i));
                    }
                }
            }
            if (resultCardStatusList.size()==_numOfCards) break;
        }

        return resultCardStatusList;
    }

    public LearningSession createLearningSession(int _numOfCards){

        ArrayList<CardStatus> sessionList = createCardStatusListForSession(_numOfCards);
        return new LearningSession(sessionList);


    }
}

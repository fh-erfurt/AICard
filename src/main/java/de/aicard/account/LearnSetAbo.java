package de.aicard.account;

import de.aicard.card.Card;
import de.aicard.learnset.CardList;
import de.aicard.card.CardStatus;
import de.aicard.enums.CardKnowledgeLevel;
import de.aicard.enums.State;
import de.aicard.learnset.LearnSet;
import de.aicard.learnset.LearningSession;

import java.util.ArrayList;

/**
 * Provides a LearnSet with further, account-specific information and is thus always embedded
 * into an account. The further information provided by the LearnSetAbo are an ArrayList of
 * CardStatus to safe the state of learning of the user for all the Cards in the LearnSet,
 * the state of the LearnSet, the LearnSet itself and the evaluation the Account has given
 * the LearnSet.
 *
 * @Author Daniel Michel
 */

public class LearnSetAbo
{
    /**
     * The member-variable m_cardStatus is an ArrayList of CardStatus. It contains a CardStatus
     * for each Card in the LearnSet m_learnSet.
     */
    private ArrayList<CardStatus> m_cardStatus;
    /**
     * The status of the LearnSet of the Account.
     */
    private State m_learnSetStatus;
    /**
     * The LearnSet the Account subscribed to.
     */
    private LearnSet m_learnSet;
    /**
     * The evaluation the Account has given to the LearnSet. If the Account has not yet given an
     * evaluation to the LearnSet, the variable m_evaluation has the value -1.
     */
    private int m_evaluation;

    /**
     * Constructor of the LearnSetAbo
     *
     *
     *
     * @param _learnSet The LearnSet to which the Account subscribes.
     */
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
                System.out.println(e); // sorry für rumschmieren :D aber wir sollen keine Ausgabe machen
            }                          // am besten eine Logger ausgabe verwenden denke ich
            catch(Exception e){        // mfg Martin //ist auch erstmal ein dummy -> siehe ToDO. Tendiere
                                        //zu weiterwerfen und erst wirklich "bei Nutzung" behandeln.
                System.out.println();   //TODO dummy?!
            }
        }
    }

    //getter

    public State getM_learnSetStatus() {
        return m_learnSetStatus;
    }

    public LearnSet getM_learnSet() {
        return m_learnSet;
    }

    public int get_evaluation(){
        return this.m_evaluation;
    }

    public ArrayList<CardStatus> getM_cardStatus() {
        return m_cardStatus;
    }

    //setter

    public void setM_learnSetStatus(State m_learnSetStatus) {
        this.m_learnSetStatus = m_learnSetStatus;
    }

    public void setM_learnSet(LearnSet m_learnSet) {
        this.m_learnSet = m_learnSet;
    }

    public void setM_cardStatus(ArrayList<CardStatus> m_cardStatus) {
        this.m_cardStatus = m_cardStatus;
    }

    /**
     * Sets the evaluation of the Account for the corresponding LearnSet.
     *
     * First checks, if there is already an evaluation of this account for this LearnSet.
     * If so, this evaluation is deleted. Then, the member-variable m_evaluation is set to the
     * value of the new evaluation, and the evaluation is added to the LearnSet.
     *
     * @param _evaluation Evaluation, the Account wants to give to the LearnSet.
     */
    public void set_evaluation(int _evaluation){
        if(this.m_evaluation != -1){
            this.m_learnSet.deleteEvaluation(this.m_evaluation);
        }
        this.m_evaluation = _evaluation;
        this.m_learnSet.addEvaluation(_evaluation);
    }

    /**
     * Deletes the evaluation of the Account for the corresponding LearnSet.
     */
    public void delete_evaluation(){
        if(this.m_evaluation != -1){
            this.m_learnSet.deleteEvaluation(this.m_evaluation);
            this.m_evaluation = -1;
        }
    }

    /**
     * Method to get all Cards of the LearnSet with a specific CardKnowledgeLevel.
     *
     * Checks the KnowLedgeLevel of all Cards in the List. Puts the CardStatus with the
     * wanted level in a new ArrayList of CardStatus. Returns this List.
     *
     * @param level The CardKnowledgeLevel we are looking for
     * @return A List of all CardStatus in the LearnSetAbo with the CardKnowledgeLevel level
     */
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

    /**
     * Creates an ArrayList of CardStatus with a specific Number of Cards.
     *
     * Checks the CardStatus List of the Abo for one CardKnowledgeLevel after another
     * (lowest level first) and fills a new ArrayList of CardStatus with the CardStatus of the
     * lowest level, until there are as many CardStatus in the List as wanted.
     *
     * @param _numOfCards The number of CardStatus we want in the returned ArrayList.
     * @return List of the size _numOfCards, consists of the CardStatus in the LearnSetAbo with the
     * lowest CardKnowledgeLevel.
     */
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

    /**
     * Creates a LearningSession with a specific number of Cards.
     *
     * First, it gets a List of the CardStatus in the LearnSetAbo of the lowest CardKnowledgeLevel.
     * Then, it constructs a LearnSet of this CardStatus ArrayList.
     *
     * @param _numOfCards how many Cards should be asked for in the LearningSession
     * @return  The created LearningSession.
     */
    public LearningSession createLearningSession(int _numOfCards){

        ArrayList<CardStatus> sessionList = createCardStatusListForSession(_numOfCards);
        return new LearningSession(sessionList);


    }
}

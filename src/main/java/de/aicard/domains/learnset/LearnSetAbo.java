package de.aicard.domains.learnset;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.CardKnowledgeLevel;
import de.aicard.domains.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a LearnSet with further, account-specific information and is thus always embedded
 * into an account. The further information provided by the LearnSetAbo are an ArrayList of
 * CardStatus to safe the state of learning of the user for all the Cards in the LearnSet,
 * the state of the LearnSet, the LearnSet itself and the evaluation the Account has given
 * the LearnSet.
 *
 * @author Daniel Michel
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LearnSetAbo extends BaseEntity
{
    /**
     * The member-variable m_cardStatus is an ArrayList of CardStatus. It contains a CardStatus
     * for each Card in the LearnSet m_learnSet.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CardStatus> cardStatus;
    //TODO: kann das weg?
    /**
     * The status of the LearnSet of the Account.
     */
    private State learnSetStatus;
    /**
     * The LearnSet the Account subscribed to.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private LearnSet learnSet;
    
    /**
     * The evaluation the Account has given to the LearnSet. If the Account has not yet given an
     * evaluation to the LearnSet, the variable m_evaluation has the value -1.
     */
    private int evaluation;
    
    @OneToOne(cascade = CascadeType.ALL)
    private LearningSession learningSession;

    /**
     * Constructor of the LearnSetAbo
     *
     * @param _learnSet The LearnSet to which the Account subscribes.
     */
    public LearnSetAbo(LearnSet _learnSet) throws NullPointerException, Exception
    {
        this.learnSet = _learnSet;
        this.learnSetStatus = State.NEW;
        this.cardStatus = new ArrayList<>();
        this.evaluation = -1;

        for(int i = 0; i<learnSet.getCardList().getListLength(); i++)
        {
            cardStatus.add(new CardStatus(learnSet.getCardList().getCardByIndex(i)));
        }

    }

    //getter + setter
    
    /**
     * Sets the evaluation of the Account for the corresponding LearnSet.
     *
     * First checks, if there is already an evaluation of this account for this LearnSet.
     * If so, this evaluation is deleted. Then, the member-variable m_evaluation is set to the
     * value of the new evaluation, and the evaluation is added to the LearnSet.
     *
     * @param _evaluation Evaluation, the Account wants to give to the LearnSet.
     */
    public void set_evaluation(int _evaluation)
    {
        if(this.evaluation != -1)
        {
            this.learnSet.deleteEvaluation(this.evaluation);
        }
        this.evaluation = _evaluation;
        this.learnSet.addEvaluation(evaluation);
    }

    /**
     * Deletes the evaluation of the Account for the corresponding LearnSet.
     */
    public void delete_evaluation()
    {
        if(this.evaluation != -1)
        {
            this.learnSet.deleteEvaluation(this.evaluation);
            this.evaluation = -1;
        }
    }

    /**
     * Method to get all Cards of the LearnSet with a specific CardKnowledgeLevel.
     *
     * Checks the KnowLedgeLevel of all Cards in the List. Puts the CardStatus with the
     * wanted level in a new ArrayList of CardStatus. Returns this List.
     *
     * @param _level The CardKnowledgeLevel we are looking for
     * @return A List of all CardStatus in the LearnSetAbo with the CardKnowledgeLevel level
     */
    private List<CardStatus> getCardStatusOfKnowledgeLevel(CardKnowledgeLevel _level)
    {
        List<CardStatus> result = new ArrayList<>();
        for(int i=0; i<(this.cardStatus.size()); i++){
            if (cardStatus.get(i).getCardKnowledgeLevel() == _level){
                result.add(cardStatus.get(i));
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
    private List<CardStatus> createCardStatusListForSession(int _numOfCards)
    {
        List<CardStatus> resultCardStatusList = new ArrayList<>();

        for (CardKnowledgeLevel level : CardKnowledgeLevel.values())
        {
            List<CardStatus> lowestLevelList = getCardStatusOfKnowledgeLevel(level);
            if(lowestLevelList != null)
            {
                for(int i = 0; i< lowestLevelList.size(); i++)
                {
                    if (resultCardStatusList.size()<_numOfCards)
                    {
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
    public LearningSession createLearningSession(int _numOfCards)
    {
        
        List<CardStatus> sessionList = createCardStatusListForSession(_numOfCards);
        LearningSession newLearningSession = new LearningSession(sessionList);
        this.learningSession = newLearningSession;
        return newLearningSession;
    }


    public boolean containsCard(Card card){
        for (CardStatus cardStatus:this.cardStatus) {
            if(cardStatus.getCard().equals(card)){
                return true;
            }
        }
        return false;
    }

    public void removeLearnSet(){
        this.learnSet = null;
    }
}

package de.aicard.domains.learnset;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.enums.CardKnowledgeLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a LearnSet with further, account-specific information and is thus always embedded
 * into an account. The further information provided by the LearnSetAbo are an ArrayList of
 * CardStatus to safe the state of learning of the user for all the Cards in the LearnSet,
 * the LearnSet itself and the evaluation the Account has given the LearnSet.
 * primary place to save learnsets
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
     * The member-variable ardStatus is an ArrayList of CardStatus. It contains a CardStatus
     * for each Card in the LearnSet m_learnSet.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CardStatus> cardStatus;
    
    /**
     * The LearnSet the Account subscribed to.
     */
    @ManyToOne
    private LearnSet learnSet;
    
    /**
     * The evaluation the Account has given to the LearnSet. If the Account has not yet given an
     * evaluation to the LearnSet, the variable m_evaluation has the value -1.
     * <p>
     * is redundant because evaluation is calculated on the fly by calculating averages of comment recomondations
     */
    private int evaluation;
    
    @OneToOne(cascade = CascadeType.ALL)
    private LearningSession learningSession;
    
    /**
     * Constructor of the LearnSetAbo
     *
     * @param learnSet The LearnSet to which the Account subscribes.
     */
    public LearnSetAbo(LearnSet learnSet) throws Exception
    {
        this.learnSet = learnSet;
        this.cardStatus = new ArrayList<>();
        this.evaluation = - 1;
        
        for (int i = 0; i < this.learnSet.getCardList().getListLength(); i++)
        {
            cardStatus.add(new CardStatus(this.learnSet.getCardList().getCardByIndex(i)));
        }
        
    }
    
    //getter + setter
    
    /**
     * Method to get all Cards of the LearnSet with a specific CardKnowledgeLevel.
     * <p>
     * Checks the KnowLedgeLevel of all Cards in the List. Puts the CardStatus with the
     * wanted level in a new ArrayList of CardStatus. Returns this List.
     *
     * @param level The CardKnowledgeLevel we are looking for
     * @return A List of all CardStatus in the LearnSetAbo with the CardKnowledgeLevel level
     */
    private List<CardStatus> getCardStatusOfKnowledgeLevel(CardKnowledgeLevel level)
    {
        List<CardStatus> result = new ArrayList<>();
        for (CardStatus status : this.cardStatus)
        {
            if (status.getCardKnowledgeLevel() == level)
            {
                result.add(status);
            }
        }
        return result;
    }
    
    /**
     * Creates an ArrayList of CardStatus with a specific Number of Cards.
     * <p>
     * Checks the CardStatus List of the Abo for one CardKnowledgeLevel after another
     * (lowest level first) and fills a new ArrayList of CardStatus with the CardStatus of the
     * lowest level, until there are as many CardStatus in the List as wanted.
     *
     * @param numOfCards The number of CardStatus we want in the returned ArrayList.
     * @return List of the size _numOfCards, consists of the CardStatus in the LearnSetAbo with the
     * lowest CardKnowledgeLevel.
     */
    private List<CardStatus> createCardStatusListForSession(int numOfCards)
    {
        List<CardStatus> resultCardStatusList = new ArrayList<>();
        
        for (CardKnowledgeLevel level : CardKnowledgeLevel.values())
        {
            List<CardStatus> lowestLevelList = getCardStatusOfKnowledgeLevel(level);
            for (CardStatus status : lowestLevelList)
            {
                if (resultCardStatusList.size() < numOfCards)
                {
                    resultCardStatusList.add(status);
                }
            }
            if (resultCardStatusList.size() == numOfCards)
            {
                break;
            }
        }
        
        return resultCardStatusList;
    }
    
    /**
     * Creates a LearningSession with a specific number of Cards.
     * <p>
     * First, it gets a List of the CardStatus in the LearnSetAbo of the lowest CardKnowledgeLevel.
     * Then, it constructs a LearnSet of this CardStatus ArrayList.
     *
     * @param numOfCards how many Cards should be asked for in the LearningSession
     * @return The created LearningSession.
     */
    public LearningSession createLearningSession(int numOfCards)
    {
        
        List<CardStatus> sessionList = createCardStatusListForSession(numOfCards);
        LearningSession newLearningSession = new LearningSession(sessionList);
        this.learningSession = newLearningSession;
        return newLearningSession;
    }
    
    /**
     * removes a cardStatus with the given card from the cardStatusList
     *
     * @param card /
     * @return CardStatus which is removed to be deleted
     */
    public CardStatus removeCardStatusByCard(Card card)
    {
        CardStatus erg = null;
        for (int i = this.cardStatus.size() - 1; i >= 0; i--)
        {
            if (this.cardStatus.get(i).getCard().equals(card))
            {
                erg = cardStatus.get(i);
                if (this.learningSession != null && this.learningSession.getCardStatusList() != null)
                {
                    this.learningSession.getCardStatusList().remove(erg);
                }
                this.cardStatus.remove(i);
            }
        }
        return erg;
    }
}

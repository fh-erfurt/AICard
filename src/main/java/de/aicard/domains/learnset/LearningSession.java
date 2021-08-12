package de.aicard.domains.learnset;

import de.aicard.domains.card.CardStatus;
import de.aicard.domains.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a ArrayList of CardStatus, so that the Account can Learn the Cards. Also safes information
 * about which Card is the Card currently shown and if the Session is active (or already finished).
 *
 * @author Daniel Michel
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class LearningSession extends BaseEntity
{
    //attributes
    /**
     * The Card that is currently shown.
     */
    private int currentCard;
    /**
     * The List of Cards the Account is learning in this session, combined with the Account-specific
     * information of the Cards, saved in an ArrayList of CardStatus.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<CardStatus> cardStatusList;
    /**
     * Information, wheather the Session is active (there is at least one other card to learn in the List)
     */
    private boolean isActive;
    
    
    /**
     * Constructor of LearningSession
     *
     * sets currentCard to 0 and isActive to true.
     *
     * @param _cardStatusList The List for the LearningSession.
     */
    public LearningSession(List<CardStatus> _cardStatusList)
    {
        this.cardStatusList = _cardStatusList;
        this.currentCard = 0;
        this.isActive = true;
    }

    //setter & getter

    public List<CardStatus> getCardStatusList() throws NullPointerException
    {
        if (this.cardStatusList == null)
        {
            throw new NullPointerException("CardStatusList was not set.");
        }
        return this.cardStatusList;
    }

    //methods

    /**
     * Method called if the Card was known by the User.
     *
     * It increases the CardKnowledgeLevel and turns to the next Card.
     */
    public void cardKnown()
    {
        System.out.println("currrentCardKnown:"+this.currentCard);
        this.cardStatusList.get(this.currentCard).increaseCardKnowledgeLevel();
        this.next();
    }

    /**
     * Method called if the Card was not known by the User.
     *
     * It decreases the CardKnowledgeLevel and turns to the next Card.
     */
    public void cardUnknown()
    {
        this.cardStatusList.get(this.currentCard).decreaseCardKnowledgeLevel();
        this.next();
    }

    /**
     * Method called to go to next Card in the LearningSession.
     *
     * Checks, if the current Card was the last Card of the Session. If so, it sets isActive to false.
     * If not, it increases currentCard by one.
     */
    private void next()
    {
        if(this.currentCard == (this).cardStatusList.size()-1)
        {
            this.isActive = false;
        }
        else {
            this.currentCard++;
        }
    }


}

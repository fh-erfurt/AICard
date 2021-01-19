package de.aicard.card;

import de.aicard.enums.CardKnowledgeLevel;

import java.util.logging.Logger;

/**
 * Provides current CardStatus of a Card
 * Shows and edits CardKnowledgeLevel of any Account who has the Card in a LearnSetAbo
 *
 * @author: Martin Kühlborn
 */
public class CardStatus
{
    // CLASS VARIABLES
    private static final Logger logger = Logger.getLogger(CardStatus.class.getName());
    
    // MEMBER VARIABLES
    private CardKnowledgeLevel m_CardKnowledgeLevel;
    private Card m_Card;
    
    
    // CONSTRUCTORS
    public CardStatus(Card _newCard)
    {
        this.m_CardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
        this.m_Card = _newCard;
    }
    
    
    //Getter + Setter
    public Card getCard()
    {
        return m_Card;
    }
    
    public void setCard(Card _newCard)
    {
        this.m_Card = _newCard;
    }

    public CardKnowledgeLevel getCardKnowledgeLevel()
    {
        return m_CardKnowledgeLevel;
    }
    
    public void setCardKnowledgeLevel(CardKnowledgeLevel _newCardKnowledgeLevel)
    {
        this.m_CardKnowledgeLevel = _newCardKnowledgeLevel;
    }
    
    
    // METHODS
    
    /**
     * Increases CardKnowledgeLevel or keeps the highest level
     *
     */
    public void increaseCardKnowledgeLevel()
    {
        switch (m_CardKnowledgeLevel)
        {
            case NOINFORMATION:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.SOMEINFORMATION;
                break;
            case SOMEINFORMATION:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.KNOW;
                break;
            case KNOW:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.KNOWWELL;
                break;
            case KNOWWELL:
            case KNOWVERYWELL:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.KNOWVERYWELL;
                break;
            default:
                logger.warning("Something went wrong, Default case should never be reached!");
                break;
        }
    }
    
    /**
     * Decrases CardKnowlegeLevel or keeps the lowest level
     */
    public void decreaseCardKnowledgeLevel()
    {
        switch (m_CardKnowledgeLevel)
        {
            case NOINFORMATION:
            case SOMEINFORMATION:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
                break;
            case KNOW:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.SOMEINFORMATION;
                break;
            case KNOWWELL:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.KNOW;
                break;
            case KNOWVERYWELL:
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.KNOWWELL;
                break;
            default:
                logger.warning("Something went wrong, Default case should never be reached!");
                return;
        }
    }
    
    public void resetCardKnowledgeLevel()
    {
        this.m_CardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
    }
    
}

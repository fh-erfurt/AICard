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
    /**
     * Provides information on how well the user has already learned the card content.
     */
    private CardKnowledgeLevel cardKnowledgeLevel;
    /**
     * The card to which the Object belongs.
     */
    private Card card;
    
    
    // CONSTRUCTORS

    /**
     * Constructor of an CardStatus object.
     *
     * Assigns the lowest CardKnowledgeLevel (NOINFORMATION) to the member cardKnowledgeLevel.
     *
     * @param newCard the Card to which the status belongs.
     */
    public CardStatus(Card newCard)
    {
        this.cardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
        this.card = newCard;
    }
    
    
    //Getter + Setter
    public Card getCard()
    {
        return card;
    }
    
    public void setCard(Card newCard)
    {
        this.card = newCard;
    }

    public CardKnowledgeLevel getCardKnowledgeLevel()
    {
        return cardKnowledgeLevel;
    }
    
    public void setCardKnowledgeLevel(CardKnowledgeLevel newCardKnowledgeLevel)
    {
        this.cardKnowledgeLevel = newCardKnowledgeLevel;
    }
    
    
    // METHODS
    
    /**
     * Increases CardKnowledgeLevel or keeps the highest level
     *
     */
    public void increaseCardKnowledgeLevel()
    {
        switch (cardKnowledgeLevel)
        {
            case NOINFORMATION:
                this.cardKnowledgeLevel = CardKnowledgeLevel.SOMEINFORMATION;
                break;
            case SOMEINFORMATION:
                this.cardKnowledgeLevel = CardKnowledgeLevel.KNOW;
                break;
            case KNOW:
                this.cardKnowledgeLevel = CardKnowledgeLevel.KNOWWELL;
                break;
            case KNOWWELL:
            case KNOWVERYWELL:
                this.cardKnowledgeLevel = CardKnowledgeLevel.KNOWVERYWELL;
                break;
            default:
                logger.warning("Something went wrong, Default case should never be reached!");
                break;
        }
    }
    
    /**
     * Decrases cardKnowledgeLevel or keeps the lowest level
     */
    public void decreaseCardKnowledgeLevel()
    {
        switch (cardKnowledgeLevel)
        {
            case NOINFORMATION:
            case SOMEINFORMATION:
                this.cardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
                break;
            case KNOW:
                this.cardKnowledgeLevel = CardKnowledgeLevel.SOMEINFORMATION;
                break;
            case KNOWWELL:
                this.cardKnowledgeLevel = CardKnowledgeLevel.KNOW;
                break;
            case KNOWVERYWELL:
                this.cardKnowledgeLevel = CardKnowledgeLevel.KNOWWELL;
                break;
            default:
                logger.warning("Something went wrong, Default case should never be reached!");
                return;
        }
    }
    
    public void resetCardKnowledgeLevel()
    {
        this.cardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
    }
    
}

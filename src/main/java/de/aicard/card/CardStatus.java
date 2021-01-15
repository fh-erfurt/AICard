package de.aicard.card;

import de.aicard.enums.CardKnowledgeLevel;

public class CardStatus
{
    // MEMBER VARIABLES
    private CardKnowledgeLevel m_CardKnowledgeLevel;
    // private int m_CardXP; might be implemented later...
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
                // Should never reach this.
                //Maybe usefull for logger
                return;
        }
    }
    
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
                // Should never reach this.
                //Maybe usefull for logger
                return;
        }
    }
    
    public void resetCardKnowledgeLevel()
    {
        this.m_CardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
    }
    
}

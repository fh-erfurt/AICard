package de.aicard.card;

import de.aicard.enums.CardKnowledgeLevel;

public class CardStatus
{
    private CardKnowledgeLevel m_CardKnowledgeLevel;
    private int m_CardXP;
    private Card m_Card;
    
    // Constructor
    public CardStatus(Card _newCard)
    {
        this.m_CardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
        this.m_CardXP = 0;
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
    
    public int getCardXP()
    {
        return m_CardXP;
    }
    
    public void setCardXP(int _newCardXP)
    {
        this.m_CardXP = _newCardXP;
    }
    
    public CardKnowledgeLevel getCardKnowledgeLevel()
    {
        return m_CardKnowledgeLevel;
    }
    
    public void setCardKnowledgeLevel(CardKnowledgeLevel _newCardKnowledgeLevel)
    {
        this.m_CardKnowledgeLevel = _newCardKnowledgeLevel;
    }
    
    
    //Some Methods and Functions
    
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
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.KNOWVERYWELL;
                break;
            case KNOWVERYWELL:
                //Not sure about this one, cant be increased afer KNOWVERYWELL
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
                //Not sure about this one, cant be decrased afer NOINFORMATION
                this.m_CardKnowledgeLevel = CardKnowledgeLevel.NOINFORMATION;
                break;
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

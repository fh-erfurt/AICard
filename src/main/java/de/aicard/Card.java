package de.aicard;

public class Card {

    /* This class represents a Card, each card has a level of knowledge meaning how good the student knows the content of the card, each card can be 'flipped'
to see the front text and the back text of it
        */

   public enum CARDKNOWLEDGELEVEL{
        NOINFORMATION,
        SOMEINFORMATION,
        KNOW,
        KNOWWELL,
        KNOWVERYWELL
    }

    //Attribute

    private String cardTextFront;
    private String cardTextBack;
    private CARDKNOWLEDGELEVEL cardKnowledgeLevel ;
    private int cardXP;


    //Constructor Card
    public Card(String _cardTextFront, String _cardTextBack, CARDKNOWLEDGELEVEL _cardKnowledgeLevel, int _cardXP) {
        this.cardTextFront = _cardTextFront;
        this.cardTextBack = _cardTextBack;
        this.cardKnowledgeLevel = _cardKnowledgeLevel;
        this.cardXP = _cardXP;
    }

    public Card(){}

    //Setter + Getter

    public String getCardTextFront()
    {
        return cardTextFront;
    }
    public void setCardTextFront(String cardTextFront)
    {
        this.cardTextFront = cardTextFront;
    }

    public String getCardTextBack()
    {
        return cardTextBack;
    }
    public void setCardTextBack(String cardTextBack)
    {
        this.cardTextBack = cardTextBack;
    }

    public CARDKNOWLEDGELEVEL getCardKnowledgeLevel()
    {
        return cardKnowledgeLevel;
    }
    public void setCardKnowledgeLevel(CARDKNOWLEDGELEVEL cardKnowledgeLevel) { this.cardKnowledgeLevel = cardKnowledgeLevel; }

    public int getCardXP()
    {
        return cardXP;
    }
    public void setCardXP(int cardXP)
    {
        this.cardXP = cardXP;
    }
}

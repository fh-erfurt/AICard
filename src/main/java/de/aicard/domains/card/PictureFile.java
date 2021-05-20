package de.aicard.domains.card;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Provides an PictureFile for side of a card
 * Currently using placeholder Strings
 * Will be real Picture in Java 2
 *
 * @author Martin Kuehlborn
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PictureFile extends CardContent
{

    // MEMBER VARIABLES
    /**
     * Placeholder for an audio file. Will be a real audio in Java 2
     */
    @Column
    private String pictureData;
    /**
     * The title of the CardContent. Will be shown on top of the AudioData.
     */
    @Column
    private String title;
    
    
    // CONSTRUCTORS
    
    public PictureFile(String _newPictureData)
    {
        this.pictureData = _newPictureData;
    }
    
    // GETTER + SETTER
    public String getPictureData() throws NullPointerException
    {
        if(this.pictureData == null)
        {
            throw new NullPointerException("PictureData was not set.");
        }
        
        return this.pictureData;
    }
    
    public void setPictureData(String _newPictureData)
    {
        this.pictureData = _newPictureData;
    }
    
    public String getTitle() throws NullPointerException
    {
        if(this.title == null)
        {
            throw new NullPointerException("PictureTitle was not set.");
        }
        
        return this.title;
    }
    
    public void setTitle(String _newTitle)
    {
        this.title = _newTitle;
    }
}

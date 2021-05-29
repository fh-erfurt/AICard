package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * CardContent is the connection interface between Card and *File Classes
 *
 * @author Martin Kuehlborn
 */

@Entity
@Table
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name="type" )
public abstract class CardContent extends BaseEntity
{
    
}

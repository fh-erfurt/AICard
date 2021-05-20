package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;

import javax.persistence.*;

/**
 * CardContent is the connection interface between Card and *File Classes
 *
 * @author Martin Kuehlborn
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CardContent extends BaseEntity
{
    
}

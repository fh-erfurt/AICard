package de.aicard.domains.card;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Method;
import java.util.List;

/**
 * CardContent is the connection interface between Card and *File Classes
 *
 * @author Martin Kuehlborn
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardContent extends BaseEntity
{
    @Column
    private String title;
    @Column(length = 512)
    private String data;
    @Column
    private DataType type;

}

package de.aicard.domains.account;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Visibility;
import de.aicard.domains.learnset.CardList;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * is the stencil for the subclasses Professor and Student
 * contains attributes of an account
 * Provides the user with the functionalities to personalize their account
 *
 * @author Antonio Blechschmidt
 */
@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {
    //Logger
    private static final Logger logger = Logger.getLogger(Account.class.getName());
    //Attribute

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String description;
    private Faculty faculty;

    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<LearnSetAbo> learnsetAbos;

    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> friends;

    /**
     * constructor for tests
     *
     * @param newEmail       /
     * @param newPassword    / here it's the plain password in application we use a hashed password
     * @param newName        /
     * @param newDescription /
     * @param newFaculty     /
     */
    public Account(String newEmail, String newPassword, String newName, String newDescription, Faculty newFaculty) {
        this.email = newEmail;
        this.password = newPassword;
        this.name = newName;
        this.description = newDescription;
        this.faculty = newFaculty;
        this.learnsetAbos = new ArrayList<>();
        this.friends = new ArrayList<>();
    }


    /**
     * Create a new LearnSetAbo with a new LearnSet
     * puts the new LearnSetAbo in learnSetAbos
     *
     * @param title       |
     * @param description | provided Information for the Constructor of LearnSet
     * @param faculty     |
     * @param visibility  |
     */
    public void createNewOwnLearnSet(String title, String description, Faculty faculty, Visibility visibility) {
        try {
            LearnSet newLearnSet = new LearnSet(title, description, faculty, new CardList(), this, visibility);
            newLearnSet.setOwner(this);
            newLearnSet.addAdmin(this);
            this.addLearnSetAbo(newLearnSet);
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }


    /**
     * adds learnSetAbo into learnSetAbo list with a given learnset
     *
     * @param learnSet /
     */
    public void addLearnSetAbo(LearnSet learnSet) {
        if (learnSet.isAuthorizedToAccessLearnSet(this)) {
            try {
                this.learnsetAbos.add(new LearnSetAbo(learnSet));
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }
    }

    /**
     * removes learnsetabo from learnsetabo list
     *
     * @param _learnSetAbo /
     */
    public void removeLearnSetAbo(LearnSetAbo _learnSetAbo) {
        this.learnsetAbos.remove(_learnSetAbo);
    }

    /**
     * method removes learnsetabos from learnsetabo list by learnset and returns it
     *
     * @param learnSet /
     * @return LearnSetAbo
     */
    public LearnSetAbo removeLearnSetAboByLearnSet(LearnSet learnSet) {
        for (int i = this.learnsetAbos.size() - 1; i >= 0; i--) {
            if (this.learnsetAbos.get(i).getLearnSet().equals(learnSet)) {
                LearnSetAbo abo = this.learnsetAbos.get(i);
                this.removeLearnSetAbo(abo);
                return abo;
            }
        }
        return null;
    }

    //Friends

    /**
     * adds account as friend into friend list
     *
     * @param friend /
     */
    public void addFriend(Account friend) {
        this.friends.add(friend);
    }

    /**
     * removes friend from friend list
     *
     * @param friend /
     */
    public void removeFriend(Account friend) {
        this.friends.remove(friend);
    }

}
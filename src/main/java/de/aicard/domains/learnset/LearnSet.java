package de.aicard.domains.learnset;

import de.aicard.domains.BaseEntity;
import de.aicard.domains.Social.Comment;
import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.enums.Recommended;
import de.aicard.domains.enums.Visibility;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Provides CardList with additional information:
 * Title, Description, Faculty(to which Accounts from which Faculties it will be shown)
 * CommentList, Evaluations(only 1 per Account->LearnSetAbo is possible)
 * Owner(Account who created the LearnSet), Admins(Accounts who can edit the LearnSet)
 * Visibility(to show the LearnSet to all Account, to Faculty and Friends or only to Owner)
 *
 * @author Martin Kuehlborn
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
public class LearnSet extends BaseEntity {
    // CLASS VARIABLES
    private static final Logger logger = Logger.getLogger(LearnSet.class.getName());

    // MEMBER VARIABLES
    private String title;

    /**
     * Short Text Description about the LearnSet content
     */
    private String description;

    /**
     * Which Faculty is the LearnSet acquainted with
     */
    private Faculty faculty;

    @OneToOne(cascade = CascadeType.ALL)
    private CardList cardList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;

    /**
     * The Account who created and owns the LearnSet has all rights
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Account owner;

    /**
     * Visibility for Accounts who are not the owner
     */
    private Visibility visibility;

    /**
     * List of people who can add and remove cards
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> adminList;


    // CONSTRUCTORS

    /**
     * LearnSet constructor
     *
     * @param newCardList /
     */
    public LearnSet(CardList newCardList) {
        this(null, null, null, newCardList, null, Visibility.PRIVATE);
    }

    /**
     * LearnSet constructor
     *
     * @param newTitle    /
     * @param newFaculty  /
     * @param newCardList /
     * @param newOwner    /
     */
    public LearnSet(String newTitle, Faculty newFaculty, CardList newCardList, Account newOwner) {
        this(newTitle, null, newFaculty, newCardList, newOwner, Visibility.PRIVATE);
    }

    /**
     * LearnSet constructor
     *
     * @param newTitle       /
     * @param newDescription /
     * @param newFaculty     /
     */
    public LearnSet(String newTitle, String newDescription, Faculty newFaculty) {
        this(newTitle, newDescription, newFaculty, new CardList(), null, Visibility.PRIVATE);
    }

    /**
     * LearnSet constructor
     *
     * @param newTitle       /
     * @param newDescription /
     * @param newFaculty     /
     * @param newCardList    /
     * @param newOwner       /
     * @param visibility     /
     */
    public LearnSet(String newTitle, String newDescription, Faculty newFaculty, CardList newCardList, Account newOwner, Visibility visibility) {
        this.title = newTitle;
        this.description = newDescription;
        this.faculty = newFaculty;
        this.cardList = newCardList;
        this.commentList = new ArrayList<>();
        this.owner = newOwner;
        this.visibility = visibility;
        this.adminList = new ArrayList<>();

    }

    /*
     * Admin
     *
     * */

    /**
     * adds given account to adminList if he is not already in the list
     *
     * @param _newAdmin /
     */
    public void addAdmin(Account _newAdmin) {
        if (!adminList.contains(_newAdmin)) {
            this.adminList.add(_newAdmin);
        } else {
            logger.warning("New Admin is already part of adminList");
        }
    }

    /**
     * Removes Admin from adminList by Index
     * is overloaded
     * used in tests
     *
     * @param indexToRemove /
     */
    public void removeAdmin(int indexToRemove) {
        if (indexToRemove <= adminList.size() && 0 <= indexToRemove) {
            this.adminList.remove(indexToRemove);
        } else {
            logger.warning("indexToRemvoe is out of bounce");
        }
    }

    /**
     * Removes Admin from adminList by Account
     * is overloaded
     *
     * @param accountToRemove /
     */
    public void removeAdmin(Account accountToRemove) {
        if (adminList.contains(accountToRemove)) {
            this.adminList.remove(accountToRemove);
        } else {
            logger.warning("accountToRemove is not part of adminList");
        }
    }

    /**
     * Comments are used as comments
     */
    public void addComment(Comment newComment) {
        this.commentList.add(newComment);
    }

    /**
     * calculates percentage of positive evaluations
     *
     * @return average positive evaluation or
     */
    public int calculateEvaluation() {
        if (this.commentList.size() == 0) {
            return -1;
        }
        int numberOfYes = 0;
        for (Comment comment : this.commentList) {
            if (comment.getRecommended().equals(Recommended.YES)) {
                numberOfYes++;
            }
        }

        return (int) Math.ceil(100.0 / this.commentList.size() * numberOfYes);
    }

    /**
     * checks if given account can see the learnSet
     *
     * @param account /
     * @return if the user can view the learnSet
     */
    public boolean isAuthorizedToAccessLearnSet(Account account) {
        switch (this.visibility) {
            case PUBLIC:
                return true;

            case PRIVATE:
                if (this.getOwner() == account || this.getAdminList().contains(account)) {
                    return true;
                }
                break;

            case PROTECTED:
                if (this.getOwner().getFriends().contains(account) || this.getOwner() == account
                        || this.getFaculty().equals(account.getFaculty()) || this.getAdminList().contains(account)) {
                    return true;
                }
                break;

        }
        return false;
    }

    /**
     * check if given account is in adminList
     *
     * @param account /
     * @return if the user is in the adminList
     */
    public boolean isAdmin(Account account) {
        return this.getAdminList().contains(account);
    }

    /**
     * check if account is the learnSetOwner
     *
     * @param account /
     * @return if the user is the owner
     */
    public boolean isOwner(Account account) {
        return this.getOwner().equals(account);
    }

}

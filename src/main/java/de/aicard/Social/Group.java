package de.aicard.Social;

import java.util.ArrayList;
import java.util.Arrays;

import de.aicard.account.Account;
import de.aicard.account.AccountList;
import de.aicard.enums.Visibility;
import de.aicard.learnset.LearnSet;
/**
the class group was supposed to be where students share their Learnsets to discuss them, functionalities will be
 added later to make that possible
 * */
public class Group {
    //might be implemented later... maybe... next semester...


    private String name;
    private AccountList members;
    private Visibility publicity;
    private ArrayList<LearnSet> groupLearnSets;

    //Constructor


    public Group(String newName , Visibility newPublicity, Account newCreator){
        this.name = newName;
        this.members = new AccountList();
        this.members.addPerson(newCreator);
        this.publicity = newPublicity ;

    }

    //Setter & getter

    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        this.name = newName;
    }

    public AccountList getMembers() {
        return this.members;
    }
    public void setMembers(AccountList newMembers) {
        this.members = newMembers;
    }

    public Visibility getPublicity() {
        return this.publicity;
    }
    public void setPublicity(Visibility newVisibility) {
        this.publicity = newVisibility;
    }

//functions
    public Account getGroupCreator(){
        return this.members.getPerson(0);
    }

    public void addToGroup(Account newMember){

    this.members.addPerson(newMember);
}


     public void removeFromGroup(Account oldMember){
        this.members.removePerson(oldMember);
}

}

package de.aicard.Social;

import java.util.ArrayList;
import java.util.Arrays;

import de.aicard.account.Account;
import de.aicard.account.AccountList;
import de.aicard.enums.Visibility;

public class Group {

    private String name;
    private AccountList members;
    private Visibility publicity;

    //Constructor


    public Group(String _name , Visibility _publicity, AccountList _acclist){
        this.name = _name;
        this.members = new AccountList();
        this.publicity = _publicity;

    }

    //Setter & getter

    public String get_name() {
        return name;
    }
    public void set_name(String _name) {
        this.name = _name;
    }

    public AccountList get_members() {
        return members;
    }
    public void set_members(AccountList _acclist) {
        this.members = _acclist;
    }

    public Visibility get_publicity() {
        return publicity;
    }
    public void set_publicity(Visibility _vis) {
        this.publicity = _vis;
    }

//functions

public void inviteToGroup(Account _acc){

    this.members.addPerson(_acc);
}

}

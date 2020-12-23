package de.aicard.account;

import java.util.ArrayList;
import java.util.Arrays;
import de.aicard.enums.Visibility;

public class Groups {

    private String name;
    private AccountList members;
    private Visibility publicity;

    //Constructor

    public Groups(){
        name = null;
        members = new AccountList();
        publicity = Visibility.PRIVATE;

    }
    public Groups(String _name , Visibility _publicity, AccountList _acclist){
        name = _name;
        members = new AccountList();
        members = _acclist;
        publicity = _publicity;

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

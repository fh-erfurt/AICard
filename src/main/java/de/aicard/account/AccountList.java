package de.aicard.account;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountList {

    private ArrayList<Account> accountlist;

    //Constructor

    public AccountList(){ accountlist = new ArrayList<Account>(); }
    public AccountList(ArrayList<Account> _accList)
    {
        this.accountlist = new ArrayList<Account>();
        this.accountlist = _accList;
    }
    public AccountList(Account[] _accounts){
        this.accountlist = new ArrayList<Account>();
        this.accountlist.addAll(Arrays.asList(_accounts));
    }

    //Setter & getter

    public ArrayList<Account> get_accountlist() {
        return accountlist;
    }

    public void set_accountlist(ArrayList<Account> _accList) {
        this.accountlist = _accList;
    }


    //functions

    public void addPerson(Account _acc)
    {
        accountlist.add(_acc);
    }

    public void removePerson(Account _acc)
    {
        accountlist.remove(_acc);
    }
    public void removePerson(int _acc)
    {
        accountlist.remove(_acc);
    }

    public int getNumberPersons(){
        return accountlist.size();
    }

}

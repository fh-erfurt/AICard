package de.aicard.account;

import java.util.ArrayList;
import java.util.Arrays;
/** this class is used to group accounts in a list */
public class AccountList {

    private ArrayList<Account> accountList;

    //Constructor

    public AccountList(){ accountList = new ArrayList<Account>(); }
    public AccountList(ArrayList<Account> newAccountList)
    {
        this.accountList = new ArrayList<Account>();
        this.accountList = newAccountList;
    }


    //Setter & getter

    public ArrayList<Account> getAccountlist() {
        return accountList;
    }

    public void setAccountlist(ArrayList<Account> newAccountList) {
        this.accountList = newAccountList;
    }


    //functions

    public void addPerson(Account newAccount)
    {
        accountList.add(newAccount);
    }

    public void removePerson(Account oldAccount)
    {
        accountList.remove(oldAccount);
    }
    public void removePerson(int oldAccount)
    {
        accountList.remove(oldAccount);
    }

    public int getNumberPersons(){
        return accountList.size();
    }

    public Account getPerson(int number){
        return this.accountList.get(number);
    }

    public boolean contain (Account anAccount){

        return (this.accountList.contains(anAccount));
    }

}

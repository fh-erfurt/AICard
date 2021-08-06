package de.aicard.services;

import de.aicard.config.RegPattern;
import de.aicard.domains.account.Account;
import de.aicard.storages.AccountRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void createAccount(Account account){
        Optional<Account> matchingEntries = accountRepository.findByEmail(account.getEmail());
        if(matchingEntries.isEmpty()
                && RegPattern.passMatches(account.getPassword())
                && RegPattern.emailMatches(account.getEmail())){
            //encode password
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        }
        else{
            if(!RegPattern.passMatches(account.getPassword())){
                throw new IllegalStateException("Passwort entspricht nicht den Passwortrichtlinien");
            }
            if(!matchingEntries.isEmpty()){
                throw new IllegalStateException("Ein Account mit diser E-Mail Adresse existiert bereits");
            }
            if(!RegPattern.emailMatches(account.getEmail())){
                throw new IllegalStateException("die e-mail adresse ist ungültig");
            }
        }
    }

    public void updateAccount(Account account){
        Optional<Account> oldAccount = accountRepository.findById(account.getId());
        String accountEmail = account.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(accountEmail);
        if (matchingEntries.isPresent() && matchingEntries.get().getId() != oldAccount.get().getId()) {
            throw new IllegalStateException(("Ein Account mit dieser E-Mail Adresse existiert bereits"));
        }
        else if (!RegPattern.emailMatches(accountEmail)){
            throw new IllegalStateException("Bitte gib eine gültige E-Mail Adresse an.");
        }
        oldAccount.get().setEmail(accountEmail);
        if(RegPattern.passMatches(account.getPassword())){
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        else{
            throw new IllegalStateException("Das Passwort stimmt nicht mit den angegebenen Passwortrichtlinien überein");
        }


        oldAccount.get().setName(account.getName());
        oldAccount.get().setDescription(account.getDescription());
        oldAccount.get().setFaculty(account.getFaculty());

        accountRepository.save(oldAccount.get());
    }

    public Boolean accountExists(Principal principal){

        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        return account.isPresent();
    }

    public Boolean accountExists(Long ID){
        Optional<Account> account = accountRepository.findById(ID);
        return account.isPresent();
    }

    public Long getID(Principal principal){
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        return account.get().getId();
    }

    public Optional<Account> getAccount(Long userID){
        Optional<Account>  account = accountRepository.findById(userID);
        if(account.isPresent()){
            return account;
        }
        else return null;

    }

    public Optional<Account> getAccount(Principal principal){
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        if(account.isPresent()){
            return account;
        }
        else return null;

    }

    public Boolean isLoggedIn(Principal principal, Long ID){
        return getID(principal).equals(ID);
    }
}

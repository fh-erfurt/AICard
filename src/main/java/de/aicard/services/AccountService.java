package de.aicard.services;

import de.aicard.config.RegPattern;
import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetAboRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    private final LearnSetAboService learnSetAboService;


    @Autowired
    public AccountService(LearnSetAboService learnSetAboService){
        this.learnSetAboService=learnSetAboService;
    }



    public Optional<Account> createAccount(Account account) throws IllegalStateException{
        Optional<Account> matchingEntries = accountRepository.findByEmail(account.getEmail());
        Optional<Account> acc = Optional.empty();
        if(matchingEntries.isEmpty()
                && RegPattern.passMatches(account.getPassword())
                && RegPattern.emailMatches(account.getEmail())){
            //encode password
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            acc = Optional.of(account);
        }
        else{
            if(!RegPattern.passMatches(account.getPassword())){
                throw new IllegalStateException("Passwort entspricht nicht den Passwortrichtlinien");
            }
            if(matchingEntries.isPresent()){
                throw new IllegalStateException("Ein Account mit diser E-Mail Adresse existiert bereits");
            }
            if(!RegPattern.emailMatches(account.getEmail())){
                throw new IllegalStateException("die e-mail adresse ist ungültig");
            }
        }
        return(acc);
    }

    public void updateAccount(Account account) throws IllegalStateException{
        Optional<Account> oldAccount = accountRepository.findById(account.getId());
        String accountEmail = account.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(accountEmail);
        if (matchingEntries.isPresent() && matchingEntries.get().getId() != oldAccount.get().getId()) {
            throw new IllegalStateException(("Ein Account mit dieser E-Mail Adresse existiert bereits"));
        }
        else if (!RegPattern.emailMatches(accountEmail)){
            throw new IllegalStateException("Bitte gib eine gültige E-Mail Adresse an.");
        }
        oldAccount.ifPresent(value -> value.setEmail(accountEmail));
        if(RegPattern.passMatches(account.getPassword())){
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        else{
            throw new IllegalStateException("Das Passwort stimmt nicht mit den angegebenen Passwortrichtlinien überein");
        }

        if(oldAccount.isPresent()) {
            oldAccount.get().setName(account.getName());
            oldAccount.get().setDescription(account.getDescription());
            oldAccount.get().setFaculty(account.getFaculty());
            accountRepository.save(oldAccount.get());
        }
    }

    public Boolean accountExists(Principal principal){

        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        return account.isPresent();
    }

    public Boolean accountExists(Long ID){
        return accountRepository.existsById(ID);
    }

    public Long getID(Principal principal){
        Optional<Account> account = accountRepository.findByEmail(principal.getName());
        return account.get().getId();
    }

    public Optional<Account> getAccount(Long userID){
        return  accountRepository.findById(userID);

    }

    public Optional<Account> getAccount(Principal principal){
        return accountRepository.findByEmail(principal.getName());

    }

    public Boolean isLoggedIn(Principal principal, Long ID){
        return getID(principal).equals(ID);
    }

    public Boolean isLoggedIn(Principal principal){
        return (accountRepository.findByEmail(principal.getName())).isPresent();
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

//    public void removeLearnSet(Account account, LearnSet learnSet){
//        List<LearnSetAbo> abos = account.getLearnsetAbos();
//        for (int i= abos.size()-1;i>=0;i--) {
//            if(abos.get(i).getLearnSet().equals(learnSet)){
//                LearnSetAbo abo = abos.get(i);
//                abo.removeLearnSet();
//                account.removeLearnSetAbo(abo);
//            }
//        }
//        account.getOwnLearnSets().remove(learnSet);
//        this.saveAccount(account);
//    }


    public List<LearnSetAbo> getLearnSetAbos(Principal principal){
        List<LearnSetAbo> erg = new ArrayList<>();
        Optional<Account> account = this.getAccount(principal);
        if(account.isPresent()){
            erg = account.get().getLearnsetAbos();
        }
        return erg;
    }

}

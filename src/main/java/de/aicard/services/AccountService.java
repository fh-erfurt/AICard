package de.aicard.services;

import de.aicard.config.RegPattern;
import de.aicard.domains.account.Account;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.AccountRepository;
import de.aicard.storages.LearnSetAboRepository;
import de.aicard.storages.LearnSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    LearnSetRepository learnSetRepository;

    @Autowired
    LearnSetAboRepository learnSetAboRepository;
    
    
    


    @Autowired
    public AccountService(){

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

    public void updateAccount(Account account,Optional<Account> friend) throws IllegalStateException{
        Optional<Account> oldAccount = accountRepository.findById(account.getId());
        String accountEmail = "";
        if(account.getEmail().isEmpty()){
            accountEmail = oldAccount.get().getEmail();
        }else{
            accountEmail = account.getEmail();
        }
        Optional<Account> matchingEntries = accountRepository.findByEmail(accountEmail);
        
        System.out.println("matching Entries: " + matchingEntries.isPresent());
        
        if (matchingEntries.isPresent() && !matchingEntries.get().getId().equals(oldAccount.get().getId()) ){
            throw new IllegalStateException("Ein Account mit dieser E-Mail Adresse existiert bereits");
        }
        System.out.println("accountEmail: " + accountEmail);
        if (!RegPattern.emailMatches(accountEmail) ){
            throw new IllegalStateException("Bitte gib eine gültige E-Mail Adresse an.");
        }
        if(!RegPattern.passMatches(account.getPassword()) && !account.getPassword().isEmpty()){
            throw new IllegalStateException("Das Passwort stimmt nicht mit den angegebenen Passwortrichtlinien überein");
        }
        if(friend.isPresent() && friend.get().getId().equals(oldAccount.get().getId())){
            throw new IllegalStateException("Der angegebene Account kann nicht hinzugefügt werden");
        }
        
        if(!account.getPassword().isEmpty()) account.setPassword(passwordEncoder.encode(account.getPassword()));
        
        
        if(oldAccount.isPresent()){
            oldAccount.get().setEmail(accountEmail);
        }
        // TODO : im frontend mit JS überpüfen, ob die beiden Passwörter die gleichen sind und nur 1 an den Server schicken
        if(oldAccount.isPresent()) {
            oldAccount.get().setName(account.getName());
            System.out.println(account.getDescription());
            oldAccount.get().setDescription(account.getDescription());
            oldAccount.get().setFaculty(account.getFaculty());
            if(friend.isPresent() && !oldAccount.get().getFriends().contains(friend.get())){
                oldAccount.get().addFriend(friend.get());
            }
            
            //TODO Freundesliste mit überschreiben
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
    
    public Optional<Account> getAccount(String email){
        return accountRepository.findByEmail(email);
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

    public void deleteLearnSetAbosByLearnSetId(Long id){
        List<Account> accounts = accountRepository.findAllAccountsByLearnsetIdInLearnSetAbo(id);
        LearnSet learnSet = learnSetRepository.findById(id).get();
        for (Account account : accounts)
        {
            LearnSetAbo learnSetAbo =  account.removeLearnSetAboByLearnSet(learnSet);
            if(learnSetAbo != null)
            {
                this.saveAccount(account);
                learnSetAboRepository.delete(learnSetAbo);
            }
        }
    }


    public List<LearnSetAbo> getLearnSetAbos(Principal principal){
        List<LearnSetAbo> erg = new ArrayList<>();
        Optional<Account> account = this.getAccount(principal);
        if(account.isPresent()){
            erg = account.get().getLearnsetAbos();
        }
        return erg;
    }

}

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
import java.util.List;
import java.util.Optional;

/**
 * account service used to create, find, save and update account
 * can deletes all learnsets with a given learnset reference
 *
 * @author Clemens Berger, Daniel Michel, Martin Kühlborn
 */
@Service
public class AccountService {

    final AccountRepository accountRepository;

    final PasswordEncoder passwordEncoder;

    final LearnSetRepository learnSetRepository;

    final LearnSetAboRepository learnSetAboRepository;
    

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, LearnSetRepository learnSetRepository, LearnSetAboRepository learnSetAboRepository){

        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.learnSetRepository = learnSetRepository;
        this.learnSetAboRepository = learnSetAboRepository;
    }

    /**
     * creates account after checking if input is satisfactory
     * @param account account
     * @return created Account
     */
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
                throw new IllegalStateException("Ein Account mit dieser E-Mail Adresse existiert bereits");
            }
            if(!RegPattern.emailMatches(account.getEmail())){
                throw new IllegalStateException("die e-mail Adresse ist ungültig");
            }
        }
        return(acc);
    }

    public void updateAccount(Account account,Optional<Account> friend) throws IllegalStateException{
        Optional<Account> oldAccount = accountRepository.findById(account.getId());
        
        if(!RegPattern.passMatches(account.getPassword()) && !account.getPassword().isEmpty()){
            throw new IllegalStateException("Das Passwort stimmt nicht mit den angegebenen Passwortrichtlinien überein");
        }
        if(oldAccount.isPresent() && friend.isPresent() && friend.get().getId().equals(oldAccount.get().getId())){
            throw new IllegalStateException("Der angegebene Account kann nicht hinzugefügt werden");
        }
        
        if(!account.getPassword().isEmpty()) account.setPassword(passwordEncoder.encode(account.getPassword()));
        
        if(oldAccount.isPresent()) {
            oldAccount.get().setName(account.getName());
            oldAccount.get().setDescription(account.getDescription());
            oldAccount.get().setFaculty(account.getFaculty());
            if(friend.isPresent() && !oldAccount.get().getFriends().contains(friend.get())){
                oldAccount.get().addFriend(friend.get());
            }
            accountRepository.save(oldAccount.get());
        }
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

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    /**
     * deletes all learnsetabos that have a reference to a given learnset
     * @param learnSetId learnSetId
     */
    public void deleteLearnSetAbosByLearnSetId(Long learnSetId){
        List<Account> accounts = accountRepository.findAllAccountsByLearnsetIdInLearnSetAbo(learnSetId);
        Optional<LearnSet> learnSet = learnSetRepository.findById(learnSetId);
        if(learnSet.isPresent())
        {
            for (Account account : accounts)
            {
                LearnSetAbo learnSetAbo = account.removeLearnSetAboByLearnSet(learnSet.get());
                if (learnSetAbo != null)
                {
                    this.saveAccount(account);
                    learnSetAboRepository.delete(learnSetAbo);
                }
            }
        }
    }
    
}

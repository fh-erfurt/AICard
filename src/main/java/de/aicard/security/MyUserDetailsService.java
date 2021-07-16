package de.aicard.security;

import de.aicard.domains.account.Account;
import de.aicard.storages.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    
    @Autowired
    AccountRepository accountRepository;
    
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException
    {
        Optional<Account> account = accountRepository.findByEmail(userEmail);
        
        account.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userEmail));
        
        return account.map(MyUserDetails::new).get();
        
    }
}

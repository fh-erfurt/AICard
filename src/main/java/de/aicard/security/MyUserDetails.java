package de.aicard.security;

import de.aicard.domains.account.Account;
import de.aicard.domains.enums.Faculty;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
/**
 * @Author Martin Kühlborn
 */
@Getter
@Setter
public class MyUserDetails implements UserDetails
{
    private Long Id;
    private String email;
    private String password;
    private String name;
    private String description;
    private Faculty faculty;
    private List<LearnSet> ownLearnSets;
    private List<LearnSetAbo> favoriteLearnSets;
    private List<Account> friends;
    
    private List<GrantedAuthority> authorities;
    
    
    
    public MyUserDetails(Account account)
    {
        this.Id = account.getId();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.name = account.getName();
        this.description = account.getDescription();
        this.faculty = account.getFaculty();
        this.favoriteLearnSets = account.getLearnsetAbos();
        this.friends = account.getFriends();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }
    
    @Override
    public String getPassword()
    {
        return this.password;
    }
    
    @Override
    public String getUsername()
    {
        return this.email;
    }
    
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isEnabled()
    {
        return true;
    }
}

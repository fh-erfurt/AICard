package de.aicard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SpringSecurity standard implementation of WebSecurityConfigurerAdapter
 *
 * @author Martin Kuehlborn
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    
    @Autowired
    UserDetailsService userDetailsService;
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);
    }
    
    /**
     * configuration for access rights for pages
     *
     * @param http http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        
        http.authorizeRequests()
                .antMatchers("/addCard/*", "/cardOverview/**", "/createLearnset",
                             "/editLearnSet/**", "/learnSets", "/profile", "/profile/**", "/comments/*",
                             "/updateProfile/**", "/learnSetShop", "/learnCard/**", "/initializeLearningSession/**"
                ).hasAnyRole("USER")
                
                .antMatchers("/", "/index", "/login", "/registration", "/error").permitAll()
                
                
                .and()
                .formLogin()
                .loginPage("/login") // use GetMapping in LoginController
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/learnSets") // if Login was successful, go to learnSets
                .permitAll()
                
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index")
        ;
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
}

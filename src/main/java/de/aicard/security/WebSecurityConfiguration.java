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
 * @Author Martin Kühlborn
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

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // TODO : add Error Controller + 404 + 503
        http.authorizeRequests()
                .antMatchers("/addCard/*", "/cardOverview/**", "/createLearnset", "/deckOverview/**",
                             "/editCard/**", "/editLearnSet/**", "/learnSets", "/profile", "/profile/**",
                             "/updateProfile/**", "/learnSetShop", "/learnCard/**", "/initializeLearningSession/**",
                             "/comments/*"
                             // TODO : hier müssen alle seiten bzw getMethoden eingetragen werden
                             ).hasAnyRole("USER")
                
                .antMatchers("/","/index", "/login", "/registration").permitAll()
                
                
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
        // TODO : Use an Encoder
        return new BCryptPasswordEncoder();// NoOpPasswordEncoder.getInstance();
    }
   
}

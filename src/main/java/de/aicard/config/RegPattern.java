package de.aicard.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.servlet.http.Cookie;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Setter
@Getter
@RequiredArgsConstructor
public class RegPattern
{
    
    private static final String patternPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String patternEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean passMatches(String password){
        Pattern pattern = Pattern.compile(patternPass);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean emailMatches(String email){
        Pattern pattern = Pattern.compile(patternEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

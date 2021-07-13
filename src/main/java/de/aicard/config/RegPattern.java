package de.aicard.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.servlet.http.Cookie;


@Setter
@Getter
@RequiredArgsConstructor
public class RegPattern
{
    
    private static final String patternReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    
    public static String getPatternReg()
    {
        return patternReg;
    }
}

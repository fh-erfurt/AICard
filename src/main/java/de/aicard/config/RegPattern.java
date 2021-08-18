package de.aicard.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Hilfsklasse zuzr Email und Passswortüberprüfung
 */

@Setter
@Getter
@RequiredArgsConstructor
public class RegPattern {

    private static final String patternPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String patternEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    /**
     * checks if the given string matches the password regex
     *
     * @param password the password string
     * @return matching
     */
    public static boolean passMatches(String password) {
        Pattern pattern = Pattern.compile(patternPass);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * checks if the given string matches the email regex
     *
     * @param email the email string
     * @return matching
     */
    public static boolean emailMatches(String email) {
        Pattern pattern = Pattern.compile(patternEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

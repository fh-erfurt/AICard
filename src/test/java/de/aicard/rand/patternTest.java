package de.aicard.rand;

import de.aicard.config.RegPattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class patternTest {

    @Test
    public void passTest(){
        String passwordTrue = "aaBB123##";
        Assertions.assertTrue(RegPattern.passMatches(passwordTrue));
        String passwordFalse = "a1#";
        Assertions.assertFalse(RegPattern.passMatches(passwordFalse));
    }

    @Test
    public void emailTest(){
        String emailTrue = "wa1d@kalt.de";
        Assertions.assertTrue(RegPattern.emailMatches(emailTrue));
        String emailFalse = "waid@K#alt.de";
        Assertions.assertFalse(RegPattern.emailMatches(emailFalse));

        emailFalse = "waidK#alt.de";
        Assertions.assertFalse(RegPattern.emailMatches(emailFalse));

        String emailBW = "BwDLZDoberlugKirchhainStandortserviceStrausberg@bundeswehr.org";
        Assertions.assertTrue(RegPattern.emailMatches(emailBW));

    }

}

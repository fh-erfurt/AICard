package de.aicard.controller;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import de.aicard.domains.account.Account;
import de.aicard.domains.account.Professor;
import de.aicard.domains.account.Student;
import de.aicard.error.ErrorModel;
import de.aicard.storages.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    private static final String patternReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/signUp")
    public String signUp(Model model) {

        model.addAttribute("newProfessor", new Professor());

        return "signUp";
    }

    @PostMapping("createNewProfessor")
    public String createNewProfessor(@ModelAttribute("newProfessor") Professor newProfessor, Model model) throws NoSuchAlgorithmException
    {
        ErrorModel errorModel = new ErrorModel();
        List<String> errors = new ArrayList<>();

        Pattern pattern = Pattern.compile(patternReg);
        String password = newProfessor.getPassword();
        Matcher matcher = pattern.matcher(password);
        String profMail = newProfessor.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(profMail);

        //creating md5 hash
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hashedPassword = DatatypeConverter.printHexBinary(digest);
        newProfessor.setPassword(hashedPassword);
        System.out.println(newProfessor.getPassword());
        System.out.println(matchingEntries.isEmpty());
        System.out.println(matcher.matches());
        //end hashing

        if (matchingEntries.isEmpty() && matcher.matches()) {
            accountRepository.save(newProfessor);
            return "redirect:index";
        } else {
            if (!matcher.matches()) {
                errorModel.getErrorMessages().add("Passwort entspricht nicht den Passwortrichtlinien");
                System.out.println("password not matched");
            }
            if (!matchingEntries.isEmpty()) {
                //TODO: DAS GEHT SO ABER NICHT! DOCH!
                errorModel.getErrorMessages().add("Ein Account mit diser E-Mail Adresse existiert bereits");
                System.out.println("entry exists");
            }
            errors.add("Anmeldung Fehlgeschlagen");


            return "signUp";
        }
    }


    @PostMapping("createNewStudent")
    public String createNewStudent(@ModelAttribute("newStudent") Student newStudent, Model model) {
        Pattern pattern = Pattern.compile(patternReg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(newStudent.getPassword());
        String studentPassword = newStudent.getPassword();
        String studentMail = newStudent.getEmail();
        Optional<Account> matchingEntries = accountRepository.findByEmail(studentMail);
        if (matchingEntries.isEmpty() && matcher.matches()) {
            accountRepository.save(newStudent);
            return "redirect:index";
        } else {
            if (!matcher.find()) {
                model.addAttribute("invalidPassword", "Passwort entspricht nicht den Passwortrichtlinien");
            }
            if (!matchingEntries.isEmpty()) {
                //TODO: DAS GEHT SO ABER NICHT! DOCH!
                model.addAttribute("accountAllreadyExists", "Ein Account mit diser E-Mail Adresse existiert bereits");
            }
            model.addAttribute("signupError", "Anmeldung Fehlgeschlagen");
            return "redirect:signUp";
        }
    }

    @GetMapping("/login")
    public String login(Model model)
    {
//ihr könnt natürlich weitermachen // TODO: nein! // <-- dreist
        model.addAttribute("account", new Professor());

        return "login";
    }

    @PostMapping("/accountLogin")
    public String accountLogin(@ModelAttribute("account") Professor account, Model model) throws NoSuchAlgorithmException {
        Optional<Account> accountFromDB = accountRepository.findByEmail(account.getEmail());
        System.out.println(account.getPassword());
        if(accountFromDB.isEmpty())
        {
            // Account with this EMail doesnt exists
            //model.addAttribute(); // write ErrorMessage for User
            System.out.println("model.addAttribute(); // write ErrorMessage for User");
            return "login";
        }

        String password = account.getPassword();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hashedPassword = DatatypeConverter.printHexBinary(digest);

        System.out.println(accountFromDB.get().getPassword());
        System.out.println(hashedPassword);

        if (accountFromDB.get().getPassword().equals(hashedPassword))
        {
            // anmeldung bestätigen
            // Session Token speichern
            // gibts das oder einfach cookie? sicherlich...
            System.out.println("gibts das oder einfach cookie? sicherlich...");
            return "redirect:index";
        }
        System.out.println("write error Message that Email or Password were incorrect and return to Login");
        // write error Message that Email or Password were incorrect and return to Login
        return "login";

    }

}

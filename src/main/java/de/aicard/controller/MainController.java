package de.aicard.controller;
//TODO dependency
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
        //@GetMapping("/")
        public String welcome() {
            return "index";
        }
}

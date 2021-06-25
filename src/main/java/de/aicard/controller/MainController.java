package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    
        @GetMapping("/")
        public String welcome() {
            return "index";
        }

        @GetMapping("/index")
        public String notWelcome(){
            return "index";
        }

}

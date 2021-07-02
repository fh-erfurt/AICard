package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    @GetMapping("/saveFile")
    public String getSaveFile(Model model)
    {
        
        return "saveFile";
    }
}

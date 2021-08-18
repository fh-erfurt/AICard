package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * controller for index
 * @author Martin Kühlborn,Clemens Berger
 */
@Controller
public class MainController {
    /**
     * returns index
     * @return String
     */
    @GetMapping("/")
        public String welcome() {
            return "redirect:/index";
        }

    /**
     * returns index
     * @return String
     */
        @GetMapping("/index")
        public String notWelcome(){
            return "index";
        }

}

package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @Author Martin Kühlborn,Clemens Berger
 */
@Controller
public class MainController {
    /**
     * i dunno man
     * @return
     */
    @GetMapping("/")
        public String welcome() {
            return "index";
        }

    /**
     * same
     * @return
     */
        @GetMapping("/index")
        public String notWelcome(){
            return "index";
        }

}

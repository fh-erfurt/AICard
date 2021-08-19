package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * controller for index
 *
 * @author Martin Kuehlborn,Clemens Berger
 */
@Controller
public class MainController
{
    /**
     * redirect to index
     *
     * @return index
     */
    @GetMapping("/")
    public String welcome()
    {
        return "redirect:/index";
    }
    
    /**
     * returns index
     *
     * @return index
     */
    @GetMapping("/index")
    public String notWelcome()
    {
        return "index";
    }
    
}

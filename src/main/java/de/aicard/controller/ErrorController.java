package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Clemens Berger, Martin Kuehlborn
 */
@Controller
public class ErrorController {
    
    /**
     * custom error handling
     *
     * @return  error
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }
    
    
    /**
     * custom 403 handling
     *
     * @return  error403
     */
    @GetMapping("/error403")
    public String error403() {
        return "error";
    }
}
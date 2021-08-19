package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * handles errors
 *
 * @author Clemens Berger, Martin Kuehlborn
 */
@Controller
public class ErrorController
{
    
    /**
     * custom error handling
     *
     * @return error html
     */
    @GetMapping("/error")
    public String error()
    {
        return "error";
    }
    
    
    /**
     * custom 403 handling
     *
     * @return error html
     */
    @GetMapping("/error403")
    public String error403()
    {
        return "error";
    }
}
package de.aicard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleTemplateController {
    @GetMapping("exampleTemplate")
    public String getExampleTemplate(Model model){
        return "exampleTemplate";
    }
}

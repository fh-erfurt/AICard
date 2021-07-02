package de.aicard.controller;

import de.aicard.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class FileUploadController
{
    @Autowired
    FileUploadService fileUploadService;
    
    
    
    @PostMapping("/saveFile")
    @ResponseBody
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) throws IOException
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        fileUploadService.uploadFile(file);
        
        return modelAndView;
    }

}

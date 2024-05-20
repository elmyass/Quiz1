package com.example.quiz1.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/Home")
    public String home(ModelMap modelMap) {
        return "index"; // renvoie le nom du fichier Thymeleaf sans extension
    }
}

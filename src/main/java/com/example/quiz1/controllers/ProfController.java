package com.example.quiz1.controllers;

import com.example.quiz1.entities.Question;
import com.example.quiz1.entities.Test;
import com.example.quiz1.services.QuestionService;
import com.example.quiz1.services.TestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class ProfController {
    @Autowired
    private TestService testService;
    private QuestionService questionService;
    @GetMapping("/tests")
    public String showAllTests(Model model) {
        List<Test> tests = testService.getAllTests();
        model.addAttribute("tests", tests );
        return "TestList"; //  le nom template Thymeleaf (TestList.html)
    }


    @GetMapping("/tests/new")
    public String showTestForm(ModelMap modelMap) {

        modelMap.addAttribute("test", new Test());
        return "CreateTest"; // Template pour afficher le formulaire de création de test
    }

    @PostMapping("/tests")
    public String saveTest(@ModelAttribute("test") Test test) {
        testService.saveTest(test);
        return "TestList";
    }

}

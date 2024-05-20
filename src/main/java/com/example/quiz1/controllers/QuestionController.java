package com.example.quiz1.controllers;

import com.example.quiz1.entities.Question;
import com.example.quiz1.entities.User;
import com.example.quiz1.services.QuestionService;
import com.example.quiz1.services.TestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    private TestService testService;
    @RequestMapping("/createQuestion")
    public String createQuestion(Model model) {
        model.addAttribute("tests", testService.getAllTests());
        return "CreateQuestion";
    }

    @PostMapping("/saveQuestion")
    public String saveQuestion(@Valid Question question, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "CreateQuestion";
        Question saveQuestion= questionService.saveQuestion(question);
        return "CreateQuestion";
    }


    @GetMapping("/questionsList")
    public String showQuestionForm(Model model) {
        // Récupérer la liste des questions avec leurs options depuis le service
        List<Question> existingQuestions = questionService.getAllQuestionsWithOptions();

        // Passer les informations au modèle Thymeleaf
        model.addAttribute("existingQuestions", existingQuestions);

        return "QuestionList";
    }


    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam("id") Long id, ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "4") int size) {
        questionService.deleteQuestionById(id);
        return "redirect:/questionsList?page=" + page + "&size=" + size;
    }

    @RequestMapping("/editQuestion")
    public String editQuestion(@RequestParam("id") Long id, ModelMap modelMap) {
        Question question = questionService.getQuestionById(id);
        modelMap.addAttribute("question", question);
        return "EditQuestion";
    }


}

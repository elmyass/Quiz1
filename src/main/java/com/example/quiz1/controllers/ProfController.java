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
        return "TestList"; // Ceci est le nom de votre template Thymeleaf (TestList.html)
    }

    @GetMapping("/tests/{id}")
    public String showTestDetails(@PathVariable Long id, ModelMap modelMap) {
        Test test = testService.getTestById(id).orElseThrow(() -> new RuntimeException("Test not found"));
        modelMap.addAttribute("test", test);
        return "test-details"; // Template pour afficher les détails d'un test
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

    @GetMapping("/tests/{id}/edit")
    public String showEditTestForm(@PathVariable Long id, ModelMap modelMap) {
        Test test = testService.getTestById(id).orElseThrow(() -> new RuntimeException("Test not found"));
        modelMap.addAttribute("test", test);
        return "CreateTest"; // Template pour afficher le formulaire de modification de test
    }

    @PostMapping("/tests/{id}/questions")
    public String saveQuestion(@PathVariable Long id, @ModelAttribute("question") Question question) {
        // Vous devez associer la question au test
        Test test = testService.getTestById(id).orElseThrow(() -> new RuntimeException("Test not found"));
        question.setTest(test);
        questionService.saveQuestion(question);
        return "redirect:/prof/tests/" + id;
    }

    @GetMapping("/tests/{testId}/questions/{questionId}/edit")
    public String showEditQuestionForm(@PathVariable Long testId, @PathVariable Long questionId, ModelMap modelMap) {
        Question question = questionService.getQuestionById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));
        modelMap.addAttribute("question", question);
        return "question-form"; // Template pour afficher le formulaire de modification de question
    }

    @PostMapping("/tests/{testId}/questions/{questionId}")
    public String updateQuestion(@PathVariable Long testId, @PathVariable Long questionId, @ModelAttribute("question") Question question) {
        question.setId(Math.toIntExact(questionId));
        questionService.saveQuestion(question);
        return "redirect:/prof/tests/" + testId;
    }

    @GetMapping("/tests/{testId}/questions/{questionId}/delete")
    public String deleteQuestion(@PathVariable Long testId, @PathVariable Long questionId) {
        questionService.deleteQuestionById(questionId);
        return "redirect:/prof/tests/" + testId;
    }
}

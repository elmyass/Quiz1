package com.example.quiz1.controllers;

import com.example.quiz1.entities.Test;
import com.example.quiz1.entities.Question;
import com.example.quiz1.services.TestService;
import com.example.quiz1.services.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/createTest")
    public String createTest() {

        return "CreateTest"; // Retourne la vue pour créer un test
    }

    @PostMapping("/saveTest")
    public String saveTest(@ModelAttribute("test") @Valid Test test, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "CreateTest"; // Retourne à la page de création de test avec les erreurs
        }

        // Récupérer les questions associées au test soumis
        List<Question> questions = test.getQuestions();
        if (questions != null) {
            // Lier chaque question au test
            for (Question question : questions) {
                question.setTest(test);
            }
        }

        // Sauvegarder le test avec les questions associées
        testService.saveTest(test);

        return "redirect:/CreateTest"; // Redirige vers la page de création de test après l'enregistrement
    }

    @GetMapping("/testsList")
    public String testsList(ModelMap modelMap,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size) {

        Page<Test> tests = testService.getAllTestsByPage(page, size);
        modelMap.addAttribute("tests", tests);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[tests.getTotalPages()]);
        return "TestList"; // Retourne la vue affichant la liste des tests
    }

    @GetMapping("/deleteTest/{id}")
    public String deleteTest(@PathVariable("id") Long id) {
        testService.deleteTestById(id);
        return "redirect:/TestList"; // Redirige vers la liste des tests après la suppression
    }

    @GetMapping("/editTest/{id}")
    public String editTest(@PathVariable("id") Long id, ModelMap modelMap) {
        Test test = testService.getTestById(id).orElse(null);
        modelMap.addAttribute("test", test);
        return "EditTest"; // Retourne la vue pour éditer le test avec l'ID spécifié
    }

    @PostMapping("/updateTest")
    public String updateTest(Test test) {
        testService.updateTest(test);
        return "redirect:/TestList"; // Redirige vers la liste des tests après la mise à jour
    }

    @GetMapping("/testDetails/{id}")
    public String testDetails(@PathVariable("id") Long id, ModelMap modelMap) {
        Test test = testService.getTestById(id).orElse(null);
        List<Question> questions = questionService.getQuestionsByTestId(id);
        modelMap.addAttribute("test", test);
        modelMap.addAttribute("questions", questions);
        return "TestDetails";
    }

}

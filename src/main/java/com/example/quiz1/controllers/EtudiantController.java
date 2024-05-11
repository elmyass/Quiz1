package com.example.quiz1.controllers;
import com.example.quiz1.entities.Question;
import com.example.quiz1.entities.Test;
import com.example.quiz1.services.QuestionService;
import com.example.quiz1.services.TestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class EtudiantController {
    @Autowired
    private TestService testService;

    // Afficher le test pour l'étudiant
    @RequestMapping("/passer-le-test/{testId}")
    public String showTestPage(@PathVariable Long testId, ModelMap modelMap) {
        Test test = testService.getTestById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
        modelMap.addAttribute("test", test);
        return "passer-le-test"; // Template pour afficher le test pour l'étudiant
    }

    // Valider les réponses soumises par l'étudiant
    @RequestMapping("/passer-le-test/{testId}/valider-reponses")
    public String validerReponses(@PathVariable Long testId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Test test = testService.getTestById(testId).orElseThrow(() -> new RuntimeException("Test not found"));

        int score = 0;
        for (Question question : test.getQuestions()) {
            String[] selectedOptions = request.getParameterValues("question_" + question.getId());
            if (selectedOptions != null) {
                List<String> selectedOptionsList = Arrays.asList(selectedOptions);
                if (selectedOptionsList.contains(question.getRightAnswer())) {
                    score++;
                }
            }
        }

        // Enregistrer le score dans la session ou dans la base de données
        // Ici, nous le stockons simplement dans les attributs de redirection pour l'afficher sur la page suivante
        redirectAttributes.addFlashAttribute("score", score);

        return "redirect:/passer-le-test/" + testId + "/resultat";
    }

    // Afficher le résultat du test (score de l'étudiant)
    @RequestMapping("/passer-le-test/{testId}/resultat")
    public String showTestResultPage(@PathVariable Long testId, @ModelAttribute("score") int score, ModelMap modelMap) {
        Test test = testService.getTestById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
        modelMap.addAttribute("test", test);
        modelMap.addAttribute("score", score);
        return "resultat-du-test"; // Template pour afficher le résultat du test
    }
}


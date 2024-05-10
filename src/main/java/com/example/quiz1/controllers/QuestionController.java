package com.example.quiz1.controllers;

import com.example.quiz1.entities.Question;
import com.example.quiz1.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@AllArgsConstructor
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/createQuestion")
    public String createQuestion(){
        return "CreateQuestion";
    }

    @RequestMapping("saveQuestion")
    public String saveQuestion(Question question) {
        questionService.saveQuestion(question);
        return "redirect:/createQuestion";
    }

    @RequestMapping("/questionsList")
    public String questionsList(ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "4") int size){

        Page<Question> questions = questionService.getAllQuestionsByPage(page, size);

        modelMap.addAttribute("questions", questions);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("pages", new int[questions.getTotalPages()]);
        return "QuestionsList";
    }

    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam("id") Long id, ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "4") int size){
        questionService.deleteQuestionById(id);
        return "redirect:/questionsList?page=" + page + "&size=" + size;
    }

    @RequestMapping("/editQuestion")
    public String editQuestion(@RequestParam("id") Long id, ModelMap modelMap){
        Question question = questionService.getQuestionById(id);
        modelMap.addAttribute("question", question);
        return "EditQuestion";
    }

    @RequestMapping("/updateQuestion")
    public String updateQuestion(Question question){
        questionService.updateQuestion(question);
        return "redirect:/questionsList";
    }
}

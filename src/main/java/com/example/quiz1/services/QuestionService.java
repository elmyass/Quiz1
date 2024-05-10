package com.example.quiz1.services;

import com.example.quiz1.entities.Question;
import com.example.quiz1.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public interface QuestionService {
        Question saveQuestion (Question question);
       Question updateQuestion(Question question);
        void deleteQuestionById(Long id);
        void deleteAllQuestions();
        Question getQuestionById(Long id);
        List<Question> getAllQuestions();
        Page<Question> getAllQuestionsByPage(int page, int size);

        List<Question> getAllQuestionsByCategory(String category);

        List<Question> getQuestionsByTestId(Long id);
    }



package com.example.quiz1.services;
import com.example.quiz1.entities.Question;
import com.example.quiz1.entities.User;
import com.example.quiz1.repositories.QuestionRepository;
import com.example.quiz1.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceempl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question saveQuestion (Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion (Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestionById(Long id) {

        questionRepository.deleteById(id);
    }

    @Override
    public void deleteAllQuestions() {

        questionRepository.deleteAll();

    }

    @Override
    public Question getQuestionById(Long id) {

        return questionRepository.findById(id).get();
    }


    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }




    public List<Question> getAllQuestionsByCategory(String category) {
            return questionRepository.findByCategory(category);
        }

    @Override
    public List<Question> getQuestionsByTestId(Long id) {
        return null;
    }

    @Override
    public Page<Question> getAllQuestionsByPage(int page, int size) {
        return null;
    }
}

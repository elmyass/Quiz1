package com.example.quiz1.repositories;

import com.example.quiz1.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

        List<Question> findByCategory(String category);
    }



package com.example.quiz1.services;

import com.example.quiz1.entities.Test;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TestService {
    Test saveTest(Test test);
    Test updateTest(Test test);
    void deleteTestById(Long id);
    Optional<Test> getTestById(Long id);
    List<Test> getAllTests();
    Page<Test> getAllTestsByPage(int page, int size);
}

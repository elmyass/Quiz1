package com.example.quiz1.services;

import com.example.quiz1.entities.Test;
import com.example.quiz1.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TestServiceempl implements TestService {

    @Autowired
    private TestRepository testRepository;
//create c est save
    @Override
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public void deleteTestById(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public Optional<Test> getTestById(Long id) {
        return testRepository.findById(id);
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    @Override
    public Page<Test> getAllTestsByPage(int page, int size) {
        return testRepository.findAll(PageRequest.of(page, size));
    }
}

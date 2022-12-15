package com.LabAPI.laboratoryAPI.services;

import com.LabAPI.laboratoryAPI.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.LabAPI.laboratoryAPI.repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TestService {
    private TestRepository testRepository;
    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test postTest(Test test){
        return testRepository.save(test);
    }

    public Optional<Test> getOneTest(Long id){
        return testRepository.findById(id);
    }

    public List<Test> getAllTest(){
        return testRepository.findAll();
    }

    public void putTest(Test test){
        testRepository.save(test);
    }

    public void deleteTest(Long id){
        testRepository.deleteById(id);
    }
}

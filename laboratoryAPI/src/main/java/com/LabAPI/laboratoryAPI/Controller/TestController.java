package com.LabAPI.laboratoryAPI.Controller;

import com.LabAPI.laboratoryAPI.entities.Test;
import com.LabAPI.laboratoryAPI.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/controller/test")
public class TestController {
    public TestService testService;
    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public ResponseEntity<List<Test>> getList(){
        List<Test> findTests = testService.getAllTest();
        if(findTests.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(testService.getAllTest());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getById(@PathVariable Long id){
        Optional<Test> findTest = testService.getOneTest(id);
        if(findTest.isPresent()){
            return ResponseEntity.ok(findTest.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Test> post(@RequestBody Test test){
        if(test.getName() != null && test.getDescription() != null){
            testService.postTest(test);
            return ResponseEntity.status(201).body(test);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> put(@RequestBody Test test){
        Optional<Test> findTest= testService.getOneTest(test.getId());
        if(findTest.isPresent()){
            testService.putTest(test);
            return ResponseEntity.status(201).body("Updated the affiliate with ID: "+test.getId());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No affiliate with ID: "+test.getId()+ " was found in the database.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Test> findTest = testService.getOneTest(id);
        if (findTest.isPresent()) {
            testService.deleteTest(id);
            return ResponseEntity.ok("The removal of the test with the ID: " + id + " has been successfully completed");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No affiliate with ID: " + id + " was found in the database.");
        }
    }
}

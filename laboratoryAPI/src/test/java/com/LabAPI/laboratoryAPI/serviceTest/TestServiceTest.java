package com.LabAPI.laboratoryAPI.serviceTest;

import com.LabAPI.laboratoryAPI.entities.Affiliate;
import com.LabAPI.laboratoryAPI.entities.Appointment;
import com.LabAPI.laboratoryAPI.services.TestService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TestServiceTest {
    @Autowired
    private TestService testService;

    // TEST OK <---------------------------------------------------------------------------------------------------------------------------------------

    @Test
    @Order(1)
    public void postTest(){
        com.LabAPI.laboratoryAPI.entities.Test testToSave= new com.LabAPI.laboratoryAPI.entities.Test("Covid", "Covid Test");
        com.LabAPI.laboratoryAPI.entities.Test testSaved=testService.postTest(testToSave);
        assertEquals(1L,testSaved.getId());
    }
    @Test
    @Order(2)
    public void findTestById(){
        Long idToFind=1L;
        Optional<com.LabAPI.laboratoryAPI.entities.Test> testSearched = testService.getOneTest(idToFind);
        assertNotNull(testSearched.get());
    }

    @Test
    @Order(3)
    public void findTests(){
        List<com.LabAPI.laboratoryAPI.entities.Test> tests = testService.getAllTest();
        assertEquals(1,tests.size());
    }

    @Test
    @Order(4)
    public void putTest(){
        com.LabAPI.laboratoryAPI.entities.Test testToUpdate= new com.LabAPI.laboratoryAPI.entities.Test(1L,"Hemocritos", "Hemocritos test");
        testService.putTest(testToUpdate);
        Optional<com.LabAPI.laboratoryAPI.entities.Test> testUpdated=testService.getOneTest(1L);
        assertEquals("Hemocritos", testUpdated.get().getName());
    }

    @Test
    @Order(5)
    public void deleteTest(){
        Long idToDelete=1L;
        testService.deleteTest(idToDelete);
        Optional<com.LabAPI.laboratoryAPI.entities.Test> testRemoved=testService.getOneTest(idToDelete);
        assertFalse(testRemoved.isPresent());
    }

    // BAD TEST <-----------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    @Order(6)
    public void postTestBADtest(){
        com.LabAPI.laboratoryAPI.entities.Test testToSave= new com.LabAPI.laboratoryAPI.entities.Test("Covid", "Covid Test");
        com.LabAPI.laboratoryAPI.entities.Test testSaved=testService.postTest(testToSave);
        assertNotEquals(1L,testSaved.getId());
    }
    @Test
    @Order(7)
    public void findTestByIdBADtest(){
        Long idToFind=2L;
        Optional<com.LabAPI.laboratoryAPI.entities.Test> testSearched = testService.getOneTest(idToFind);
        assertNotEquals(testSearched.get(), null);
    }

    @Test
    @Order(8)
    public void findTestsBADtest(){
        List<com.LabAPI.laboratoryAPI.entities.Test> tests = testService.getAllTest();
        assertNotEquals(2,tests.size());
    }

    @Test
    @Order(9)
    public void putTestBADtest(){
        com.LabAPI.laboratoryAPI.entities.Test testToUpdate= new com.LabAPI.laboratoryAPI.entities.Test(2L,"Hemocritos", "Hemocritos test");
        testService.putTest(testToUpdate);
        Optional<com.LabAPI.laboratoryAPI.entities.Test> testUpdated=testService.getOneTest(2L);
        assertNotSame("Covid", testUpdated.get().getName());
    }

    @Test
    @Order(10)
    public void deleteTestBADtest(){
        Long idToDelete=2L;
        testService.deleteTest(idToDelete);
        Optional<com.LabAPI.laboratoryAPI.entities.Test> testRemoved=testService.getOneTest(idToDelete);
        assertTrue(!testRemoved.isPresent());
    }
}


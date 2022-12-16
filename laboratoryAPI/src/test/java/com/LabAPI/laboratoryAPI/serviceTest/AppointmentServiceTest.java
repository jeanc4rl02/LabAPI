package com.LabAPI.laboratoryAPI.serviceTest;

import com.LabAPI.laboratoryAPI.entities.Affiliate;
import com.LabAPI.laboratoryAPI.entities.Appointment;
import com.LabAPI.laboratoryAPI.services.AffiliateService;
import com.LabAPI.laboratoryAPI.services.AppointmentService;
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
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AffiliateService affiliateService;
    @Autowired
    private TestService testService;

    // TEST OK -------------------------------------------------------------------------------------------------------

    @Test
    @Order(1)
    public void postAppointmentTest() {
        com.LabAPI.laboratoryAPI.entities.Test test = new com.LabAPI.laboratoryAPI.entities.Test("Covid", "Covid test");
        testService.postTest(test);
        Affiliate affiliate = new Affiliate("Jesusa", 21, "laurita_ballesteritos@gmail.com");
        affiliateService.putAffiliate(affiliate);
        Appointment appointmentToSave = new Appointment(new Date(11, 03, 2000), new Date(0, 0, 0, 14, 0, 0), new com.LabAPI.laboratoryAPI.entities.Test(1L, "Covid", "Covid test"), new Affiliate(1L, "Jesusa", 21, "laurita_ballesteritos@gmail.com"));
        Appointment appointmentSaved = appointmentService.postAppointment(appointmentToSave);
        assertEquals(1L, appointmentSaved.getId());
    }
    @Test
    @Order(2)
    public void findAppointmentByIdTest(){
        Long idToFind=1L;
        Optional<Appointment> appointmentSearched = appointmentService.getOneAppointment(idToFind);
        assertNotNull(appointmentSearched.get());
    }

    @Test
    @Order(3)
    public void findAppointmentByDateTest(){
        LocalDate dateToFind=LocalDate.of(2000, 3, 11);
        List<Appointment> appointmentSearched = appointmentService.getByDate(dateToFind);
        assertTrue(appointmentSearched.isEmpty());
    }

    @Test
    @Order(4)
    public void findAppointmentByAffiliateTest(){
        Long idToFind=1L;
        List<Appointment> appointmentSearched = appointmentService.getByAffiliate(idToFind);
        assertNotNull(!appointmentSearched.isEmpty());
    }

    @Test
    @Order(5)
    public void findAppointmentTest(){
        List<Appointment> appointments = appointmentService.getAllAppointment();
        assertEquals(1,appointments.size());
    }

    @Test
    @Order(6)
    public void putAppointmentTest(){
        Appointment appointmentToUpdate= new Appointment(1L, new Date(12, 03, 2000), new Date(0, 0, 0, 13, 0, 0), new com.LabAPI.laboratoryAPI.entities.Test(1L, "Covid", "Covid test"), new Affiliate(1L, "Jesusa", 21, "laurita_ballesteritos@gmail.com"));
        appointmentService.putAppointment(appointmentToUpdate);
        Optional<Appointment> appointmentUpdated=appointmentService.getOneAppointment(1L);
        assertEquals(new Date(12, 03, 2000), appointmentUpdated.get().getDate());
    }

    @Test
    @Order(7)
    public void deleteAppointmentTest(){
        Long idToDelete=1L;
        appointmentService.deleteAppointment(idToDelete);
        Optional<Appointment> appointmentRemoved=appointmentService.getOneAppointment(idToDelete);
        assertFalse(appointmentRemoved.isPresent());
    }

    // BAD TEST -------------------------------------------------------------------------------------------------------

    @Test
    @Order(8)
    public void postAppointmentBADTest() {
        com.LabAPI.laboratoryAPI.entities.Test test = new com.LabAPI.laboratoryAPI.entities.Test("Covid", "Covid test");
        testService.postTest(test);
        Affiliate affiliate = new Affiliate("Laura", 21, "laurita_ballesteritos2@gmail.com");
        affiliateService.putAffiliate(affiliate);
        Appointment appointmentToSave = new Appointment(new Date(11, 03, 2000), new Date(0, 0, 0, 14, 0, 0), new com.LabAPI.laboratoryAPI.entities.Test(2L, "Covid", "Covid test"), new Affiliate(2L, "Jesusa", 21, "laurita_ballesteritos@gmail.com"));
        Appointment appointmentSaved = appointmentService.postAppointment(appointmentToSave);
        assertNotEquals(1L, appointmentSaved.getId());
    }
    @Test
    @Order(9)
    public void findAppointmentByIdBADTest(){
        Long idToFind2=2L;
        Optional<Appointment> appointmentSearched = appointmentService.getOneAppointment(idToFind2);
        assertNotEquals(appointmentSearched.get(), null);
    }

    @Test
    @Order(10)
    public void findAppointmentByDateBADTest(){
        LocalDate dateToFind=LocalDate.of(2000, 3, 11);
        List<Appointment> appointmentSearched = appointmentService.getByDate(dateToFind);
        assertFalse(!appointmentSearched.isEmpty());
    }

    @Test
    @Order(11)
    public void findAppointmentByAffiliateBADTest(){
        Long idToFind=2L;
        List<Appointment> appointmentSearched = appointmentService.getByAffiliate(idToFind);
        assertFalse(appointmentSearched.isEmpty());
    }


    @Test
    @Order(12)
    public void findAppointmentBADTest(){
        List<Appointment> appointments = appointmentService.getAllAppointment();
        assertNotEquals(2,appointments.size());
    }

    @Test
    @Order(13)
    public void putAppointmentBADTest(){
        Appointment appointmentToUpdate= new Appointment(2L, new Date(12, 03, 2000), new Date(0, 0, 0, 13, 0, 0), new com.LabAPI.laboratoryAPI.entities.Test(1L, "Covid", "Covid test"), new Affiliate(1L, "Jesusa", 21, "laurita_ballesteritos@gmail.com"));
        appointmentService.putAppointment(appointmentToUpdate);
        Optional<Appointment> appointmentUpdated=appointmentService.getOneAppointment(2L);
        assertNotEquals(new Date(14, 03, 2001), appointmentUpdated.get().getDate());
    }

    @Test
    @Order(14)
    public void deleteAppointmentBADTest(){
        Long idToDelete=2L;
        appointmentService.deleteAppointment(idToDelete);
        Optional<Appointment> appointmentRemoved=appointmentService.getOneAppointment(idToDelete);
        assertTrue(!appointmentRemoved.isPresent());
    }

}



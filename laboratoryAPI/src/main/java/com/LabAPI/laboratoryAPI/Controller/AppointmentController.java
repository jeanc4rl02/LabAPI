package com.LabAPI.laboratoryAPI.Controller;

import com.LabAPI.laboratoryAPI.entities.Appointment;
import com.LabAPI.laboratoryAPI.services.AppointmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/controller/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;
    @Autowired

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getList(){
        List<Appointment> findAppointments = appointmentService.getAllAppointment();
        if(findAppointments.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(appointmentService.getAllAppointment());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id){
        Optional<Appointment> findAppointment = appointmentService.getOneAppointment(id);
        if(findAppointment.isPresent()){
            return ResponseEntity.ok(findAppointment.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/affiliate/{affiliate_id}")
        public ResponseEntity<List<Appointment>> getByAffiliates(@PathVariable Long affiliate_id){
        return ResponseEntity.ok(appointmentService.getByAffiliate(affiliate_id)) ;
    }



    @GetMapping(params = "date")
    public ResponseEntity<List<Appointment>> getByDate(@RequestParam("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate myDate = LocalDate.parse(date, formatter);
        return ResponseEntity.ok(appointmentService.getByDate(myDate));
    }



    @PostMapping
    public ResponseEntity<Appointment> post(@RequestBody Appointment appointment){
        Appointment saveAppointment = appointmentService.postAppointment(appointment);
        if(saveAppointment.getId() != null){
            appointmentService.postAppointment(appointment);
            return ResponseEntity.status(201).body(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> put(@RequestBody Appointment appointment){
        Optional<Appointment> findAppointment= appointmentService.getOneAppointment(appointment.getId());
        if(findAppointment.isPresent()){
            appointmentService.putAppointment(appointment);
            return ResponseEntity.status(201).body("Updated the affiliate with ID: "+appointment.getId());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No affiliate with ID: "+appointment.getId()+ " was found in the database.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Appointment> findAppointment = appointmentService.getOneAppointment(id);
        if(findAppointment.isPresent()){
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("The removal of the appointment with the ID: " +id+ " has been successfully completed");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No appointment with ID: "+id+ " was found in the database.");
        }

    }
}

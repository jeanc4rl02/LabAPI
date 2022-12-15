package com.LabAPI.laboratoryAPI.services;

import com.LabAPI.laboratoryAPI.entities.Appointment;
import com.LabAPI.laboratoryAPI.repositories.AppointmentRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment postAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getOneAppointment(Long id){
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> getByAffiliate(Long affiliate_id){
        return appointmentRepository.finByAffiliateId(affiliate_id);
    }

    public List<Appointment> getByDate(LocalDate date){
        return appointmentRepository.finByDate(date);
    }

    public void putAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }


}

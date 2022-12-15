package com.LabAPI.laboratoryAPI.repositories;


import com.LabAPI.laboratoryAPI.entities.Appointment;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = "SELECT * FROM appointments WHERE affiliate_id = ?1", nativeQuery = true)
    List<Appointment> finByAffiliateId(Long Id);
    @Query(value = "SELECT * FROM appointments WHERE date_add(date, interval 1 DAY) LIKE ?1% GROUP BY affiliate_id", nativeQuery = true)
    List<Appointment> finByDate(LocalDate date);
}

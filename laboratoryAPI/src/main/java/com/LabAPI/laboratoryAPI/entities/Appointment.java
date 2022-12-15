package com.LabAPI.laboratoryAPI.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "HH:mm")
    private Date hour;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test testId;

    @ManyToOne
    @JoinColumn(name = "affiliate_id", referencedColumnName = "id")
    private Affiliate affiliateId;

    public Appointment(Long id, Date date, Date hour, Test testId, Affiliate affiliateId) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.affiliateId = affiliateId;
        this.testId = testId;
    }

    public Appointment(Date date, Date hour, Test testId, Affiliate affiliateId) {
        this.date = date;
        this.hour = hour;
        this.testId = testId;
        this.affiliateId = affiliateId;
    }

    public Appointment() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public Long getTestId() {
        return testId.getId();
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    public Long getAffiliateId() {
        return affiliateId.getId();
    }

    public void setAffiliateId(Affiliate affiliateId) {
        this.affiliateId = affiliateId;
    }
}

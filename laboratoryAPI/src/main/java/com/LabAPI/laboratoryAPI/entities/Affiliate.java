package com.LabAPI.laboratoryAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "affiliates")
public class Affiliate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column(unique = true)
    @NonNull
    private String mail;
    @OneToMany(mappedBy = "affiliateId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet();
    public Affiliate(String name, Integer age, String mail) {
        this.name = name;
        this.age = age;
        this.mail = mail;
    }

    public Affiliate(Long id, String name, Integer age, String mail) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;;
    }

    public Affiliate(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}

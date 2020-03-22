package com.rekrut.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Tourist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String sex;
    private String country;
    private String notes;
    private LocalDate dOB;
    @OneToMany
    @JsonIgnoreProperties(value = "flights")
    private List<Flight> flights;

    public Tourist(String name, String surname, String sex, String country, String notes, LocalDate dOB) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.country = country;
        this.notes = notes;
        this.dOB = dOB;
    }

    public Tourist() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getdOB() {
        return dOB;
    }

    public void setdOB(LocalDate dOB) {
        this.dOB = dOB;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

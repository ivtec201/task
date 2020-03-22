package com.rekrut.task.model;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

public class TouristCreator {

    private String name;
    private String surname;
    private String sex;
    private String country;
    private String notes;
    private LocalDate dOB;
    @OneToMany
    private List<Flight> flights;

    public TouristCreator(String name, String surname, String country, String sex, String notes, LocalDate dOB) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.country = country;
        this.notes = notes;
        this.dOB = dOB;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getdOB() {
        return dOB;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public String getSex() {
        return sex;
    }
}

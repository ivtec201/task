package com.rekrut.task.model;

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
    private String country;
    private String notes;
    private LocalDate dOB;
    @OneToMany
    private List<Flight> flights;

    public Tourist(String name, String surname, String country, String notes, LocalDate dOB, List<Flight> flights) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.notes = notes;
        this.dOB = dOB;
        this.flights = flights;
    }

    public Tourist() {
    }
}

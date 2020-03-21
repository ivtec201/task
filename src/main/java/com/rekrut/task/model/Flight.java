package com.rekrut.task.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Flight {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime takeOff;
    private LocalDateTime arrival;
    private int numberOfSeats;
    @OneToMany
    private List<Tourist> tourists;
    private double ticketPrice;

    public Flight() {
    }

    public Flight(LocalDateTime takeOff, LocalDateTime arrival, int numberOfSeats, List<Tourist> tourists, double ticketPrice) {
        this.takeOff = takeOff;
        this.arrival = arrival;
        this.numberOfSeats = numberOfSeats;
        this.tourists = tourists;
        this.ticketPrice = ticketPrice;
    }
}

package com.rekrut.task.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    private int totalNumberOfSeats;
    @OneToMany
    @JsonIgnoreProperties(value = "tourists")
    private List<Tourist> tourists;
    private double ticketPrice;

    public Flight() {
    }

    public Flight(LocalDateTime takeOff, LocalDateTime arrival, int numberOfSeats, double ticketPrice) {
        this.takeOff = takeOff;
        this.arrival = arrival;
        this.numberOfSeats = numberOfSeats;
        this.totalNumberOfSeats = numberOfSeats;
        this.ticketPrice = ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(LocalDateTime takeOff) {
        this.takeOff = takeOff;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public void setTourists(List<Tourist> tourists) {
        this.tourists = tourists;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    public void setTotalNumberOfSeats(int currentNumberOfSeats) {
        this.totalNumberOfSeats = currentNumberOfSeats;
    }
}

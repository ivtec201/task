package com.rekrut.task.model;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

public class FlightCreator {

    private LocalDateTime takeOff;
    private LocalDateTime arrival;
    private int numberOfSeats;
    @OneToMany
    private List<Tourist> tourists;
    private double ticketPrice;

    public FlightCreator(LocalDateTime takeOff, LocalDateTime arrival, int numberOfSeats, double ticketPrice) {
        this.takeOff = takeOff;
        this.arrival = arrival;
        this.numberOfSeats = numberOfSeats;
        this.ticketPrice = ticketPrice;
    }

    public LocalDateTime getTakeOff() {
        return takeOff;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}

package com.rekrut.task.controller;

import com.rekrut.task.model.Flight;
import com.rekrut.task.model.FlightCreator;
import com.rekrut.task.model.Tourist;
import com.rekrut.task.repository.FlightRepository;
import com.rekrut.task.repository.TouristRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightController {

    private FlightRepository flightRepo;
    private TouristRepository tourRepo;

    @Autowired
    public FlightController(FlightRepository flightRepo, TouristRepository tourRepo) {
        this.flightRepo = flightRepo;
        this.tourRepo = tourRepo;
    }

    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return flightRepo.findAll();
    }

    @PostMapping("/flights")
    public ResponseEntity<?> addFlights(@RequestBody FlightCreator creator){

        Flight flight = new Flight(creator.getTakeOff(), creator.getArrival(), creator.getNumberOfSeats(), creator.getTicketPrice());

        flightRepo.save(flight);

        return ResponseEntity.ok(flight);
    }

    @DeleteMapping("/flights/{id}")
    public void removeTourist(@PathVariable("id") Long id) {
        flightRepo.deleteById(id);
    }

    @PatchMapping("/flights/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Tourist updated!")
    public void modifyTourist(@RequestBody Long tourID, String order,
                              @PathVariable Long flightID) throws NotFoundException, NoSeatsException {

        Flight flight = flightRepo.getFlightById(flightID);
        Tourist tourist = tourRepo.getTouristById(tourID);

        if (flight == null) {
            throw new NotFoundException(String.format("Flight with ID %d does not exist!", flightID));
        }
        if (flight.getNumberOfSeats() < 1) {
            throw new NoSeatsException("There's no seats left on this flight!");
        } else if (order == "update") {
            partialUpdate(flightID, tourID);
        } else if (order == "delete"){
            deleteTourist(flight, tourID);
        }
    }

    private void deleteTourist(Flight flight, Long id) {
        Tourist tourist = tourRepo.getTouristById(id);
        flight.getTourists().remove(tourist);
        if (flight.getNumberOfSeats() < flight.getTotalNumberOfSeats()) {
            flight.setNumberOfSeats(flight.getNumberOfSeats() + 1);
        }
    }

//    private void partialUpdateTourist(Tourist tourist, Long id) {
//        Flight flight = flightRepo.getFlightById(id);
//        List<Flight> flights = new ArrayList<>();
//
//        if (flight != null) {
//            flights.add(flight);
//            tourist.setFlights(flights);
//        }
//    }
    private void partialUpdate(Long flightID, Long tourID) {
        Flight flight = flightRepo.getFlightById(flightID);
        Tourist tourist = tourRepo.getTouristById(tourID);
        List<Tourist> tourists = new ArrayList<>();
        List<Flight> flights = new ArrayList<>();

        Patch(flight, tourist, tourists, flights);
    }

    static void Patch(Flight flight, Tourist tourist, List<Tourist> tourists, List<Flight> flights) {
        if (flight != null && flight.getTourists().size() < flight.getTotalNumberOfSeats()) {
            flights.add(flight);
            tourist.setFlights(flights);
            tourists.add(tourist);
            flight.setTourists(tourists);
            flight.setNumberOfSeats(flight.getNumberOfSeats() - 1);
        }
    }
}

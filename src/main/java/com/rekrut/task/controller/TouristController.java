package com.rekrut.task.controller;

import com.rekrut.task.model.Flight;
import com.rekrut.task.model.Tourist;
import com.rekrut.task.model.TouristCreator;
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
public class TouristController {

    private TouristRepository tourRepo;
    private FlightRepository flightRepo;

    @Autowired
    public TouristController(TouristRepository tourRepo, FlightRepository flightRepo) {
        this.tourRepo = tourRepo;
        this.flightRepo = flightRepo;
    }

    @GetMapping("/tourists")
    public List<Tourist> getTourists() {
        return tourRepo.findAll();
    }

    @PostMapping("/tourists")
    public ResponseEntity<?> addTourist(@RequestBody TouristCreator creator) {

        Tourist tourist = new Tourist(creator.getName(), creator.getSurname(), creator.getSex(), creator.getCountry(),
                creator.getNotes(), creator.getdOB());

        tourRepo.save(tourist);

        return ResponseEntity.ok(tourist);
    }

    @DeleteMapping("/tourists/{id}")
    public void removeTourist(@PathVariable("id") Long id) {
        tourRepo.deleteById(id);
    }

    @PatchMapping("/tourists/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Tourist updated!")
    public void modifyTourist(@RequestBody Long flightID, String order,
                              @PathVariable Long tourID) throws NotFoundException, NoSeatsException {

        Tourist tourist = tourRepo.getTouristById(tourID);
        Flight flight = flightRepo.getFlightById(flightID);

        if (tourist == null) {
            throw new NotFoundException(String.format("Tourist with ID %d does not exist!", tourID));
        }
        if (flight.getNumberOfSeats() < 1) {
            throw new NoSeatsException("There's no seats left on this flight!");
        } else if (order == "update") {
            partialUpdate(tourID, flightID);
        } else if (order == "delete") {
            deleteFlight(tourist, flightID);
        }
    }

    private void deleteFlight(Tourist tourist, Long id) {
        Flight flight = flightRepo.getFlightById(id);
        tourist.getFlights().remove(flight);
        if (flight.getNumberOfSeats() < flight.getTotalNumberOfSeats()) {
            flight.setNumberOfSeats(flight.getNumberOfSeats() + 1);
        }
    }

    private void partialUpdate(Long tourID, Long flightID) {
        Tourist tourist = tourRepo.getTouristById(tourID);
        Flight flight = flightRepo.getFlightById(flightID);
        List<Flight> flights = new ArrayList<>();
        List<Tourist> tourists = new ArrayList<>();

        FlightController.Patch(flight, tourist, tourists, flights);
    }

//    private void partialUpdateFlight(Long flightID, Long tourID) {
//        Flight flight = flightRepo.getFlightById(flightID);
//        Tourist tourist = tourRepo.getTouristById(tourID);
//        List<Tourist> tourists = new ArrayList<>();
//
//        if (tourist != null && flight.getTourists().size() < flight.getTotalNumberOfSeats()) {
//            tourists.add(tourist);
//            flight.setTourists(tourists);
//        }
//    }


    /*@PatchMapping("/tourists/{id}")           patch na rozne rzeczy
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Tourist updated!")
    public void modifyTourist(@RequestBody Map<String, Object> updates, @PathVariable Long id) throws NotFoundException {
        Tourist tourist = tourRepo.getTouristById(id);
        if (tourist == null) {
            throw new NotFoundException(String.format("Tourist with ID %d does not exist!", id));
        }
        partialUpdate(tourist, updates);
    }

    private void partialUpdate(Tourist tourist, Map<String, Object> updates) {
        if (updates.containsKey("name")) {
            tourist.setName((String) updates.get("name"));
        }
        if (updates.containsKey("surname")) {
            tourist.setSurname((String) updates.get("surname"));
        }
        if (updates.containsKey("sex")) {
            tourist.setSurname((String) updates.get("sex"));
        }
        if (updates.containsKey("country")) {
            tourist.setSurname((String) updates.get("country"));
        }
        if (updates.containsKey("notes")) {
            tourist.setSurname((String) updates.get("notes"));
        }
        if (updates.containsKey("DOB")) {
            tourist.setSurname((String) updates.get("DOB"));
        }
        tourRepo.partialUpdated(tourist);
    }*/
}

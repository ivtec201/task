package com.rekrut.task.repository;

import com.rekrut.task.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    List<Flight> findAll();

    Flight save(Flight flight);

    @Override
    void deleteById(Long id);

    Flight getFlightById(Long id);
}

package com.rekrut.task.controller;

import com.rekrut.task.model.Tourist;
import com.rekrut.task.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TouristController {

    private TouristRepository tourRep;

    @Autowired
    public TouristController(TouristRepository tourRep) {
        this.tourRep = tourRep;
    }

    @GetMapping("/tourists")
    public List<Tourist> getTourists(){
        return tourRep.findAll();
    }

    @PostMapping("/tourists")
    public void addTourist(@RequestBody Tourist tourist){

    }
}

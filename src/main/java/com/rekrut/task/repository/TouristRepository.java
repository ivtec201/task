package com.rekrut.task.repository;

import com.rekrut.task.model.Tourist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristRepository extends CrudRepository<Tourist, Long> {

    List<Tourist> findAll();

    Tourist save(Tourist tourist);
}

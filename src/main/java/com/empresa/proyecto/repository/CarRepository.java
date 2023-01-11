package com.empresa.proyecto.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.empresa.proyecto.model.Car;

@Repository
public interface CarRepository extends ReactiveSortingRepository<Car, Long> {

}

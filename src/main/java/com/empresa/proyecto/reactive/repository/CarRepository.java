package com.empresa.proyecto.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.empresa.proyecto.reactive.entity.Car;

@Repository
public interface CarRepository extends ReactiveSortingRepository<Car, Long> {

}

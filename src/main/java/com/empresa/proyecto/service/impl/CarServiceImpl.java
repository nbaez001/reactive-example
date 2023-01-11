package com.empresa.proyecto.service.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.empresa.proyecto.entity.Car;
import com.empresa.proyecto.repository.CarRepository;
import com.empresa.proyecto.service.CarService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	public Mono<Car> findById(Long id) {
		return carRepository.findById(id).doOnNext(p -> log.info("Car with id " + p.getId()));
	}

	public Mono<Void> deleteById(Long id) {
		return carRepository.deleteById(id).doOnNext(c -> log.info("Car with id {} deleted", id));
	}

	public Mono<Car> save(Car car) {
		return carRepository.save(car);
	}

	public Mono<Car> update(Car car) {
		return carRepository.save(car);
	}

	public Flux<Car> findAll(Sort sort) {
		return carRepository.findAll(sort).delaySequence(Duration.ofSeconds(5));
	}
}

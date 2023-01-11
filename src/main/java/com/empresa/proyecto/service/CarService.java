package com.empresa.proyecto.service;

import org.springframework.data.domain.Sort;

import com.empresa.proyecto.entity.Car;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarService {

	public Mono<Car> findById(Long id);

	public Mono<Void> deleteById(Long id);

	public Mono<Car> save(Car car);

	public Mono<Car> update(Car car);

	public Flux<Car> findAll(Sort sort);
}

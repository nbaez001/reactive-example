package com.empresa.proyecto.routes;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import com.empresa.proyecto.entity.Car;
import com.empresa.proyecto.service.CarService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CarHandler {

	@Autowired
	private CarService service;

	public Mono<ServerResponse> getAll(ServerRequest req) {
		var all = service.findAll(Sort.by("model", "brand"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(all, Car.class));
	}

	public Mono<ServerResponse> getOne(ServerRequest req) {
		var mono = service.findById(Long.valueOf(req.pathVariable("id")))
				.switchIfEmpty(Mono.error(() -> new ResponseStatusException(NOT_FOUND)));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(mono, Car.class));
	}

	public Mono<ServerResponse> save(ServerRequest req) {
		final Mono<Car> car = req.bodyToMono(Car.class);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(car.flatMap(service::save), Car.class));
	}

	public Mono<ServerResponse> update(ServerRequest req) {
		final Mono<Car> car = req.bodyToMono(Car.class);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(car.flatMap(service::update), Car.class));
	}

	public Mono<ServerResponse> deleteById(ServerRequest req) {
		var id = Long.valueOf(req.pathVariable("id"));
		log.info("Car with id {} will be deleted", id);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.deleteById(id), Void.class);
	}
}

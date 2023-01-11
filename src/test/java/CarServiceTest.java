import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.empresa.proyecto.model.Car;
import com.empresa.proyecto.service.CarService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class CarServiceTest {

	@Autowired
	CarService carService;

	@Test
	void testFindById() {
		Mono<Car> uno = carService.findById(1L);
		StepVerifier.create(uno).expectNextMatches(obj -> obj.getModel().equals("MUSTANG")).verifyComplete();
	}

	@Test
	void testFindAll() {
		Flux<Car> uno = carService.findAll(Sort.by("model", "brand"));
		StepVerifier.create(uno)
				.expectNextMatches(obj -> obj.getModel().equals("MUSTANG"))
				.thenAwait(Duration.ofSeconds(2))
				.expectNextMatches(obj -> obj.getModel().equals("TOLEDO"))
				.verifyComplete();
	}
}

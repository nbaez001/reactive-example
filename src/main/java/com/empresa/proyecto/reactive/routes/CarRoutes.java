package com.empresa.proyecto.reactive.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CarRoutes {

  @Bean
  public RouterFunction<ServerResponse> routes(CarHandler handler) {
    return RouterFunctions.route().path(
        "/cars", builder -> builder
            .GET("", handler::getAll)
            .GET("/{id}", handler::getOne)
            .POST("",handler::save)
            .PUT("",handler::save)
            .DELETE("/{id}", handler::deleteById)
    ).build();
  }
}

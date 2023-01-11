package com.empresa.proyecto.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String model;

	private String brand;

	private String color;

}

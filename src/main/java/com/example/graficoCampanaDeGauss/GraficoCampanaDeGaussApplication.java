package com.example.graficoCampanaDeGauss;

import com.example.graficoCampanaDeGauss.Service.EstudianteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraficoCampanaDeGaussApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraficoCampanaDeGaussApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(EstudianteService estudianteService) {
		return args -> {
			estudianteService.guardarEstudiantes();
		};

	}
}

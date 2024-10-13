package com.example.graficoCampanaDeGauss.Controller;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;
import com.example.graficoCampanaDeGauss.Repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/estudiantes")
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    @GetMapping("/edades")
    public List<Double> getAllEdades() {
        return estudianteRepository.findAll().stream()
            .map(Estudiante::getEdad)
            .collect(Collectors.toList());
}
    @GetMapping("/alturas")
    public List<Double> getAllAlturas() {
        return estudianteRepository.findAll().stream()
                .map(Estudiante::getAltura)
                .collect(Collectors.toList());
    }

    @GetMapping("/pesos")
    public List<Double> getAllPesos() {
        return estudianteRepository.findAll().stream()
                .map(Estudiante::getPeso)
                .collect(Collectors.toList());
    }

    @GetMapping("/notasFinales")
    public List<Double> getAllNotasFinales() {
        return estudianteRepository.findAll().stream()
                .map(Estudiante::getNotaFinal)
                .collect(Collectors.toList());
    }

    @PostMapping("/sendData")
    public Double sendData(@RequestBody Double data) {
        // Aquí puedes procesar los datos recibidos como prefieras
        // Por ahora, simplemente los devolvemos
        return data;
    }
}

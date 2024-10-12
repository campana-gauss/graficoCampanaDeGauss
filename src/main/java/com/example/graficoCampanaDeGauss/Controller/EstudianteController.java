package com.example.graficoCampanaDeGauss.Controller;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;
import com.example.graficoCampanaDeGauss.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/estudiantes")
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }
}

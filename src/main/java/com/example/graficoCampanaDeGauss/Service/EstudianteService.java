package com.example.graficoCampanaDeGauss.Service;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;
import com.example.graficoCampanaDeGauss.Repository.EstudianteRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


    public void guardarEstudiantes() {

        // Eliminar la tabla si existe
        estudianteRepository.deleteAllInBatch();


        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/datos_estudiantes.csv"))) {
            // Omitir la fila de encabezado
            br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                Estudiante estudiante = new Estudiante();
                estudiante.setId(Long.parseLong(campos[0]));
                estudiante.setEdad(Double.parseDouble(campos[1]));
                estudiante.setAltura(Double.parseDouble(campos[2]));
                estudiante.setPeso(Double.parseDouble(campos[3]));
                estudiante.setNotaFinal(Double.parseDouble(campos[4]));
                estudiante.setGenero(campos[5]);
                estudianteRepository.save(estudiante);
            }
        } catch (IOException e) {
            throw new RuntimeException("fallo al obtener los datos", e);
        }
    }
    public Optional<Estudiante> obtenerEstudiante(Long id) {
        return estudianteRepository.findById(id);
    }
}
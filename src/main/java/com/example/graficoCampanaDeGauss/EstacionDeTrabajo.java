package com.example.graficoCampanaDeGauss;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;
import com.example.graficoCampanaDeGauss.Service.EstudianteService;

public class EstacionDeTrabajo extends Thread {
    private BufferCompartido buffer;
    private EstudianteService estudianteService;

    public EstacionDeTrabajo(BufferCompartido buffer, EstudianteService estudianteService) {
        this.buffer = buffer;
        this.estudianteService = estudianteService;
    }

    @Override
    public void run() {
        while (true) {
            Componente componente = producirComponente();
            buffer.agregar(componente);
        }
    }

    private Componente producirComponente() {
        // Aquí va la lógica para producir un componente
        Estudiante estudiante = estudianteService.obtenerEstudiante(1L).orElse(null);
        return new Componente(estudiante);
    }
}

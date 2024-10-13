package com.example.graficoCampanaDeGauss;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;
import com.example.graficoCampanaDeGauss.Repository.EstudianteRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class LineaDeProduccion implements Runnable {
    private final BlockingQueue<Estudiante> buffer;
    private final Semaphore semaphore;
    private volatile boolean running = true;

    public LineaDeProduccion(BlockingQueue<Estudiante> buffer, Semaphore semaphore) {
        this.buffer = buffer;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (running) {
            try {
                semaphore.acquire(); // Controlar la cantidad de bolas que se ensamblan simultáneamente
                Estudiante estudiante = buffer.take(); // Tomar un estudiante (componente) del buffer
                // Simular el ensamblaje del componente en la máquina
                estudiante.run(); // Ejecuta la lógica de proceso del estudiante
                semaphore.release();  // Liberar el semáforo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}

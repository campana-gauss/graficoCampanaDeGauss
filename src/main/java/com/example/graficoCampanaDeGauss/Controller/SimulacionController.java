package com.example.graficoCampanaDeGauss.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class SimulacionController {

    // Array para mantener el estado de la simulación progresiva
    private int[] contenedores = new int[10];  // 10 contenedores para la simulación

    @GetMapping("/datosSimulacion")
    public SseEmitter streamSimulacion() {
        SseEmitter emitter = new SseEmitter(30000L); // Timeout de 30 segundos

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                // Simular la caída de nuevas bolas progresivamente
                simularCaidaBolas();

                // Enviar los datos al frontend
                emitter.send(Arrays.toString(contenedores)); // Convertir el array a JSON

            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }, 0, 2, TimeUnit.SECONDS); // Cada 2 segundos

        emitter.onCompletion(() -> System.out.println("SSE completed"));
        emitter.onTimeout(() -> {
            System.out.println("SSE timeout");
            emitter.complete();
        });

        return emitter;
    }

    // Método para simular la caída progresiva de bolas en los contenedores
    private void simularCaidaBolas() {
        Random random = new Random();
        int numBolas = 10; // Número de bolas que caen cada vez

        // Distribuir aleatoriamente las bolas entre los contenedores, imitando una curva normal
        for (int i = 0; i < numBolas; i++) {
            // Generar un número aleatorio con una distribución normal (media en el centro)
            double valor = random.nextGaussian() * 2 + 5;  // Media en el contenedor 5
            int contenedor = Math.max(0, Math.min(9, (int) Math.round(valor))); // Limitar entre 0 y 9
            contenedores[contenedor]++;  // Añadir una bola al contenedor
        }
    }
}

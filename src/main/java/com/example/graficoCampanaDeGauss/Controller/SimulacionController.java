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

    private int[] contenedores = new int[10];  // 10 contenedores, todos inicialmente vacíos
    private final Random random = new Random();
    private int totalBolas = 0; // Contador de bolas que han caído

    @GetMapping("/datosSimulacion")
    public SseEmitter streamSimulacion() {
        SseEmitter emitter = new SseEmitter(30000L); // Timeout de 30 segundos

        // Simulación periódica: cada 1 segundo "caen" más bolas
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                if (totalBolas < 100) {  // Simular hasta 100 bolas
                    caerBola();
                    emitter.send(Arrays.toString(contenedores));  // Enviar el estado actual de los contenedores
                } else {
                    emitter.complete();  // Completar el SSE cuando todas las bolas han caído
                }

            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }, 0, 1, TimeUnit.SECONDS); // Cada 1 segundo

        return emitter;
    }

    private void caerBola() {
        // Generar un número con distribución normal (media en el centro, contenedor 5)
        double valor = random.nextGaussian() * 1.5 + 5;  // Distribución centrada en el contenedor 5
        int contenedor = Math.max(0, Math.min(9, (int) Math.round(valor))); // Limitar entre 0 y 9
        contenedores[contenedor]++;  // Añadir una bola al contenedor
        totalBolas++;  // Aumentar el contador de bolas
    }
}


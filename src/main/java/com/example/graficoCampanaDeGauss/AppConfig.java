package com.example.graficoCampanaDeGauss;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

@Configuration
public class AppConfig {

    @Bean
    public BlockingQueue<Estudiante> estudianteQueue() {
        return new LinkedBlockingQueue<>();
    }

    @Bean
    public Semaphore semaphore() {
        // Inicializar el semáforo con un número adecuado de permisos (ej: 10)
        return new Semaphore(10);  // Número de permisos puede variar según tus necesidades
    }
}
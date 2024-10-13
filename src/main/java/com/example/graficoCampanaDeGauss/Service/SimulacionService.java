package com.example.graficoCampanaDeGauss.Service;

import com.example.graficoCampanaDeGauss.EstacionDeTrabajo;
import com.example.graficoCampanaDeGauss.LineaDeProduccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulacionService {
    private final List<EstacionDeTrabajo> estaciones;
    private final LineaDeProduccion lineaDeProduccion;
    private int estacionActual = 0;
    private Thread threadLineaDeProduccion;

    @Autowired
    public SimulacionService(List<EstacionDeTrabajo> estaciones, LineaDeProduccion lineaDeProduccion) {
        this.estaciones = estaciones;
        this.lineaDeProduccion = lineaDeProduccion;
    }

    public void iniciarSimulacion() {
        // Iniciar las estaciones de trabajo en round-robin
        for (EstacionDeTrabajo estacion : estaciones) {
            new Thread(estacion).start();
        }

        // Iniciar la línea de producción
        threadLineaDeProduccion = new Thread(lineaDeProduccion);
        threadLineaDeProduccion.start();
    }

    public void detenerSimulacion() {
        // Detener las estaciones de trabajo
        for (EstacionDeTrabajo estacion : estaciones) {
            estacion.stop();
        }
        lineaDeProduccion.stop();
        threadLineaDeProduccion.interrupt();
    }
}

package com.example.graficoCampanaDeGauss;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BufferCompartido {
    private BlockingQueue<Componente> buffer;

    public BufferCompartido() {
        buffer = new LinkedBlockingQueue<>();
    }

    public void agregar(Componente componente) {
        try {
            buffer.put(componente);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Componente retirar() {
        try {
            return buffer.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}

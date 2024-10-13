package com.example.graficoCampanaDeGauss;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;

public class Componente {
    private Estudiante estudiante;

    public Componente(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}

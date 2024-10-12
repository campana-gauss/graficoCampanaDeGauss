package com.example.graficoCampanaDeGauss.Repository;

import com.example.graficoCampanaDeGauss.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
package com.ProyectoIntegrador.demo.repository;

import com.ProyectoIntegrador.demo.model.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PuntuacionRepository  extends JpaRepository<Puntuacion, Long> {
}

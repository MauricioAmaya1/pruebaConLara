package com.ProyectoIntegrador.demo.repository;

import com.ProyectoIntegrador.demo.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository <Imagen, Long> {
}

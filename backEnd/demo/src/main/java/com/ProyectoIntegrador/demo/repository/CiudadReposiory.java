package com.ProyectoIntegrador.demo.repository;

import com.ProyectoIntegrador.demo.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadReposiory extends JpaRepository<Ciudad, Long> {
}

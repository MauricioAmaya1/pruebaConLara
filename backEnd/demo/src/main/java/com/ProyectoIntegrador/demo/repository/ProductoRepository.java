package com.ProyectoIntegrador.demo.repository;
import com.ProyectoIntegrador.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long> {


}

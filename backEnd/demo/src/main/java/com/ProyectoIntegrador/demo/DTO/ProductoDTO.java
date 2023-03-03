package com.ProyectoIntegrador.demo.dto;

import com.ProyectoIntegrador.demo.model.Imagen;
import com.ProyectoIntegrador.demo.model.Puntuacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    private Long id_producto;
    private String nombre;
    private String descripcion;
    private String direccion;

    private CategoriaDTO categoria;
    private CiudadDTO ciudad;


   // private Integer puntuaciones;
    // private List<Imagen> lista_de_imagenes;



}

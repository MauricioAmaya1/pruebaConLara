package com.ProyectoIntegrador.demo.DTO;

import com.ProyectoIntegrador.demo.model.Categoria;
import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.model.Imagen;
import com.ProyectoIntegrador.demo.model.Puntuacion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id_producto;
    private String nombre;
    private String descripcion;
    private String direccion;


    private Long id_categoria;
    private Long id_ciudad;

    /*
    private List<Puntuacion> puntuaciones;
    private List<Imagen> listadeimagenes;
    */


}

package com.ProyectoIntegrador.demo.DTO;

import com.ProyectoIntegrador.demo.model.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) //ignora cualquier propiedad no reconocida (osea que no este determinada en el DTO) durante la deserialización
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id_categoria;
    private String titulo;
    private String descripcion;
    private String url_imagen;
    private List<Producto> productos;


}
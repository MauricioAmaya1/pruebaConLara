package com.ProyectoIntegrador.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true) //ignora cualquier propiedad no reconocida (osea que no este determinada en el DTO) durante la deserialización
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id_categoria;
    private String titulo;
    private String descripcion;
    private String url_imagen;


}
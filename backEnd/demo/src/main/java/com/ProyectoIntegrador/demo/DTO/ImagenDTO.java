package com.ProyectoIntegrador.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagenDTO {

    private Long id_imagen;
    private String titulo;
    private String url_imagen;
    private Long id_producto;

}

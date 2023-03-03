package com.ProyectoIntegrador.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_imagen;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(length = 250, nullable = false)
    private String url_imagen;

    //@Column(name = "id_producto")
    //private Long id_producto;



}

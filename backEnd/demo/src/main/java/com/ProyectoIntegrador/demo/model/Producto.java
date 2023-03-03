package com.ProyectoIntegrador.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 300, nullable = false)
    private String descripcion;

    @Column(length = 100, nullable = false)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto")
    private List<Imagen> lista_de_imagenes;       //unidireccional

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto")
    private List<Puntuacion> puntuaciones;     //unidireccional

    @ManyToMany
    @JoinTable(name ="caracteristicas_de_productos",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name ="id_caracteristica"))              //unidireccional
    private List <Caracteristica> caracteristicas;





}

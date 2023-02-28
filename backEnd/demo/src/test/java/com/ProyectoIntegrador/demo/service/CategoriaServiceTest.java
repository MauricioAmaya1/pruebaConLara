package com.ProyectoIntegrador.demo.service;


import com.ProyectoIntegrador.demo.exception.BadRequestException;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Categoria;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;


    @Test
    @Order(1)
    void guardarCategoria() throws BadRequestException {

        Categoria categoria = new Categoria(1L,"depto","es un departamento","www.google.com/images");
        Categoria categoriaAGuardar = categoriaService.agregarCategoria(categoria);
        Assertions.assertNotNull(categoriaAGuardar);

    }


    @Test
    @Order(2)
    void buscarCategoria() throws BadRequestException {

        Long idABuscar = 1L;
        Optional<Categoria> categoria = categoriaService.buscarCategoria(idABuscar);
        Assertions.assertNotNull(categoria.get());

    }

    @Test
    @Order(3)
    void buscarTodosCategorias() {
        List<Categoria> categoria= categoriaService.listaCategoria();
        Integer cantidadEsperada = 1;
        Assertions.assertEquals(cantidadEsperada, categoria.size());
    }



    @Test
    @Order(4)
    void actualizarCategoria() throws BadRequestException {

        Categoria categoriaActualizar = new Categoria(1L,"Casa","es una casa","www.google.com/");
        categoriaService.actualizarCategoria(categoriaActualizar);
        Optional<Categoria> categoriaActualizado= categoriaService.buscarCategoria(categoriaActualizar.getId_categoria());
        assertEquals("Casa",categoriaActualizado.get().getTitulo());

    }


    @Test
    @Order(5)
    void eliminarCategoria() throws BadRequestException, ResourceNotFoundException {

        Long idAEliminar=1L;
        categoriaService.eliminarCategoria(idAEliminar);
        Optional<Categoria> odontologoEliminado = categoriaService.buscarCategoria(idAEliminar);
        Assertions.assertFalse(odontologoEliminado.isPresent());

    }





}

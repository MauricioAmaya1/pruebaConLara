package com.ProyectoIntegrador.demo.service;


import com.ProyectoIntegrador.demo.dto.CategoriaDTO;
import com.ProyectoIntegrador.demo.exception.BadRequestException;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Categoria;
import com.ProyectoIntegrador.demo.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ObjectMapper mapper;


    @Test
    @Order(1)
    void guardarCategoria() throws BadRequestException {
        Set<Producto> productos = new HashSet<>();
        Categoria categoria = new Categoria(11L,"depto2","es un departamento",
                "www.google.com/images", productos);

        CategoriaDTO categoriaDTO = mapper.convertValue(categoria, CategoriaDTO.class);
        CategoriaDTO categoriaCreada = categoriaService.agregarCategoria(categoriaDTO);

        Assertions.assertNotNull(categoriaService.agregarCategoria(categoriaCreada));

    }


    @Test
    @Order(2)
    void buscarCategoria() throws BadRequestException {

        Long idABuscar = 1L;
        CategoriaDTO categoria = categoriaService.buscarCategoria(idABuscar);
        Assertions.assertNotNull(categoria.getId_categoria());

    }

    @Test
    @Order(3)
    void buscarTodosCategorias() {
        List<CategoriaDTO> categoria= categoriaService.listaCategoria();
        Assertions.assertTrue(categoria.size() > 0);
    }



    @Test
    @Order(4)
    void actualizarCategoria() {

        CategoriaDTO categoriaActualizar = new CategoriaDTO(1L,"Nueva categoria","es una nueva cat","soy una url");

        categoriaService.actualizarCategoria(categoriaActualizar);

        Assertions.assertNotNull(categoriaActualizar);

    }


    @Test
    @Order(5)
    void eliminarCategoria() throws ResourceNotFoundException {

        Long idAEliminar=1L;

        categoriaService.eliminarCategoria(idAEliminar);

        CategoriaDTO categoriaDTOEliminada = categoriaService.buscarCategoria(idAEliminar);

        Assertions.assertNull(categoriaDTOEliminada);

    }





}

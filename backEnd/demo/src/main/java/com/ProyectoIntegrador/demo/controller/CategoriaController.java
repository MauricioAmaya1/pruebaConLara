package com.ProyectoIntegrador.demo.controller;


import com.ProyectoIntegrador.demo.exception.BadRequestException;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Categoria;
import com.ProyectoIntegrador.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> registrarCategoria (@RequestBody Categoria categoria) throws BadRequestException {
        return ResponseEntity.ok(categoriaService.agregarCategoria(categoria));
    }


    @PutMapping
    public ResponseEntity<String> actualizarCategoria (@RequestBody Categoria categoria) throws BadRequestException {

        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoria(categoria.getId_categoria());

        if (categoriaBuscada.isPresent()){
            categoriaService.actualizarCategoria(categoria);
            return ResponseEntity.ok("Se actualizó la categoria con " + "titulo " + categoria.getTitulo());
        } else {
            return ResponseEntity.badRequest().body("La categoria con titulo " + categoria.getTitulo() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listaCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId (@PathVariable Long id) throws  BadRequestException {

        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoria(id);

        if (categoriaBuscada.isPresent()){
            return ResponseEntity.ok(categoriaBuscada.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {

        Optional<Categoria> categoriaBuscada = categoriaService.buscarCategoria(id);

        if (categoriaBuscada.isPresent()){
            categoriaService.eliminarCategoria(id);
            return ResponseEntity.ok("Se elimino la categoria con Id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }

    }



}

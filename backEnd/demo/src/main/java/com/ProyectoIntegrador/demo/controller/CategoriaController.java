package com.ProyectoIntegrador.demo.controller;


import com.ProyectoIntegrador.demo.dto.CategoriaDTO;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> registrarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.agregarCategoria(categoriaDTO));
    }


    @PutMapping
    public ResponseEntity<CategoriaDTO> actualizarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
       return ResponseEntity.ok(categoriaService.actualizarCategoria(categoriaDTO));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listaCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarCategoria(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(categoriaService.eliminarCategoria(id));
    }
}

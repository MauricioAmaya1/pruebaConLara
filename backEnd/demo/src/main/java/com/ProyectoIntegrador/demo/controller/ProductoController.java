package com.ProyectoIntegrador.demo.controller;

import com.ProyectoIntegrador.demo.DTO.ProductoDTO;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Producto;
import com.ProyectoIntegrador.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {


    private ProductoService productoService;

    @Autowired
    public ProductoController( ProductoService productoService ){
        this.productoService = productoService;
    }


    @PostMapping
    public ResponseEntity<ProductoDTO> registrarProducto (@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.agregarProducto(productoDTO));
    }


    @PutMapping
    public ResponseEntity<String> actualizarProducto (@RequestBody ProductoDTO productoDTO) {

        Optional<ProductoDTO> productoBuscado = productoService.buscarProducto(productoDTO.getId_producto());

        if (productoBuscado.isPresent()){
            productoService.actualizarProducto(productoDTO);
            return ResponseEntity.ok("Se actualizó el Producto con nombre " + productoDTO.getNombre());
        } else {
            return ResponseEntity.badRequest().body("El Producto con titulo " + productoDTO.getNombre() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos(){
        return ResponseEntity.ok(productoService.listaProducto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId (@PathVariable Long id) {

        Optional<ProductoDTO> productoBuscado = productoService.buscarProducto(id);

        if (productoBuscado.isPresent()){
            return ResponseEntity.ok(productoBuscado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) throws ResourceNotFoundException {

        Optional<ProductoDTO> productoBuscado = productoService.buscarProducto(id);

        if (productoBuscado.isPresent()){
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Se elimino el Producto con Id " + id);
        }else{
            return ResponseEntity.notFound().build();
        }

    }


}


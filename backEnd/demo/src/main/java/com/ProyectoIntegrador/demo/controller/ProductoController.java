package com.ProyectoIntegrador.demo.controller;
import com.ProyectoIntegrador.demo.dto.ProductoDTO;
import com.ProyectoIntegrador.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
    public ResponseEntity<ProductoDTO> actualizarProducto (@RequestBody ProductoDTO productoDTO) {
         return ResponseEntity.ok(productoService.actualizarProducto(productoDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos(){
        return ResponseEntity.ok(productoService.listaProducto());
    }

    /*
    @GetMapping("/ciudad/{id}")
    public ResponseEntity<List<ProductoDTO>> listarProductosPorCiudad(@PathVariable Long id){
        return ResponseEntity.ok(productoService.listaProducto());
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId (@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarProducto(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id)  {
        return ResponseEntity.ok(productoService.eliminarProducto(id));

    }


}


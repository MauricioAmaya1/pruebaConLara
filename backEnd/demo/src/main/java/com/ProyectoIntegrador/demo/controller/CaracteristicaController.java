package com.ProyectoIntegrador.demo.controller;

import com.ProyectoIntegrador.demo.dto.CaracteristicaDTO;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService caracteristicaService;

    @PostMapping()
    public ResponseEntity registrarCaracteristica(@RequestBody CaracteristicaDTO caracteristicaDTO){
        return ResponseEntity.ok(caracteristicaService.agregarCaracteristica(caracteristicaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarCaracteristica(@PathVariable Long id){
        return ResponseEntity.ok(caracteristicaService.buscaCaracteristica(id));
    }


    @GetMapping()
    public ResponseEntity listarTodasCaracteristicas(){
        return ResponseEntity.ok(caracteristicaService.listarCaracteristica());
    }

    @PutMapping("/{id}")
    public ResponseEntity editar(@RequestBody CaracteristicaDTO caracteristicaDTO, @PathVariable Long id){
        return ResponseEntity.ok(caracteristicaService.actualizarCaracteristica(caracteristicaDTO, id));
    }

    @DeleteMapping()
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(caracteristicaService.eliminarCaracteristica(id));
    }


}

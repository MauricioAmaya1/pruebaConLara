package com.ProyectoIntegrador.demo.controller;

import com.ProyectoIntegrador.demo.dto.CiudadDTO;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/ciudades")
public class CiudadController {

    private CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService){
        this.ciudadService = ciudadService;
    }


    @PostMapping
    public ResponseEntity<CiudadDTO> registrarCiudad (@RequestBody CiudadDTO ciudadDTO)  {
        return ResponseEntity.ok(ciudadService.agregarCiudad(ciudadDTO));
    }


    @PutMapping
    public ResponseEntity<CiudadDTO> actualizarCiudad (@RequestBody CiudadDTO ciudadDTO)  {
          return ResponseEntity.ok(ciudadService.actualizarCiudad(ciudadDTO));
    }

    @GetMapping
    public ResponseEntity<List<CiudadDTO>> listarCuidades(){
        return ResponseEntity.ok(ciudadService.listaCiudad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> buscarCiudadPorId (@PathVariable Long id) {
        return ResponseEntity.ok(ciudadService.buscarCiudad(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCiudad(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(ciudadService.eliminarCiudad(id));

    }


}

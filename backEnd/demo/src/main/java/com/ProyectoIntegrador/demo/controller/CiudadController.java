package com.ProyectoIntegrador.demo.controller;

import com.ProyectoIntegrador.demo.exception.BadRequestException;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/ciudades")
public class CiudadController {

    private CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService){
        this.ciudadService = ciudadService;
    }


    @PostMapping
    public ResponseEntity<Ciudad> registrarCiudad (@RequestBody Ciudad ciudad) throws BadRequestException {
        return ResponseEntity.ok(ciudadService.agregarCiudad(ciudad));
    }


    @PutMapping
    public ResponseEntity<String> actualizarCiudad (@RequestBody Ciudad ciudad) throws BadRequestException {

        Optional<Ciudad> ciudadBuscada = ciudadService.buscarCiudad(ciudad.getId_ciudad());

        if (ciudadBuscada.isPresent()){
            ciudadService.actualizarCiudad(ciudad);
            return ResponseEntity.ok("Se actualizó la ciudad con " + "nombre " + ciudad.getNombre());
        } else {
            return ResponseEntity.badRequest().body("La ciudad con nombre " + ciudad.getNombre() + " no existe en la BD. No se puede actualizar algo que no existe");

        }
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> listarCuidades(){
        return ResponseEntity.ok(ciudadService.listaCiudad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> buscarCiudadPorId (@PathVariable Long id) throws  BadRequestException {

        Optional<Ciudad> ciudadBuscada = ciudadService.buscarCiudad(id);

        if (ciudadBuscada.isPresent()){
            return ResponseEntity.ok(ciudadBuscada.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCiudad(@PathVariable Long id) throws ResourceNotFoundException,BadRequestException {

        Optional<Ciudad> ciudadBuscada = ciudadService.buscarCiudad(id);

        if (ciudadBuscada.isPresent()){
            ciudadService.eliminarCiudad(id);
            return ResponseEntity.ok("Se elimino la ciudad con Id " + id);
        }else{
            return ResponseEntity.ok("La ciudad con id: " + id + ", no existe o ya fue eliminada");
        }

    }









}

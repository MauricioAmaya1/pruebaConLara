package com.ProyectoIntegrador.demo.service;


import com.ProyectoIntegrador.demo.dto.CiudadDTO;
import com.ProyectoIntegrador.demo.dto.ProductoDTO;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.model.Producto;
import com.ProyectoIntegrador.demo.repository.CiudadReposiory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadReposiory ciudadReposiory;
    private static final Logger LOGGER = Logger.getLogger(CiudadService.class);

    @Autowired
    private ObjectMapper mapper;

    public CiudadService( CiudadReposiory ciudadReposiory){
        this.ciudadReposiory = ciudadReposiory;
    }


    public CiudadDTO agregarCiudad(CiudadDTO ciudadDTO) {
        LOGGER.info("Se inicio una operacion para guardar una ciudad con nombre: " + ciudadDTO.getNombre());

        Ciudad ciudadAGuardar = mapper.convertValue(ciudadDTO , Ciudad.class);
        Ciudad ciudadGuardada = ciudadReposiory.save(ciudadAGuardar);

        return mapper.convertValue(ciudadGuardada, CiudadDTO.class);
    }

    public CiudadDTO actualizarCiudad(CiudadDTO ciudadDTO) {

        LOGGER.info("Se inicio una operacion para actualizar un producto con nombre: " + ciudadDTO.getNombre());

        Optional<Ciudad> ciudadBuscada = ciudadReposiory.findById(ciudadDTO.getId_ciudad());

        if (ciudadBuscada.isEmpty()) {
            ResponseEntity.badRequest().body("La ciudad con nombre " + ciudadDTO.getNombre() +
                    " no existe en la BD. No se puede actualizar algo que no existe");
        }

        Ciudad ciudadAActualizar = mapper.convertValue(ciudadBuscada , Ciudad.class);
        Ciudad ciudadActualizada = ciudadReposiory.save(ciudadAActualizar);

        return mapper.convertValue(ciudadActualizada, CiudadDTO.class);
    }

    public CiudadDTO buscarCiudad(Long id) {

        LOGGER.info("Se inicio una operacion para buscar una ciudad con el id: " + id);

        Optional<Ciudad> ciudadABuscar = ciudadReposiory.findById(id);

        if (ciudadABuscar.isPresent()){
            ResponseEntity.ok("La ciudad con id " + id + " se encuentra en la base de datos");
        }else{
            ResponseEntity.ok("La ciudad con id " + id + " no se encuentra en la base de datos");
        }

        return mapper.convertValue(ciudadABuscar, CiudadDTO.class);
    }

    public List<CiudadDTO> listaCiudad() {
        LOGGER.info("Se inicio una operacion de listado de ciudades ");

        List<Ciudad> ciudadesEncontradas = ciudadReposiory.findAll();

        List<CiudadDTO> ciudadDTOList = new ArrayList<>();

        for ( Ciudad c : ciudadesEncontradas ) {
            ciudadDTOList.add(mapper.convertValue(c , CiudadDTO.class));
        }

        return  ciudadDTOList;
    }

    public String eliminarCiudad(Long id) throws ResourceNotFoundException {

        LOGGER.info("Se inicio una operacion para borrar una ciudad ");

        Optional<Ciudad> ciudadABuscar = ciudadReposiory.findById(id);

        if (ciudadABuscar.isEmpty() ){
            ResponseEntity.ok("El producto que desea eliminar no existe o ya fue eliminado");
        }

        ciudadReposiory.deleteById(id);

        return "Se elimino la ciudad con Id " + id ;
    }


}
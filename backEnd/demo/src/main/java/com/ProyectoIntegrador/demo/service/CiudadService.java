package com.ProyectoIntegrador.demo.service;


import com.ProyectoIntegrador.demo.exception.BadRequestException;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Categoria;
import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.repository.CiudadReposiory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Ciudad agregarCiudad(Ciudad ciudad) {
        LOGGER.info("Se inicio una operacion para guardar una ciudad con nombre: " + ciudad.getNombre());
        return ciudadReposiory.save(ciudad);
    }

    public Ciudad actualizarCiudad(Ciudad ciudad) {
        LOGGER.info("Se inicio una operacion para actualizar una ciudad con nombre: " + ciudad.getNombre());
        return ciudadReposiory.save(ciudad);
    }

    public Optional<Ciudad> buscarCiudad(Long id) {
        LOGGER.info("Se inicio una operacion para buscar una ciudad con el id: " + id);
        return ciudadReposiory.findById(id);
    }

    public List<Ciudad> listaCiudad() {
        LOGGER.info("Se inicio una operacion de listado de ciudades ");
        return ciudadReposiory.findAll();
    }

    public void eliminarCiudad(Long id) throws ResourceNotFoundException {
        Optional<Ciudad> ciudadAEliminar = buscarCiudad(id);
        if (ciudadAEliminar.isPresent()) {
            ciudadReposiory.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de ciudad con id " + id);
        } else {

            throw new ResourceNotFoundException("La ciudad a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= " + id);
        }

    }


}
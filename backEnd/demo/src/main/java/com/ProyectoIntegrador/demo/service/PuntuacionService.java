package com.ProyectoIntegrador.demo.service;

import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Producto;
import com.ProyectoIntegrador.demo.model.Puntuacion;
import com.ProyectoIntegrador.demo.repository.PuntuacionRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuntuacionService {

    private static final Logger LOGGER = Logger.getLogger(ImagenService.class);

    private PuntuacionRepository puntuacionRepository;

    public PuntuacionService(PuntuacionRepository puntuacionRepository){
        this.puntuacionRepository = puntuacionRepository;
    }


    public Puntuacion agregarPuntuacion(Puntuacion puntuacion) {
        LOGGER.info("Se inicio una operacion para guardar una Puntuacion con id: " + puntuacion.getId_puntuacion());
        return puntuacionRepository.save(puntuacion);
    }

    public Puntuacion actualizarPuntuacion(Puntuacion puntuacion) {
        LOGGER.info("Se inicio una operacion para actualizar una Puntuacion con id: " + puntuacion.getId_puntuacion());

        Optional<Puntuacion> puntuacionBuscada = puntuacionRepository.findById(puntuacion.getId_puntuacion());

        if (puntuacionBuscada.isEmpty()){
            puntuacion.setPuntuacion(puntuacion.getPuntuacion());
            puntuacion.setId_producto(puntuacion.getId_producto());
            return puntuacionRepository.save(puntuacion);
        }else{
            LOGGER.info("La puntuacion no pude ser actualizada");
            return null;
        }
    }

    public Optional<Puntuacion> buscarPuntuacion(Long id) {
        LOGGER.info("Se inicio una operacion para buscar una Puntuacion con el id: " + id);
        return puntuacionRepository.findById(id);
    }

    public List<Puntuacion> listaPuntuacion() {
        LOGGER.info("Se inicio una operacion de listado de Puntuaciones ");
        return puntuacionRepository.findAll();
    }

    public void eliminarPuntuacion(Long id) throws ResourceNotFoundException {
        Optional<Puntuacion> puntuacionAEliminar = buscarPuntuacion(id);
        if (puntuacionAEliminar.isPresent()) {
            puntuacionRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de Puntuacion con id " + id);
        } else {
            throw new ResourceNotFoundException("La Puntuacion a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= " + id);
        }

    }


}

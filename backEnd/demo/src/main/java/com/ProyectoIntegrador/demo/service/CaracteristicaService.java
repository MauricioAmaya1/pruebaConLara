package com.ProyectoIntegrador.demo.service;

import com.ProyectoIntegrador.demo.dto.CaracteristicaDTO;
import com.ProyectoIntegrador.demo.model.Caracteristica;
import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.repository.CaracteristicaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CaracteristicaService {

    final static Logger LOGGER = Logger.getLogger(CaracteristicaService.class);

    @Autowired()
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private ObjectMapper mapper;

    public CaracteristicaDTO buscaCaracteristica(Long id) {

        Optional<Caracteristica> caracteristicaBuscada = caracteristicaRepository.findById(id);

        return mapper.convertValue(caracteristicaBuscada,CaracteristicaDTO.class);
    }


    public CaracteristicaDTO agregarCaracteristica(CaracteristicaDTO caracteristicaDTO) {

        LOGGER.info("Se inicio una operacion para agregar una caracteristica");
        Caracteristica caracteristicaNueva = new Caracteristica();
        caracteristicaNueva.setNombre(caracteristicaDTO.getNombre());

        return mapper.convertValue(caracteristicaRepository.save(caracteristicaNueva),CaracteristicaDTO.class);
    }

    public List<CaracteristicaDTO> listarCaracteristica() {

        LOGGER.info("Se inicio una operacion para listar las caracteristicas");

        return mapper.convertValue(caracteristicaRepository.findAll(), List.class);
    }


    public CaracteristicaDTO actualizarCaracteristica(CaracteristicaDTO caracteristicaDTO, Long id) {

        Caracteristica caracteristica= mapper.convertValue(this.buscaCaracteristica(id),Caracteristica.class);

        if (caracteristica!= null){
            caracteristica.setNombre(caracteristicaDTO.getNombre());
            LOGGER.info("Caracteristica actualizada correctamente");
            return mapper.convertValue(caracteristicaRepository.save(caracteristica),CaracteristicaDTO .class);
        }else{
            LOGGER.error("la caracteristica buscada no existe");
            return null;
        }
    }

    public String eliminarCaracteristica(Long id) {
        LOGGER.info("Se inicio una operacion para borrar una caracteristica ");

        Optional<Caracteristica> ciudadABuscar = caracteristicaRepository.findById(id);

        if (ciudadABuscar.isEmpty() ){
            ResponseEntity.ok("El producto que desea eliminar no existe o ya fue eliminado");
        }

        caracteristicaRepository.deleteById(id);

        return "Se elimino la ciudad con Id " + id ;
    }








}

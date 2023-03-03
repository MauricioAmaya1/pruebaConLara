package com.ProyectoIntegrador.demo.service;

import com.ProyectoIntegrador.demo.dto.ImagenDTO;
import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.model.Imagen;
import com.ProyectoIntegrador.demo.repository.ImagenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    private static Logger LOGGER = Logger.getLogger(ImagenService.class);

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ObjectMapper mapper;

    public ImagenService(ImagenRepository imagenRepository){
        this.imagenRepository = imagenRepository;
    }

    public List<Imagen> agregarNueva(List<Imagen> imagenesList) {

        List<Imagen> imagenDTOS = new ArrayList<>(imagenesList);

        LOGGER.info("LISTA DE IMAGENES RESTANTE : " + imagenDTOS);

        return imagenDTOS;

    }





}

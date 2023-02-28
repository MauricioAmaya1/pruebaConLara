package com.ProyectoIntegrador.demo.service;

import com.ProyectoIntegrador.demo.model.Ciudad;
import com.ProyectoIntegrador.demo.model.Imagen;
import com.ProyectoIntegrador.demo.repository.ImagenRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    private static final Logger LOGGER = Logger.getLogger(ImagenService.class);

    private ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository){
        this.imagenRepository = imagenRepository;
    }

    public List<Imagen> agregarNueva(List<Imagen> imagenesList) {
        return null;
    }


    public Imagen actualizarImagen(Ciudad ciudad) {
        return null;
    }

    public Optional<Imagen> buscarImagen(Long id) {
        return null;
    }

    public List<Imagen> listaImagen() {
        return null;
    }

    public void eliminarImagen() {
    }




}

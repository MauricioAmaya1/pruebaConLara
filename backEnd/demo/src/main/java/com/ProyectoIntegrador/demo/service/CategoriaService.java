package com.ProyectoIntegrador.demo.service;

import com.ProyectoIntegrador.demo.dto.CategoriaDTO;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Categoria;
import com.ProyectoIntegrador.demo.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ObjectMapper mapper;

    private static final Logger LOGGER = Logger.getLogger(CategoriaService.class);

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO)  {
        LOGGER.info("Se inicio una operacion de guardado de la categoria con titulo " + categoriaDTO.getTitulo());

        Categoria categoriaAGuardar = mapper.convertValue(categoriaDTO , Categoria.class);
        Categoria cateogoriaGuardada = categoriaRepository.save(categoriaAGuardar);

        return mapper.convertValue(cateogoriaGuardada, CategoriaDTO.class);
    }


    public CategoriaDTO actualizarCategoria(CategoriaDTO categoriaDTO){
        LOGGER.info("Se inicio una operacion de actualizado de categoria con ID= " + categoriaDTO.getId_categoria());

        Optional<Categoria> categoriaABuscar = categoriaRepository.findById(categoriaDTO.getId_categoria());

        if (categoriaABuscar.isEmpty()) {
            ResponseEntity.badRequest().body("La categoria con titulo " + categoriaDTO.getTitulo() + " no existe en la BD. No se puede actualizar algo que no existe");
        }

        Categoria categoriaAActualizar = mapper.convertValue(categoriaDTO , Categoria.class);
        Categoria categoriaActualizada = categoriaRepository.save(categoriaAActualizar);

        return mapper.convertValue(categoriaActualizada, CategoriaDTO.class);
    }

    public CategoriaDTO buscarCategoria(Long id) {
        LOGGER.info("Se inicio una operacion de busqueda de categoria con ID " + id);

        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);

        if (categoriaBuscada.isPresent()){
            ResponseEntity.ok("La categoria con id " + id + " se encuentra en la base de datos");
        }else{
            ResponseEntity.ok("La categoria con id " + id + " no se encuentra en la base de datos");
        }

        return mapper.convertValue(categoriaBuscada, CategoriaDTO.class);
    }


    public List<CategoriaDTO> listaCategoria(){
        LOGGER.info("Se inicio una operacion de listado de categorias ");

        List<Categoria> categoriasEncontradas = categoriaRepository.findAll();

        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();

        for ( Categoria c : categoriasEncontradas ) {
            categoriaDTOList.add(mapper.convertValue(c , CategoriaDTO.class));
        }

        return  categoriaDTOList;
    }

    public String eliminarCategoria(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de eliminar una categoria ");

        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);

        if (categoriaBuscada.isEmpty()){
            ResponseEntity.ok("La categoria que desea eliminar no existe o ya fue eliminado");
        }

        categoriaRepository.deleteById(id);

        return "Se elimino la categoria con Id " + id ;

    }



}

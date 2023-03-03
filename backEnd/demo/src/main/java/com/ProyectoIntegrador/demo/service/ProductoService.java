package com.ProyectoIntegrador.demo.service;


import com.ProyectoIntegrador.demo.dto.CategoriaDTO;
import com.ProyectoIntegrador.demo.dto.CiudadDTO;
import com.ProyectoIntegrador.demo.dto.ImagenDTO;
import com.ProyectoIntegrador.demo.dto.ProductoDTO;
import com.ProyectoIntegrador.demo.model.Imagen;
import com.ProyectoIntegrador.demo.model.Producto;
import com.ProyectoIntegrador.demo.model.Puntuacion;
import com.ProyectoIntegrador.demo.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {


    private static final Logger LOGGER = Logger.getLogger(ProductoService.class);
    private ProductoRepository productoRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    CiudadService ciudadService;

    @Autowired
    PuntuacionService puntuacionService;

    @Autowired
    ImagenService imagenService;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
        LOGGER.info("Se inicio una operacion para guardar un producto con nombre: " + productoDTO.getNombre());

        Producto productoAGuardar = mapper.convertValue(productoDTO , Producto.class);
        Producto productoGuardado = productoRepository.save(productoAGuardar);

        CategoriaDTO categoriaDTO =  categoriaService.buscarCategoria(productoDTO.getCategoria().getId_categoria());
        CiudadDTO ciudadDTO = ciudadService.buscarCiudad(productoDTO.getCiudad().getId_ciudad());


        //Puntuacion puntuacion = puntuacionService.agregarPuntuacion(agregarProducto());
        //List<Imagen> imagenes = imagenService.agregarNueva(productoDTO.getLista_de_imagenes());

        ProductoDTO response = mapper.convertValue(productoGuardado, ProductoDTO.class);




        //response.setCategoria(categoria);
        //response.setCiudad(ciudad);

        //return response;


        return ProductoDTO.builder()
                .id_producto(response.getId_producto())
                .nombre(response.getNombre())
                .direccion(response.getDireccion())
                .descripcion(response.getDescripcion())
                .categoria(categoriaDTO)
                .ciudad(ciudadDTO)
                //.puntuaciones(puntuacion)
                //.lista_de_imagenes(imagenes)
                .build();


    }

    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) {

        LOGGER.info("Se inicio una operacion para actualizar un producto con nombre: " + productoDTO.getNombre());

       Optional<Producto> productoBuscado = productoRepository.findById(productoDTO.getId_producto());

        if (productoBuscado.isEmpty()) {
            ResponseEntity.badRequest().body("El Producto con titulo " + productoDTO.getNombre() + " no existe en la BD. No se puede actualizar algo que no existe");
        }

        Producto productoAActualizar = mapper.convertValue(productoDTO , Producto.class);
        Producto productoActualizado = productoRepository.save(productoAActualizar);

        return mapper.convertValue(productoActualizado, ProductoDTO.class);
    }

    public ProductoDTO buscarProducto(Long id) {

        LOGGER.info("Se inicio una operacion para buscar un producto con el id: " + id);

        Optional<Producto> productoBuscado = productoRepository.findById(id);

        if (productoBuscado.isPresent()){
            ResponseEntity.ok("El producto con id " + id + " se encuentra en la base de datos");
        }else{
            ResponseEntity.ok("El producto con id " + id + " no se encuentra en la base de datos");
        }

        return mapper.convertValue(productoBuscado, ProductoDTO.class);

    }

    public List<ProductoDTO> listaProducto() {
        LOGGER.info("Se inicio una operacion de listado de productos ");

        List<Producto> productosEncontrados = productoRepository.findAll();

        List<ProductoDTO> productoDTOList = new ArrayList<>();

        for ( Producto p : productosEncontrados ) {
            productoDTOList.add(mapper.convertValue(p , ProductoDTO.class));
        }

        return  productoDTOList;
    }


    /*
    public List<ProductoDTO> listaDeProductosPorCiudad (Long ciudadId){

        List<Producto> productosEncontrados = productoRepository.findProductoByCiudadId(ciudadId);

        List<ProductoDTO> productoDTOList = new ArrayList<>();

        for ( Producto p : productosEncontrados ) {
            productoDTOList.add(mapper.convertValue(p , ProductoDTO.class));
        }

        return  productoDTOList;


    }*/

    public String eliminarProducto(Long id)  {

        LOGGER.info("Se inicio una operacion para borrar un producto ");

        Optional<Producto> productoBuscado = productoRepository.findById(id);

        if (productoBuscado.isEmpty() ){
            ResponseEntity.ok("El producto que desea eliminar no existe o ya fue eliminado");
        }

        productoRepository.deleteById(id);

        return "Se elimino el Producto con Id " + id ;

    }

}

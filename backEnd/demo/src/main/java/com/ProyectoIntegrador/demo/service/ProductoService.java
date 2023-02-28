package com.ProyectoIntegrador.demo.service;


import com.ProyectoIntegrador.demo.DTO.ProductoDTO;
import com.ProyectoIntegrador.demo.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.demo.model.Producto;
import com.ProyectoIntegrador.demo.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {


    private static final Logger LOGGER = Logger.getLogger(ProductoService.class);
    private ProductoRepository productoRepository;

    @Autowired
    private ObjectMapper mapper;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
        LOGGER.info("Se inicio una operacion para guardar un producto con nombre: " + productoDTO.getNombre());



        Producto productoAGuardar = mapper.convertValue(productoDTO , Producto.class);
        Producto productoGuardado = productoRepository.save(productoAGuardar);

        return mapper.convertValue(productoGuardado, ProductoDTO.class);
    }

    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) {
        LOGGER.info("Se inicio una operacion para actualizar un producto con nombre: " + productoDTO.getNombre());

        Producto productoAActualizar = mapper.convertValue(productoDTO , Producto.class);
        Producto productoActualizado = productoRepository.save(productoAActualizar);


        return mapper.convertValue(productoActualizado, ProductoDTO.class);
    }

    public Optional<ProductoDTO> buscarProducto(Long id) {
        LOGGER.info("Se inicio una operacion para buscar un producto con el id: " + id);

        Optional<Producto> productoBuscado = productoRepository.findById(id);

        if (productoBuscado.isPresent()){
            return Optional.of(mapper.convertValue(productoBuscado, ProductoDTO.class));
        }else{
            return Optional.empty();
        }

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

    public void eliminarProducto(Long id) throws ResourceNotFoundException {



        Optional<ProductoDTO> productoAEliminar = buscarProducto(id);

        if (productoAEliminar.isPresent()) {

            LOGGER.warn("Se realizo una operacion de eliminado de producto con id " + id);
            productoRepository.deleteById(id);

        } else {
            throw new ResourceNotFoundException("El producto a eliminar no existe en la base de datos, se intentó encontrar sin éxito en id= " + id);
        }

    }

}

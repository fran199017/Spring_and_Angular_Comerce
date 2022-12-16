package com.francisconicolau.service;


import com.francisconicolau.entity.DetalleProducto;
import com.francisconicolau.entity.Proveedor;
import com.francisconicolau.entity.dto.DetalleProductoDTO;
import com.francisconicolau.repository.DetalleProductoRepository;
import com.francisconicolau.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("detalleProductoService")
public class DetalleProductoService {


    private DetalleProductoRepository detalleProductoRepository;

    @Autowired
    public DetalleProductoService(DetalleProductoRepository detalleProductoRepository) {
        this.detalleProductoRepository = detalleProductoRepository;
    }

    public DetalleProducto findById(int id){
        var detalleProductoOpt = detalleProductoRepository.findById(id);
        if (detalleProductoOpt.isPresent()){
            var detalleProducto = detalleProductoOpt.get();
        }
        return null;
    }

    public DetalleProducto createDetalleProducto(DetalleProductoDTO detalleProductoDTO){
        var detalleProducto = new DetalleProducto();
        detalleProducto.setCost(detalleProductoDTO.getCost());
        detalleProducto.setImpuesto(detalleProductoDTO.getImpuesto());
        detalleProducto.setMaterial(detalleProductoDTO.getMaterial());
        detalleProducto.setPeso(detalleProductoDTO.getPeso());
        return detalleProductoRepository.save(detalleProducto);
    }

}

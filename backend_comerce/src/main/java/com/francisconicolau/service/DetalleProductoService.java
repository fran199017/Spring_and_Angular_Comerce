package com.francisconicolau.service;


import com.francisconicolau.entity.Descuento;
import com.francisconicolau.entity.DetalleProducto;
import com.francisconicolau.entity.Proveedor;
import com.francisconicolau.entity.dto.DetalleProductoDTO;
import com.francisconicolau.repository.DescuentoRepository;
import com.francisconicolau.repository.DetalleProductoRepository;
import com.francisconicolau.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("detalleProductoService")
public class DetalleProductoService {


    private DetalleProductoRepository detalleProductoRepository;
    private DescuentoRepository descuentoRepository;

    @Autowired
    public DetalleProductoService(DetalleProductoRepository detalleProductoRepository, DescuentoRepository descuentoRepository) {
        this.detalleProductoRepository = detalleProductoRepository;
        this.descuentoRepository = descuentoRepository;
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

    public DetalleProducto crearDescuentoEnDetalle(int id, int descuentoId) {
        var detalleProductoOpt = detalleProductoRepository.findById(id);
        if (detalleProductoOpt.isPresent()){
            var detalleProducto = detalleProductoOpt.get();
            var descuentoOpt = descuentoRepository.findById(descuentoId);
            if (descuentoOpt.isPresent()){
                var descuento = descuentoOpt.get();
                detalleProducto.setDescuento(descuento);
                var nuevoCoste = aplicarDescuento(detalleProducto.getCost(), descuento.getDescuento());
                detalleProducto.setCost(nuevoCoste);
                return detalleProductoRepository.save(detalleProducto);
            }
        }
        return null;
    }

    private float aplicarDescuento(float cost, int descuento){
        if (descuento <= 0){
            return cost;
        }
        return cost - ((cost / 100) * descuento);
    }
}

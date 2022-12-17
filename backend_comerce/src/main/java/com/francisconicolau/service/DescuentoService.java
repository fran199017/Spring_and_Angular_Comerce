package com.francisconicolau.service;


import com.francisconicolau.entity.Descuento;
import com.francisconicolau.entity.DetalleProducto;
import com.francisconicolau.entity.dto.DetalleProductoDTO;
import com.francisconicolau.repository.DescuentoRepository;
import com.francisconicolau.repository.DetalleProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("descuentoService")
public class DescuentoService {


    private DescuentoRepository descuentoRepository;

    @Autowired
    public DescuentoService(DescuentoRepository descuentoRepository) {
        this.descuentoRepository = descuentoRepository;
    }

    public Descuento findById(int id){
        var descuentoOpt = descuentoRepository.findById(id);
        if (descuentoOpt.isPresent()){
            var descuento = descuentoOpt.get();
        }
        return null;
    }

    public Descuento createDescuento(int porcentaje){
        var descuento = new Descuento();
        descuento.setDescuento(porcentaje);
        return descuentoRepository.save(descuento);
    }

    public Iterable<Descuento> getDescuentos() {
        return  descuentoRepository.findAll();
    }
}

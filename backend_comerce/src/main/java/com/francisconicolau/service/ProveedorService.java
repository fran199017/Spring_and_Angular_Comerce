package com.francisconicolau.service;


import com.francisconicolau.entity.Product;
import com.francisconicolau.entity.Proveedor;
import com.francisconicolau.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("proveedorService")
public class ProveedorService {


    private ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }


    public Iterable<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedorById(int id) {
        var proveedorOPt = proveedorRepository.findById(id);
        if (proveedorOPt.isPresent()){
            return proveedorOPt.get();
        }
        return null;

    }
}

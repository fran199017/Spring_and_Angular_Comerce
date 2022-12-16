package com.francisconicolau.service;


import com.francisconicolau.entity.Proveedor;
import com.francisconicolau.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

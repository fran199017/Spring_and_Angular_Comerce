package com.francisconicolau.repository;

import com.francisconicolau.entity.Proveedor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("proveedorRepository")
public interface ProveedorRepository extends PagingAndSortingRepository<Proveedor, Integer> {

}
